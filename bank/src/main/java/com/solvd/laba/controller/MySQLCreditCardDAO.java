package com.solvd.laba.controller;

import com.solvd.laba.dao.ICreditCardDAO;
import com.solvd.laba.models.CreditCard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLCreditCardDAO extends MySQLDAO implements ICreditCardDAO {
    private final static Logger LOGGER = LogManager.getLogger();

    private final static String CREDIT_CARD_BY_CHECKING_ACCOUNT_ID = "SELECT * FROM bank_solvd.CREDIT_CARD INNER JOIN bank_solvd.CHECKING_ACCOUNT ON CREDIT_CARD.CREDIT_CARD_ID = CHECKING_ACCOUNT.CREDIT_CARD_ID WHERE CHECKING_ACCOUNT_ID = ?";
    final String INSERT = "INSERT INTO bank_solvd.CREDIT_CARD (LIMIT_IN_1_DUE, LIMIT_IN_DUES, EXPIRATION_DATE, SECURITY_CODE, PROVIDER) VALUES (?, ?, ?, ?, ?)";
    final String UPDATE = "UPDATE bank_solvd.CREDIT_CARD SET LIMIT_IN_1_DUE = ?, LIMIT_IN_DUES = ?, EXPIRATION_DATE = ?, SECURITY_CODE = ?, PROVIDER = ? WHERE CREDIT_CARD_ID = ?";
    final String DELETE = "DELETE FROM bank_solvd.CREDIT_CARD WHERE CREDIT_CARD_ID = ?";
    final String SELECT_ONE = "SELECT * FROM bank_solvd.CREDIT_CARD WHERE CREDIT_CARD_ID = ?";
    final String SELECT_ALL = "SELECT * FROM bank_solvd.CREDIT_CARD";


    public MySQLCreditCardDAO() throws SQLException {
    }

    @Override
    public CreditCard getCreditCardByCheckingAccountId(int checkingAccountId) {

        CreditCard c = null;
        try (Connection conn = MySQLDAO.getConnection()
             ; PreparedStatement statement = conn.prepareStatement(
                CREDIT_CARD_BY_CHECKING_ACCOUNT_ID)) {
            statement.setInt(1, checkingAccountId);
            ResultSet rs = statement.executeQuery();
            c = new CreditCard();
            while (rs.next()) {
                c.setId(rs.getInt("CREDIT_CARD_ID"));
                c.setLimitInOneDue(rs.getInt("LIMIT_IN_1_DUE"));
                c.setLimitInDues(rs.getInt("LIMIT_IN_DUES"));
                c.setExpirationDate(rs.getString("EXPIRATION_DATE"));
                c.setSecurityCode(rs.getInt("SECURITY_CODE"));
                c.setProvider(rs.getString("PROVIDER"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;

    }

    @Override
    public void insert(CreditCard a) {
        try (Connection conn = MySQLDAO.getConnection()
             ; PreparedStatement stat = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            stat.setInt(1, a.getLimitInOneDue());
            stat.setInt(2, a.getLimitInDues());
            stat.setString(3, a.getExpirationDate());
            stat.setInt(4, a.getSecurityCode());
            stat.setString(5, a.getProvider());
            stat.executeUpdate();
            ResultSet rs = stat.getGeneratedKeys();
            while (rs.next()) {
                a.setId(rs.getInt(1));
            }
            LOGGER.info("Credit card created.");
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void update(CreditCard a) {
        LOGGER.info("Updating credit card with id " + a.getId() + ".");
        try (Connection conn = MySQLDAO.getConnection()
             ; PreparedStatement stat = conn.prepareStatement(UPDATE)) {
            stat.setInt(6, a.getId());
            stat.setInt(1, a.getLimitInOneDue());
            stat.setInt(2, a.getLimitInDues());
            stat.setString(3, a.getExpirationDate());
            stat.setInt(4, a.getSecurityCode());
            stat.setString(5, a.getProvider());
            stat.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void delete(CreditCard a) {
        LOGGER.info("Deleting credit card with id " + a.getId() + ".");
        try (Connection conn = MySQLDAO.getConnection()
             ; PreparedStatement stat = conn.prepareStatement(DELETE)) {
            stat.setInt(1, a.getId());
            stat.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }


    @Override
    public CreditCard selectOne(int id) {
        CreditCard c = new CreditCard();
        try (Connection conn = MySQLDAO.getConnection()
             ; PreparedStatement stat = conn.prepareStatement(SELECT_ONE)) {
            stat.setInt(1, id);
            ResultSet rs = stat.executeQuery();
            while (rs.next()) {
                c.setId(rs.getInt("CREDIT_CARD_ID"));
                c.setLimitInOneDue(rs.getInt("LIMIT_IN_1_DUE"));
                c.setLimitInDues(rs.getInt("LIMIT_IN_DUES"));
                c.setExpirationDate(rs.getString("EXPIRATION_DATE"));
                c.setSecurityCode(rs.getInt("SECURITY_CODE"));
                c.setProvider(rs.getString("PROVIDER"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return c;
    }

    @Override
    public List<CreditCard> selectAll() {
        ArrayList<CreditCard> cc = new ArrayList<>();
        try (Connection conn = MySQLDAO.getConnection()
             ; PreparedStatement statement = conn.prepareStatement(SELECT_ALL)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                cc.add(new CreditCard(rs.getInt("CREDIT_CARD_ID"), rs.getInt("LIMIT_IN_1_DUE"), rs.getInt("LIMIT_IN_DUES"), rs.getString("EXPIRATION_DATE"), rs.getInt("SECURITY_CODE"), rs.getString("PROVIDER")));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return cc;
    }

}
