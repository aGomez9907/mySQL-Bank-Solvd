package com.solvd.laba.models;


public class CertificateDepositAccount {
    private int id;
    private String startDate;
    private String finishDate;
    private double interestRate;
    private double balance;

    public CertificateDepositAccount() {
    }

    public CertificateDepositAccount(String startDate, String finishDate, double interestRate, double balance) {
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.interestRate = interestRate;
        this.balance = balance;
    }

    public CertificateDepositAccount(int id, String startDate, String finishDate, double interestRate, double balance) {
        this.id = id;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.interestRate = interestRate;
        this.balance = balance;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public String toString() {
        return "CertificateDepositAccount{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", finishDate=" + finishDate +
                ", interestRate=" + interestRate +
                ", balance=" + balance +
                '}';
    }
}
