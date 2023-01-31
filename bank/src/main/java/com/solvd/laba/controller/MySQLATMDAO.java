package com.solvd.laba.controller;

import com.solvd.laba.dao.IATMDAO;
import com.solvd.laba.models.ATM;
import com.solvd.laba.models.CertificateDepositAccount;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLATMDAO extends MySQLDAO implements IATMDAO {

    private final static Logger LOGGER = LogManager.getLogger();

    private final static String ATM_BY_OFFICE_ID = "SELECT * FROM bank_solvd.ATM WHERE OFFICE_ID = ?";

    final String INSERT = "INSERT INTO bank_solvd.ATM (BALANCE, OFFICE_ID) VALUES (?, ?)";
    final String UPDATE = "UPDATE bank_solvd.ATM SET BALANCE = ? WHERE ATM_ID = ?";
    final String DELETE = "DELETE FROM bank_solvd.ATM WHERE ATM_ID = ?";
    final String SELECT_ONE = "SELECT * FROM bank_solvd.ATM WHERE ATM_ID = ?";
    final String SELECT_ALL = "SELECT * FROM bank_solvd.ATM";


    public MySQLATMDAO() throws SQLException {
    }


    public ArrayList<ATM> getAtmsByOfficeId(int id) {
        ArrayList<ATM> c = new ArrayList<>();
        try (Connection conn = MySQLDAO.getConnection()
             ; PreparedStatement statement = conn.prepareStatement(
                ATM_BY_OFFICE_ID)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                ATM atm = new ATM();
                atm.setId(rs.getInt("ATM_ID"));
                atm.setBalance((rs.getDouble("BALANCE")));
                c.add(atm);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }

    @Override
    public void insert(ATM a) {
        try (Connection conn = MySQLDAO.getConnection()
             ; PreparedStatement stat = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {

            stat.setDouble(1, a.getBalance());
            stat.setInt(2, a.getOffice().getId());
            stat.executeUpdate();
            ResultSet rs = stat.getGeneratedKeys();
            while (rs.next()) {
                a.setId(rs.getInt(1));
            }
            LOGGER.info("ATM created.");
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void update(ATM a) {
        LOGGER.info("Updating ATM with id " + a.getId() + ".");
        try (Connection conn = MySQLDAO.getConnection()
             ; PreparedStatement stat = conn.prepareStatement(UPDATE)) {
            stat.setDouble(1, a.getBalance());
            stat.setInt(2, a.getId());
            stat.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void delete(ATM a) {
        LOGGER.info("Deleting ATM with id " + a.getId() + ".");
        try (Connection conn = MySQLDAO.getConnection()
             ; PreparedStatement stat = conn.prepareStatement(DELETE)) {
            stat.setInt(1, a.getId());
            stat.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public ATM selectOne(int id) {
        ATM c = new ATM();
        try (Connection conn = MySQLDAO.getConnection()
             ; PreparedStatement stat = conn.prepareStatement(SELECT_ONE)) {
            stat.setInt(1, id);
            ResultSet rs = stat.executeQuery();
            while (rs.next()) {
                c.setId(rs.getInt("ATM_ID"));
                c.setBalance(rs.getDouble("BALANCE"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return c;
    }

    @Override
    public List<ATM> selectAll() {
        ArrayList<ATM> atms = new ArrayList<>();
        try (Connection conn = MySQLDAO.getConnection()
             ; PreparedStatement statement = conn.prepareStatement(SELECT_ALL)) {

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                atms.add(new ATM(rs.getInt("ATM_ID"), rs.getDouble("BALANCE")));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return atms;
    }
}
