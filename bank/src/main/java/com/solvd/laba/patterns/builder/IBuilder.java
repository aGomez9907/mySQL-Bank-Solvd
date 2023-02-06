package com.solvd.laba.patterns.builder;

public interface IBuilder {

    void reset();

    void setId(int id);

    void setExpirationDate(String expirationDate);

    void setSecurityCode(int securityCode);

    void setProvider(String provider);

    void setLimitInOneDue(int limitInOneDue);

    void setLimitInDues(int limitInDues);
}
