package com.deli.bank.service;

import com.deli.bank.exception.AppException;
import com.deli.bank.exception.MoneyNotEnoughException;

/**
 * @Author:VictoryChan
 * @Description：
 * @Date:Creat in20:38 2022/10/30
 * @Modified By:
 */
public interface AccountService {
    void transfer(String fromActno, String toActno, double money) throws MoneyNotEnoughException, AppException;
}
