package com.solvd.laba.service;

import com.solvd.laba.controller.MySQLATMDAO;
import com.solvd.laba.controller.MySQLBankBranchOfficeDAO;
import com.solvd.laba.models.ATM;
import com.solvd.laba.models.BankBranchOffice;

import java.sql.SQLException;

public class OfficeService {

    MySQLBankBranchOfficeDAO officeDAO = new MySQLBankBranchOfficeDAO();
    MySQLATMDAO atmDAO;

    public OfficeService() throws SQLException {
        this.atmDAO = new MySQLATMDAO();
    }

    public BankBranchOffice selectOne(int id) {
        BankBranchOffice office = officeDAO.selectOne(id);
        office.setAtms(atmDAO.getAtmsByOfficeId(id));
        return office;
    }


    public BankBranchOffice getOfficeByClientId(int id) {
        BankBranchOffice office = officeDAO.getOfficeByClientId(id);
        return selectOne(office.getId());
    }


    public void insert(BankBranchOffice c) throws SQLException {
        officeDAO.insert(c);
        for (ATM atm : c.getAtms()) {
            atm.setOffice(c);
            atmDAO.insert(atm);

        }

    }

    public void update(BankBranchOffice office) throws SQLException {
        for (ATM atm : office.getAtms()) {
            atmDAO.update(atm);
        }
        officeDAO.update(office);
    }
}
