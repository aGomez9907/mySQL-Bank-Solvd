package com.solvd.laba.models;

import java.sql.Date;
import java.time.LocalDate;

public class CertificateDepositAccount {
    private int id;
    private Date startDate;
    private Date finishDate;
    private double interestRate;

    public CertificateDepositAccount() {
    }

    public CertificateDepositAccount(Date startDate, Date finishDate, double interestRate) {
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.interestRate = interestRate;
    }

    public CertificateDepositAccount(int id, Date startDate, Date finishDate, double interestRate) {
        this.id = id;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.interestRate = interestRate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
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
                '}';
    }
}
