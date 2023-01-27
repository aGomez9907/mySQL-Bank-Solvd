package com.solvd.laba.controller;

import com.solvd.laba.dao.IHomeBankingDAO;
import com.solvd.laba.models.HomeBanking;
import com.solvd.laba.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLHomeBankingDAO extends MySQLDAO implements IHomeBankingDAO {
    private final static Logger LOGGER = LogManager.getLogger();
    private final static String HOMEBANKING_BY_CLIENT_ID = "SELECT bank_solvd.HOMEBANKING.* FROM HOMEBANKING INNER JOIN bank_solvd.CLIENT ON HOMEBANKING.HOMEBANKING_ID = CLIENT.HOMEBANKING_ID WHERE CLIENT_ID = ?";
    final String INSERT = "INSERT INTO bank_solvd.HOMEBANKING (USERNAME, PASSWORD) VALUES (?, ?)";
    final String UPDATE = "UPDATE bank_solvd.HOMEBANKING SET USERNAME = ?, PASSWORD = ? WHERE HOMEBANKING_ID = ?";
    final String DELETE = "DELETE FROM bank_solvd.HOMEBANKING WHERE HOMEBANKING_ID = ?";
    final String SELECT_ONE = "SELECT * FROM bank_solvd.HOMEBANKING WHERE HOMEBANKING_ID = ?";
    final String SELECT_ALL = "SELECT * FROM bank_solvd.HOMEBANKING";


    public MySQLHomeBankingDAO() throws SQLException {

    }


    public HomeBanking getHomeBankingByClientId(int accountId) {
        HomeBanking c = null;
        try {
            PreparedStatement statement = conn.prepareStatement(
                    HOMEBANKING_BY_CLIENT_ID);
            statement.setInt(1, accountId);
            ResultSet rs = statement.executeQuery();

            c = new HomeBanking();
            while (rs.next()) {
                c.setId(rs.getInt("HOMEBANKING_ID"));
                c.setUsername(rs.getString("USERNAME"));
                c.setPassword(rs.getString("PASSWORD"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }

    @Override
    public void insert(HomeBanking a) {
        try {
            PreparedStatement stat = conn.prepareStatement(INSERT);
            stat.setString(1, a.getUsername());
            stat.setString(2, a.getPassword());
            stat.executeUpdate();
            LOGGER.info("Homebanking created.");
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void update(HomeBanking a) {
        LOGGER.info("Updating homebanking with id " + a.getId() + ".");
        try (PreparedStatement stat = conn.prepareStatement(UPDATE)) {
            stat.setString(1, a.getUsername());
            stat.setString(2, a.getPassword());
            stat.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void delete(HomeBanking a) {
        LOGGER.info("Deleting homebanking with id " + a.getId() + ".");
        try (PreparedStatement stat = conn.prepareStatement(DELETE)) {
            stat.setInt(1, a.getId());
            stat.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public HomeBanking selectOne(int id) {
        HomeBanking c = new HomeBanking();
        try (PreparedStatement stat = conn.prepareStatement(SELECT_ONE)) {
            stat.setInt(1, id);
            ResultSet rs = stat.executeQuery();
            while (rs.next()) {
                c.setId(rs.getInt("HOMEBANKING_ID"));
                c.setUsername(rs.getString("USERNAME"));
                c.setPassword(rs.getString("PASSWORD"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return c;
    }

    @Override
    public List<HomeBanking> selectAll() {
        ArrayList<HomeBanking> ca = new ArrayList<>();
        try {
            PreparedStatement statement = conn.prepareStatement(SELECT_ALL);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                ca.add(new HomeBanking(rs.getInt("HOMEBANKING_ID"), rs.getString("USERNAME"), rs.getString("PASSWORD")));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return ca;
    }
}
