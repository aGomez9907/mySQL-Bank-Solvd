package com.solvd.laba.models;

public class ATM {
    private int id;
    private double balance;
    private BankBranchOffice office;

    public ATM(double balance, BankBranchOffice office) {
        this.balance = balance;
        this.office = office;
    }

    public ATM(int id, double balance, BankBranchOffice office) {
        this.id = id;
        this.balance = balance;
        this.office = office;
    }

    public ATM() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public BankBranchOffice getOffice() {
        return office;
    }

    public void setOffice(BankBranchOffice office) {
        this.office = office;
    }

    @Override
    public String toString() {
        return "ATM{" +
                "id=" + id +
                ", balance=" + balance +
                ", office=" + office +
                '}';
    }
}
