package com.apricot.store.Service.ex;

public class ProductUnavailableException extends ServiceException {
    public ProductUnavailableException() {
        super();
    }

    public ProductUnavailableException(String message) {
        super(message);
    }

    public ProductUnavailableException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductUnavailableException(Throwable cause) {
        super(cause);
    }

    protected ProductUnavailableException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
