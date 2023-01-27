package com.solvd.laba.models;

public class Client {
    private int id;
    private String name;
    private String surname;
    private int age;
    private String country;
    private String address;
    private BankBranchOffice office;
    private CertificateDepositAccount certificateDepositAccount;
    private CheckingAccount checkingAccount;
    private CreditSummary creditSummary;
    private SavingAccount savingAccount;
    private HomeBanking homeBanking;

    public Client() {

    }

    public Client(int id, String name, String surname, int age, String country, String address, BankBranchOffice office, CreditSummary creditSummary) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.country = country;
        this.address = address;
        this.office = office;
        this.creditSummary = creditSummary;
    }

    public Client(int id, String name, String surname, int age, String country, String address) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.country = country;
        this.address = address;
    }

    public BankBranchOffice getOffice() {
        return office;
    }

    public void setOffice(BankBranchOffice office) {
        this.office = office;
    }

    public CertificateDepositAccount getCertificateDepositAccount() {
        return certificateDepositAccount;
    }

    public void setCertificateDepositAccount(CertificateDepositAccount certificateDepositAccount) {
        this.certificateDepositAccount = certificateDepositAccount;
    }

    public CheckingAccount getCheckingAccount() {
        return checkingAccount;
    }

    public void setCheckingAccount(CheckingAccount checkingAccount) {
        this.checkingAccount = checkingAccount;
    }

    public CreditSummary getCreditSummary() {
        return creditSummary;
    }

    public void setCreditSummary(CreditSummary creditSummary) {
        this.creditSummary = creditSummary;
    }

    public SavingAccount getSavingAccount() {
        return savingAccount;
    }

    public void setSavingAccount(SavingAccount savingAccount) {
        this.savingAccount = savingAccount;
    }

    public HomeBanking getHomeBanking() {
        return homeBanking;
    }

    public void setHomeBanking(HomeBanking homeBanking) {
        this.homeBanking = homeBanking;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", country='" + country + '\'' +
                ", address='" + address + '\'' +
                ", office=" + office +
                ", certificateDepositAccount=" + certificateDepositAccount +
                ", checkingAccount=" + checkingAccount +
                ", creditSummary=" + creditSummary +
                ", savingAccount=" + savingAccount +
                ", homeBanking=" + homeBanking +
                '}';
    }
}
