package gr6.se2.exception.auth;

import gr6.se2.exception.BusinessLogicException;

public class InvalidAccessTokenException extends BusinessLogicException {
    public InvalidAccessTokenException() {
        super("You don't have the role to perform this action");
    }
}