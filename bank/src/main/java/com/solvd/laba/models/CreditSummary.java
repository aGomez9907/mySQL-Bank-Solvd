package com.solvd.laba.models;

public class CreditSummary {
    private int id;
    private Client client;
    private double salary;
    private double patrimonyM;
    private boolean creditTaken;

    public CreditSummary() {
    }

    public CreditSummary(int id, Client client, double salary, double patrimonyM, boolean creditTaken) {
        this.id = id;
        this.client = client;
        this.salary = salary;
        this.patrimonyM = patrimonyM;
        this.creditTaken = creditTaken;
    }

    public CreditSummary(Client client, double salary, double patrimonyM, boolean creditTaken) {
        this.client = client;
        this.salary = salary;
        this.patrimonyM = patrimonyM;
        this.creditTaken = creditTaken;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getPerson() {
        return client;
    }

    public void setPerson(Client client) {
        this.client = client;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getPatrimonyM() {
        return patrimonyM;
    }

    public void setPatrimonyM(double patrimonyM) {
        this.patrimonyM = patrimonyM;
    }

    public boolean isCreditTaken() {
        return creditTaken;
    }

    public void setCreditTaken(boolean creditTaken) {
        this.creditTaken = creditTaken;
    }

    @Override
    public String toString() {
        return "CreditSummary{" +
                "id=" + id +
                ", client=" + client +
                ", salary=" + salary +
                ", patrimonyM=" + patrimonyM +
                ", creditTaken=" + creditTaken +
                '}';
    }
}
