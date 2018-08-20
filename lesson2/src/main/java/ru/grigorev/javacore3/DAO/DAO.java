package ru.grigorev.javacore3.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Dmitriy Grigorev
 */
public interface DAO {
    void createTableIfNotExists() throws SQLException;

    void fillTableWithGoods(int quantity) throws SQLException;

    void deleteTableIfExist() throws SQLException;

    int getPriceByTitle(String title) throws SQLException;

    void changePriceOfGood(String title, String price) throws SQLException;

    ResultSet getGoodsBetween(String from, String to) throws SQLException;

    void printResultSet(ResultSet rs) throws SQLException;
}
