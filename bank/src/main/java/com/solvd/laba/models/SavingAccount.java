package com.solvd.laba.models;

public class SavingAccount {
    private int id;
    private int monthWithdrawals;

    private double balance;

    public SavingAccount() {
    }

    public SavingAccount(int id, int monthWithdrawals, double balance) {
        this.id = id;
        this.monthWithdrawals = monthWithdrawals;
        this.balance = balance;
    }

    public SavingAccount(int monthWithdrawals, double balance) {
        this.monthWithdrawals = monthWithdrawals;
        this.balance = balance;
    }

    public SavingAccount(int monthWithdrawals) {
        this.monthWithdrawals = monthWithdrawals;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMonthWithdrawals() {
        return monthWithdrawals;
    }

    public void setMonthWithdrawals(int monthWithdrawals) {
        this.monthWithdrawals = monthWithdrawals;
    }

    @Override
    public String toString() {
        return "SavingAccount{" +
                "id=" + id +
                ", monthWithdrawals=" + monthWithdrawals +
                ", balance=" + balance +
                '}';
    }
}
