package com.apricot.store.Service.ex;

public class TooManyAddressesException extends ServiceException {
    public TooManyAddressesException() {
        super();
    }

    public TooManyAddressesException(String message) {
        super(message);
    }

    public TooManyAddressesException(String message, Throwable cause) {
        super(message, cause);
    }

    public TooManyAddressesException(Throwable cause) {
        super(cause);
    }

    protected TooManyAddressesException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
