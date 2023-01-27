package com.solvd.laba.models;

public class CheckingAccount {
    private int id;
    private int checks;
    private double balance;

    private CreditCard creditCard;
    private DebitCard debitCard;

    public CheckingAccount() {
    }

    public CheckingAccount(int id, int checks, double balance) {
        this.id = id;
        this.checks = checks;
        this.balance = balance;
    }


    public CheckingAccount(int checks, double balance) {
        this.checks = checks;
        this.balance = balance;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public DebitCard getDebitCard() {
        return debitCard;
    }

    public void setDebitCard(DebitCard debitCard) {
        this.debitCard = debitCard;
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

    public int getChecks() {
        return checks;
    }

    public void setChecks(int checks) {
        this.checks = checks;
    }

    @Override
    public String toString() {
        return "CheckingAccount{" +
                "id=" + id +
                ", checks=" + checks +
                ", balance=" + balance +
                ", creditCard=" + creditCard +
                ", debitCard=" + debitCard +
                '}';
    }
}
