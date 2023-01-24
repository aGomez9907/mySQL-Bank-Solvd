package com.solvd.laba.models;

public class AccountsMain {
    private int id;
    private Client client;
    private CertificateDepositAccount certificateDeposit;
    private SavingAccount saving;
    private CheckingAccount checking;
    private double balance;
    private BankBranchOffice office;

    public AccountsMain() {
    }

    public AccountsMain(Client client, CertificateDepositAccount certificateDeposit, SavingAccount saving, CheckingAccount checking, double balance, BankBranchOffice office) {
        this.client = client;
        this.certificateDeposit = certificateDeposit;
        this.checking = checking;
        this.saving = saving;
        this.balance = balance;
        this.office = office;
    }

    public AccountsMain(int id, Client client, CertificateDepositAccount certificateDeposit, CheckingAccount checking, SavingAccount saving, double balance, BankBranchOffice office) {
        this.id = id;
        this.client = client;
        this.certificateDeposit = certificateDeposit;
        this.checking = checking;
        this.saving = saving;
        this.balance = balance;
        this.office = office;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public CertificateDepositAccount getCertificateDeposit() {
        return certificateDeposit;
    }

    public void setCertificateDeposit(CertificateDepositAccount certificateDeposit) {
        this.certificateDeposit = certificateDeposit;
    }

    public CheckingAccount getChecking() {
        return checking;
    }

    public void setChecking(CheckingAccount checking) {
        this.checking = checking;
    }

    public SavingAccount getSaving() {
        return saving;
    }

    public void setSaving(SavingAccount saving) {
        this.saving = saving;
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
        return "AccountsMain{" +
                "id=" + id +
                ", client=" + client +
                ", certificateDepositID=" + certificateDeposit +
                ", checkingID=" + checking +
                ", savingID=" + saving +
                ", balance=" + balance +
                ", office=" + office +
                '}';
    }

}
