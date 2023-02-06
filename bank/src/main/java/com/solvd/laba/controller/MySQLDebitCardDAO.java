package com.solvd.laba.controller;

import com.solvd.laba.dao.IDebitCardDAO;
import com.solvd.laba.models.DebitCard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLDebitCardDAO extends MySQLDAO implements IDebitCardDAO {
    private final static Logger LOGGER = LogManager.getLogger();

    private final static String DEBIT_CARD_BY_CHECKING_ACCOUNT_ID = "SELECT * FROM bank_solvd.DEBIT_CARD INNER JOIN bank_solvd.CHECKING_ACCOUNT ON DEBIT_CARD.DEBIT_CARD_ID = CHECKING_ACCOUNT.DEBIT_CARD_ID WHERE CHECKING_ACCOUNT_ID = ?";
    final String INSERT = "INSERT INTO bank_solvd.DEBIT_CARD (EXPIRATION_DATE, SECURITY_CODE, PROVIDER) VALUES (?, ?, ?)";
    final String UPDATE = "UPDATE bank_solvd.DEBIT_CARD SET EXPIRATION_DATE = ?, SECURITY_CODE = ?, PROVIDER = ? WHERE DEBIT_CARD_ID = ?";
    final String DELETE = "DELETE FROM bank_solvd.DEBIT_CARD WHERE DEBIT_CARD_ID = ?";
    final String SELECT_ONE = "SELECT * FROM bank_solvd.DEBIT_CARD WHERE DEBIT_CARD_ID = ?";
    final String SELECT_ALL = "SELECT * FROM bank_solvd.DEBIT_CARD";


    public MySQLDebitCardDAO() throws SQLException {

    }


    public DebitCard getDebitCardByCheckingAccountId(int checkingAccountId) {

        DebitCard c = null;
        try (Connection conn = MySQLDAO.getConnection()
             ; PreparedStatement statement = conn.prepareStatement(
                DEBIT_CARD_BY_CHECKING_ACCOUNT_ID)) {
            statement.setInt(1, checkingAccountId);
            ResultSet rs = statement.executeQuery();
            c = new DebitCard();
            while (rs.next()) {
                c.setId(rs.getInt("DEBIT_CARD_ID"));
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
    public void insert(DebitCard a) {
        try (Connection conn = MySQLDAO.getConnection()
             ; PreparedStatement stat = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            stat.setString(1, a.getExpirationDate());
            stat.setInt(2, a.getSecurityCode());
            stat.setString(3, a.getProvider());
            stat.executeUpdate();
            ResultSet rs = stat.getGeneratedKeys();
            while (rs.next()) {
                a.setId(rs.getInt(1));
            }
            LOGGER.info("Debit card created.");
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void update(DebitCard a) {
        LOGGER.info("Updating debit card with id " + a.getId() + ".");
        try (Connection conn = MySQLDAO.getConnection()
             ; PreparedStatement stat = conn.prepareStatement(UPDATE)) {
            stat.setInt(4, a.getId());
            stat.setString(1, a.getExpirationDate());
            stat.setInt(2, a.getSecurityCode());
            stat.setString(3, a.getProvider());
            stat.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void delete(DebitCard a) {
        LOGGER.info("Deleting debit card with id " + a.getId() + ".");
        try (Connection conn = MySQLDAO.getConnection()
             ; PreparedStatement stat = conn.prepareStatement(DELETE)) {
            stat.setInt(1, a.getId());
            stat.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public DebitCard selectOne(int id) {
        DebitCard c = new DebitCard();
        try (Connection conn = MySQLDAO.getConnection()
             ; PreparedStatement stat = conn.prepareStatement(SELECT_ONE)) {
            stat.setInt(1, id);
            ResultSet rs = stat.executeQuery();
            while (rs.next()) {
                c.setId(rs.getInt("DEBIT_CARD_ID"));
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
    public List<DebitCard> selectAll() {
        ArrayList<DebitCard> dc = new ArrayList<>();
        try (Connection conn = MySQLDAO.getConnection()
             ; PreparedStatement statement = conn.prepareStatement(SELECT_ALL)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                dc.add(new DebitCard(rs.getInt("DEBIT_CARD_ID"), rs.getString("EXPIRATION_DATE"), rs.getInt("SECURITY_CODE"), rs.getString("PROVIDER")));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return dc;
    }


}
