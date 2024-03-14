package gr6.se2.service.auth;

import gr6.se2.exception.auth.AccessTokenExpiredException;
import gr6.se2.exception.auth.AccountDeletedException;
import gr6.se2.exception.auth.InvalidAccessTokenException;
import gr6.se2.model.auth.Account;
import gr6.se2.model.auth.AccountRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class AuthorizeService {
    private final String secretKey;
    private final AccountRepository accountRepository;

    public AuthorizeService(@Value("${auth.secret-key}")
                            String secretKey,
                            AccountRepository accountRepository) {
        this.secretKey = secretKey;
        this.accountRepository = accountRepository;
    }

    public int authorize(String token, Account.Role role) throws AccountDeletedException, AccessTokenExpiredException, InvalidAccessTokenException {
        final Claims claims = Jwts.parser()
                .setSigningKey(secretKey.getBytes())
                .parseClaimsJws(token)
                .getBody();
        final String username = claims.getId();
        final Account account = accountRepository.findByUsername(username);
        if (account == null)
            throw new AccountDeletedException();
        if (!account.getRole().equals(role))
            throw new InvalidAccessTokenException();
        if (/*
            Check if the access token has expired
             */
                claims.getExpiration().before(Date.from(Instant.now())) ||
                        /*
                        Check if the access token is invalidated by checking its created time
                         */
                        !account.acceptAccessTokenCreatedAt(claims.getIssuedAt()))
            throw new AccessTokenExpiredException();
        return account.getOwnerId();
    }
}
