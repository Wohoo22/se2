package gr6.se2.service.auth;

import gr6.se2.exception.auth.UnknownAccountException;
import gr6.se2.exception.auth.WrongPasswordException;
import gr6.se2.model.auth.Account;
import gr6.se2.model.auth.AccountRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class LoginService {
    private final AccountRepository accountRepository;
    private static final int TOKEN_ALIVE_TIME_IN_DAYS = 10;
    private final String secretKey;

    public LoginService(AccountRepository accountRepository,
                        @Value("${auth.secret-key}")
                        String secretKey) {
        this.accountRepository = accountRepository;
        this.secretKey = secretKey;
    }

    public String login(String username, String password) throws UnknownAccountException, WrongPasswordException {
        final Account account = accountRepository.findByUsername(username);
        if (account == null)
            throw new UnknownAccountException(username);
        if (account.getPassword().equals(password))
            return generateBearerToken(account);
        else
            throw new WrongPasswordException();
    }

    private String generateBearerToken(Account account) {
        return Jwts.builder()
                .setId(account.getUsername())
                .setExpiration(Date.from(Instant.now().plus(TOKEN_ALIVE_TIME_IN_DAYS, ChronoUnit.DAYS)))
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes())
                .compact();
    }
}
