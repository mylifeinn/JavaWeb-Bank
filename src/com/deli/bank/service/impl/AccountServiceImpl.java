package com.deli.bank.service;

import com.deli.bank.dao.AccountDao;
import com.deli.bank.dao.AccountDaoImpl;
import com.deli.bank.exception.AppException;
import com.deli.bank.exception.MoneyNotEnoughException;
import com.deli.bank.pojo.Account;
import com.deli.bank.utils.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * service翻译为：业务
 * AccountService:专门处理Account业务的一个类。
 * 在该类中应该编写纯业务代码。
 * 能让业务完美实现，少量bug。
 */
public class AccountServiceImpl implements AccountService {
    //为什么定义到这里，因为在每一个业务方法中都可以连接数据库。
    private AccountDao accountDao = new AccountDaoImpl();

    //这里的方法起名一定要体现出处理的什么业务

    /**
     * 完成转账的业务逻辑
     *
     * @param fromActno 转出账号
     * @param toActno   转入账号
     * @param money     转账金额
     */
    public void transfer(String fromActno, String toActno, double money) throws MoneyNotEnoughException, AppException {
        //service层控制事务
        //先不管代码好不好看，先把问题解决了，不想在service中用connection，得用动态代理(还不会- -)
        try (Connection connection = DBUtil.getConnection()){//资源自动管理
            System.out.println(connection);
            //开启事务(需要使用Connection对象)
            connection.setAutoCommit(false);
            //查询余额是否充足
            Account fromAct = accountDao.selectByActno(fromActno);
            if (fromAct.getBalance() < money) {
                throw new MoneyNotEnoughException("对不起，余额不足");
            }
            //程序到这里说明余额充足
            Account toAct = accountDao.selectByActno(toActno);
            //修改余额（只是修改了内存中java对象的余额）
            fromAct.setBalance(fromAct.getBalance() - money);
            toAct.setBalance(toAct.getBalance() + money);
            //更新数据库中的余额
            int count = accountDao.update(fromAct);

            //模拟异常
/*            String s=null;
            s.toString();*/

            count += accountDao.update(toAct);
            if (count!=2) {
                throw new AppException("账户转账异常!!!");
            }
            //提交事务,这里不能解决事务问题，因为不是同一个connection对象
            connection.commit();
        } catch (SQLException e) {
            throw new AppException("账户转账异常!!!");
        }finally {
            //关闭资源
        }

    }
}
