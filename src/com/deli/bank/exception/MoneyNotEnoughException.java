package com.deli.bank.exception;

/**
 * 余额不足异常
 * @author Victory
 * @version 2.0
 * @since 2.0
 */
public class MoneyNotEnoughException extends Exception{
    public MoneyNotEnoughException() {
    }

    public MoneyNotEnoughException(String msg) {
        super(msg);
    }
}
