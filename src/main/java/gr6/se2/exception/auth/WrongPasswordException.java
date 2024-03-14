package gr6.se2.exception.auth;

import gr6.se2.exception.BusinessLogicException;

public class WrongPasswordException extends BusinessLogicException {
    public WrongPasswordException() {
        super("Wrong password, please try again");
    }
}
