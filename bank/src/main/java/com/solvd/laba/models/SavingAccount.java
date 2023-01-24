package com.solvd.laba.models;

public class SavingAccount {
    private int id;
    private int monthWithdrawals;

    public SavingAccount() {
    }

    public SavingAccount(int id, int monthWithdrawals) {
        this.id = id;
        this.monthWithdrawals = monthWithdrawals;
    }

    public SavingAccount(int monthWithdrawals) {
        this.monthWithdrawals = monthWithdrawals;
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
                '}';
    }
}
