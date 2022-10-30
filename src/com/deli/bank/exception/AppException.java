package com.deli.bank.exception;

/**
 * App异常
 * @author Victory
 * @version 2.0
 * @since 2.0
 */
public class AppException extends Exception{
    public AppException() {
    }

    public AppException(String msg) {
        super(msg);
    }
}
