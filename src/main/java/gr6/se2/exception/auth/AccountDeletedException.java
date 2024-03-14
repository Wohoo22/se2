package gr6.se2.exception.auth;

import gr6.se2.exception.BusinessLogicException;

public class AccountDeletedException extends BusinessLogicException  {
    public AccountDeletedException() {
        super("This account has been deleted, please contact your administrator");
    }
}
