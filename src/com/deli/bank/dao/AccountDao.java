package com.deli.bank.dao;

import com.deli.bank.pojo.Account;

import java.util.List;

/**
 * @Author:VictoryChan
 * @Description：
 * @Date:Creat in20:36 2022/10/30
 * @Modified By:
 */
public interface AccountDao {
    int insert(Account act);
    int deleteById(Long id);
    int update(Account act);
    Account selectByActno(String actno);
    List<Account> selectAll();
}
