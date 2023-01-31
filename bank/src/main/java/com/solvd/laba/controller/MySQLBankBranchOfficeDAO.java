package com.solvd.laba.controller;

import com.solvd.laba.dao.IBankBranchOfficeDAO;
import com.solvd.laba.models.BankBranchOffice;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLBankBranchOfficeDAO extends MySQLDAO implements IBankBranchOfficeDAO {

    private final static Logger LOGGER = LogManager.getLogger();
    private final static String OFFICE_BY_CLIENT_ID = "SELECT * FROM BANK_BRANCH_OFFICE INNER JOIN bank_solvd.CLIENT ON BANK_BRANCH_OFFICE.OFFICE_ID = CLIENT.OFFICE_ID WHERE CLIENT_ID = ?";
    final String INSERT = "INSERT INTO bank_solvd.BANK_BRANCH_OFFICE (GENERAL_BALANCE, COUNTRY, ADDRESS) VALUES (?, ?, ?)";
    final String UPDATE = "UPDATE bank_solvd.BANK_BRANCH_OFFICE SET GENERAL_BALANCE = ?, COUNTRY = ?, ADDRESS = ? WHERE OFFICE_ID = ?";
    final String DELETE = "DELETE FROM bank_solvd.BANK_BRANCH_OFFICE WHERE OFFICE_ID = ?";
    final String SELECT_ONE = "SELECT * FROM bank_solvd.BANK_BRANCH_OFFICE WHERE OFFICE_ID = ?";
    final String SELECT_ALL = "SELECT * FROM bank_solvd.BANK_BRANCH_OFFICE";


    public MySQLBankBranchOfficeDAO() throws SQLException {
    }

    public BankBranchOffice getOfficeByClientId(int accountId) {
        BankBranchOffice c = null;
        try (Connection conn = MySQLDAO.getConnection()
             ; PreparedStatement statement = conn.prepareStatement(
                OFFICE_BY_CLIENT_ID)) {

            statement.setInt(1, accountId);
            ResultSet rs = statement.executeQuery();

            c = new BankBranchOffice();
            while (rs.next()) {
                c.setId(rs.getInt("OFFICE_ID"));
                c.setGeneralBalance(rs.getDouble("GENERAL_BALANCE"));
                c.setCountry(rs.getString("COUNTRY"));
                c.setAddress(rs.getString("ADDRESS"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }

    @Override
    public void insert(BankBranchOffice a) {
        try (Connection conn = MySQLDAO.getConnection()
             ; PreparedStatement stat = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            stat.setDouble(1, a.getGeneralBalance());
            stat.setString(2, a.getCountry());
            stat.setString(3, a.getAddress());
            stat.executeUpdate();
            ResultSet rs = stat.getGeneratedKeys();
            while (rs.next()) {
                a.setId(rs.getInt(1));
            }
            LOGGER.info("Office created.");
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void update(BankBranchOffice a) {
        LOGGER.info("Updating Office with id " + a.getId() + ".");
        try (Connection conn = MySQLDAO.getConnection()
             ; PreparedStatement stat = conn.prepareStatement(UPDATE)) {
            stat.setDouble(1, a.getGeneralBalance());
            stat.setString(2, a.getCountry());
            stat.setString(3, a.getAddress());
            stat.setInt(4, a.getId());
            stat.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void delete(BankBranchOffice a) {
        LOGGER.info("Deleting Office with id " + a.getId() + ".");
        try (Connection conn = MySQLDAO.getConnection()
             ; PreparedStatement stat = conn.prepareStatement(DELETE)) {
            stat.setInt(1, a.getId());
            stat.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public BankBranchOffice selectOne(int id) {
        BankBranchOffice c = new BankBranchOffice();
        try (Connection conn = MySQLDAO.getConnection()
             ; PreparedStatement stat = conn.prepareStatement(SELECT_ONE)) {
            stat.setInt(1, id);
            ResultSet rs = stat.executeQuery();
            while (rs.next()) {
                c.setId(rs.getInt("OFFICE_ID"));
                c.setGeneralBalance(rs.getDouble("GENERAL_BALANCE"));
                c.setCountry(rs.getString("COUNTRY"));
                c.setAddress(rs.getString("ADDRESS"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return c;
    }

    @Override
    public List<BankBranchOffice> selectAll() {
        ArrayList<BankBranchOffice> offices = new ArrayList<>();
        try (Connection conn = MySQLDAO.getConnection()
             ; PreparedStatement statement = conn.prepareStatement(SELECT_ALL)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                offices.add(new BankBranchOffice(rs.getInt("OFFICE_ID"), rs.getDouble("GENERAL_BALANCE"), rs.getString("COUNTRY"), rs.getString("ADDRESS")));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return offices;

    }
}
