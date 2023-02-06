package com.solvd.laba.controller;

import com.solvd.laba.dao.ICreditSummaryDAO;
import com.solvd.laba.models.CreditSummary;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLCreditSummaryDAO extends MySQLDAO implements ICreditSummaryDAO {

    private final static Logger LOGGER = LogManager.getLogger();
    private final static String CREDIT_SUMMARY_BY_CLIENT_ID = "SELECT * FROM bank_solvd.CREDIT_SUMMARY INNER JOIN bank_solvd.CLIENT ON CREDIT_SUMMARY.CREDIT_SUMMARY_ID = CLIENT.CREDIT_SUMMARY_ID WHERE CLIENT_ID = ?";
    final String INSERT = "INSERT INTO bank_solvd.CREDIT_SUMMARY (SALARY, PATRIMONY, CREDIT_TAKEN) VALUES (?, ?, ?)";
    final String UPDATE = "UPDATE bank_solvd.CREDIT_SUMMARY SET SALARY = ?, PATRIMONY = ?, CREDIT_TAKEN = ? WHERE CREDIT_SUMMARY_ID = ?";
    final String DELETE = "DELETE FROM bank_solvd.CREDIT_SUMMARY WHERE CREDIT_SUMMARY_ID = ?";
    final String SELECT_ONE = "SELECT * FROM bank_solvd.CREDIT_SUMMARY WHERE CREDIT_SUMMARY_ID = ?";
    final String SELECT_ALL = "SELECT * FROM bank_solvd.CREDIT_SUMMARY";


    public MySQLCreditSummaryDAO() throws SQLException {

    }

    public CreditSummary getCreditSummaryByClientId(int accountId) {
        CreditSummary c = null;
        try (Connection conn = MySQLDAO.getConnection()
             ; PreparedStatement statement = conn.prepareStatement(
                CREDIT_SUMMARY_BY_CLIENT_ID)) {
            statement.setInt(1, accountId);
            ResultSet rs = statement.executeQuery();

            c = new CreditSummary();
            while (rs.next()) {
                c.setId(rs.getInt("CREDIT_SUMMARY_ID"));
                c.setSalary(rs.getDouble("SALARY"));
                c.setPatrimony(rs.getDouble("PATRIMONY"));
                c.setCreditTaken(rs.getBoolean("CREDIT_TAKEN"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }

    @Override
    public void insert(CreditSummary a) {
        try (Connection conn = MySQLDAO.getConnection()
             ; PreparedStatement stat = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            stat.setDouble(1, a.getSalary());
            stat.setDouble(2, a.getPatrimony());
            stat.setBoolean(3, a.isCreditTaken());
            stat.executeUpdate();
            ResultSet rs = stat.getGeneratedKeys();
            while (rs.next()) {
                a.setId(rs.getInt(1));
            }

            LOGGER.info("Credit summary created.");
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void update(CreditSummary a) {
        LOGGER.info("Updating credit summary with id " + a.getId() + ".");
        try (Connection conn = MySQLDAO.getConnection()
             ; PreparedStatement stat = conn.prepareStatement(UPDATE)) {
            stat.setInt(4, a.getId());
            stat.setDouble(1, a.getSalary());
            stat.setDouble(2, a.getPatrimony());
            stat.setBoolean(3, a.isCreditTaken());
            stat.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void delete(CreditSummary a) {
        LOGGER.info("Deleting credit summary with id " + a.getId() + ".");
        try (Connection conn = MySQLDAO.getConnection()
             ; PreparedStatement stat = conn.prepareStatement(DELETE)) {
            stat.setInt(1, a.getId());
            stat.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public CreditSummary selectOne(int id) {
        CreditSummary c = new CreditSummary();
        try (Connection conn = MySQLDAO.getConnection()
             ; PreparedStatement stat = conn.prepareStatement(SELECT_ONE)) {
            stat.setInt(1, id);
            ResultSet rs = stat.executeQuery();
            while (rs.next()) {
                c.setId(rs.getInt("CREDIT_SUMMARY_ID"));
                c.setSalary(rs.getDouble("SALARY"));
                c.setPatrimony(rs.getDouble("PATRIMONY"));
                c.setCreditTaken(rs.getBoolean("CREDIT_TAKEN"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return c;
    }

    @Override
    public List<CreditSummary> selectAll() {
        ArrayList<CreditSummary> cs = new ArrayList<>();
        try (Connection conn = MySQLDAO.getConnection()
             ; PreparedStatement statement = conn.prepareStatement(SELECT_ALL)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                cs.add(new CreditSummary(rs.getInt("CREDIT_SUMMARY_ID"), rs.getDouble("SALARY"), rs.getDouble("PATRIMONY"), rs.getBoolean("CREDIT_TAKEN")));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return cs;
    }


}
