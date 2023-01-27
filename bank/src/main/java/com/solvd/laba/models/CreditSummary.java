package com.solvd.laba.models;

public class CreditSummary {
    private int id;
    private double salary;
    private double patrimony;
    private boolean creditTaken;

    public CreditSummary() {
    }

    public CreditSummary(int id, double salary, double patrimony, boolean creditTaken) {
        this.id = id;
        this.salary = salary;
        this.patrimony = patrimony;
        this.creditTaken = creditTaken;
    }

    public CreditSummary(double salary, double patrimony, boolean creditTaken) {
        this.salary = salary;
        this.patrimony = patrimony;
        this.creditTaken = creditTaken;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getPatrimony() {
        return patrimony;
    }

    public void setPatrimony(double patrimony) {
        this.patrimony = patrimony;
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
                ", salary=" + salary +
                ", patrimony=" + patrimony +
                ", creditTaken=" + creditTaken +
                '}';
    }
}
