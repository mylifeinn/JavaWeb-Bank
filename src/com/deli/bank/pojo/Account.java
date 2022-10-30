package com.deli.bank.pojo;

/**
 * 账户实体类:封装账户信息，一般一张表一个
 * @author Victory
 * @version 1.0
 * @since 1.0
 */
public class Account {
    //一般这种属性不设计为基本数据类型，建议使用包装类，首字母大写，防止null带来的问题；
    //private long id;
    private Long id;
    private String actno;
    private Double balance;

    public Account() {
    }

    public Account(Long id, String actno, Double balance) {
        this.id = id;
        this.actno = actno;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActno() {
        return actno;
    }

    public void setActno(String actno) {
        this.actno = actno;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", actno='" + actno + '\'' +
                ", balance=" + balance +
                '}';
    }
}
