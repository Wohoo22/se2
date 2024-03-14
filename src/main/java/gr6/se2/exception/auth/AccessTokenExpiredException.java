package gr6.se2.exception.auth;

import gr6.se2.exception.BusinessLogicException;

public class AccessTokenExpiredException extends BusinessLogicException {
    public AccessTokenExpiredException() {
        super("Access token expired, please log in again");
    }
}
