package ru.grigorev.javacore3.DAO;

import java.sql.*;

/**
 * @author Dmitriy Grigorev
 */
public class DAOimpl implements DAO {
    private Connection connection;

    public DAOimpl(Connection connection) {
        this.connection = connection;
    }

    public void createTableIfNotExists() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute(
                "CREATE TABLE IF NOT EXISTS products (\n" +
                        "    id     INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL DEFAULT (1),\n" +
                        "    prodid INTEGER NOT NULL,\n" +
                        "    title  TEXT    NOT NULL,\n" +
                        "    cost   INTEGER NOT NULL\n" +
                        ");");
    }

    public void fillTableWithGoods(int quantity) throws SQLException {
        connection.setAutoCommit(false);
        String sql = "INSERT INTO products (prodid, title, cost) VALUES (?, ?, ?);";
        PreparedStatement ps = connection.prepareStatement(sql);
        long time1 = System.currentTimeMillis();
        for (Integer i = 1; i < quantity + 1; i++) {
            ps.setInt(1, i);
            ps.setString(2, "товар" + i);
            ps.setInt(3, i * 10);
            ps.addBatch();
        }
        long time2 = System.currentTimeMillis();
        System.out.println("added " + quantity + " strings... time - " + (time2 - time1) + " ms");
        ps.executeBatch();
        long time3 = System.currentTimeMillis();
        System.out.println("executing time for " + quantity + " strings... - " + (time3 - time2) + " ms"); //~100ms
        ps.close();
        connection.setAutoCommit(true);
    }

    public void deleteTableIfExist() throws SQLException {
        Statement deleteStatement = connection.createStatement();
        deleteStatement.execute("DROP TABLE IF EXISTS products;");
        deleteStatement.close();
    }

    public int getPriceByTitle(String title) throws SQLException {
        if (title == null) throw new SQLException();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("Select * from products where title like '" + title + "';");
        int price = resultSet.getInt(4);
        statement.close();
        return price;
    }

    public void changePriceOfGood(String title, String price) throws SQLException {
        if (title == null || price == null) throw new SQLException();
        try {
            Integer.parseInt(price);
        } catch (NumberFormatException e) {
            System.out.println("Wrong price!");
            return;
        }
        Statement statement = connection.createStatement();
        statement.execute("UPDATE products SET cost = '" + price + "' WHERE title = '" + title + "'");
        statement.close();
        System.out.println("Price has changed");
    }

    public ResultSet getGoodsBetween(String from, String to) throws SQLException {
        if (from == null || to == null) throw new SQLException();
        try {
            Integer.parseInt(from);
            Integer.parseInt(to);
        } catch (NumberFormatException e) {
            System.out.println("Wrong prices!");
            return null;
        }
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(
                "SELECT * FROM products WHERE cost >= " + from + " AND cost <= " + to + ";");
        return resultSet;
    }

    public void printResultSet(ResultSet rs) throws SQLException {
        if (rs == null) throw new SQLException();
        while (rs.next()) {
            int id = rs.getInt(1);
            int prodid = rs.getInt(2);
            String title = rs.getString(3);
            int cost = rs.getInt(4);
            System.out.println("id: " + id + " prodid: " + prodid + " title: " + title + " cost: " + cost);
        }
    }
}
