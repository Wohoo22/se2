package gr6.se2.exception.auth;

import gr6.se2.exception.BusinessLogicException;

public class UnknownAccountException extends BusinessLogicException {
    public UnknownAccountException(String username) {
        super("Cannot find account with username: " + username);
    }
}
