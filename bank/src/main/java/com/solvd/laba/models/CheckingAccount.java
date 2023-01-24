package com.solvd.laba.models;

public class CheckingAccount {
    private int id;
    private int checks;

    public CheckingAccount() {
    }

    public CheckingAccount(int id, int checks) {
        this.id = id;
        this.checks = checks;
    }

    public CheckingAccount(int checks) {
        this.checks = checks;
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
        return "SavingAccount{" +
                "id=" + id +
                ", checks=" + checks +
                '}';
    }
}
