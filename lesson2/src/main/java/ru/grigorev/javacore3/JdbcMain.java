package ru.grigorev.javacore3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

/**
 * @author Dmitriy Grigorev
 */
public class JdbcMain {
    private static Connection connection;

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:myDb.db");

        try {
            createTableIfNotExists();
            clearAndFillTheTableWithGoods(10_000);
            getCommandFromConsole();
        } finally {
            connection.close();
        }
    }

    public static void createTableIfNotExists() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute(
                "CREATE TABLE IF NOT EXISTS products (\n" +
                        "    id     INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL DEFAULT (1),\n" +
                        "    prodid INTEGER NOT NULL,\n" +
                        "    title  TEXT    NOT NULL,\n" +
                        "    cost   INTEGER NOT NULL\n" +
                        ");");
    }

    public static void clearAndFillTheTableWithGoods(int quantity) throws SQLException {
        Statement clearStatement = connection.createStatement();
        clearStatement.executeUpdate("DELETE FROM products;");
        clearStatement.executeUpdate("VACUUM");

        // in a following code the time for adding a string by separate query was ~40 minutes for 10 000 strings

        /*String sql = "INSERT INTO products (prodid, title, cost) VALUES (?, ?, ?);";
        PreparedStatement ps = connection.prepareStatement(sql);
        long time1 = System.currentTimeMillis();
        for (Integer i = 1; i < quantity + 1; i++) {
            ps.setInt(1, i);
            ps.setString(2, "товар" + i);
            ps.setInt(3, i * 10);
            ps.executeUpdate();
            if (i % 50 == 0) {
                long time2 = System.currentTimeMillis();
                System.out.println("added " + i + " strings... time - " + (time2 - time1) + " ms");
            }
        }
        long time3 = System.currentTimeMillis();
        System.out.println("final time for " + quantity + " strings... - " + (time3 - time1) + " ms");*/

        StringBuilder sb = new StringBuilder("INSERT INTO products (prodid, title, cost) VALUES");
        for (Integer i = 1; i < quantity + 1; i++) {
            sb.append(" (");
            sb.append(i);
            sb.append(", 'товар");
            sb.append(i);
            sb.append("', ");
            sb.append(i * 10);
            sb.append("),"); // VALUES (1, товар1, 10), (2, товар2, 20), (3, товар3, 30), etc...
        }
        sb.deleteCharAt(sb.length() - 1);

        Statement updateStatement = connection.createStatement();
        long time1 = System.currentTimeMillis();
        updateStatement.execute(sb.toString());
        long time2 = System.currentTimeMillis();
        System.out.println("time of execute query for " + quantity + " strings - " + (time2 - time1) + " ms"); //~400ms
    }

    public static void getCommandFromConsole() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Type command: (/стоп - finishes the program");
            while (true) {
                String command = br.readLine();
                String[] parts = command.split("\\s+");
                if (command.equalsIgnoreCase("/стоп")) {
                    break;
                }
                if (command.startsWith("/цена ")) {
                    if (parts.length < 2) continue;
                    try {
                        System.out.println("Price of " + parts[1] + " is " + getPriceByTitle(parts[1]));
                    } catch (SQLException e) {
                        System.out.println("Такого товара нет");
                    }
                }
                if (command.startsWith("/сменитьцену ")) {
                    if (parts.length < 3) continue;
                    String title = parts[1];
                    String price = parts[2];
                    try {
                        changePriceOfGood(title, price);
                    } catch (SQLException e) {
                        System.out.println("No such good or some other exception");
                        e.printStackTrace();
                    }
                }
                if (command.startsWith("/товарыпоцене ")) {
                    if (parts.length < 3) continue;
                    String from = parts[1];
                    String to = parts[2];
                    try {
                        printResultSet(getGoodsBetween(from, to));
                    } catch (SQLException e) {
                        System.out.println("Wrong SQL query!");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getPriceByTitle(String title) throws SQLException {
        if (title == null) throw new SQLException();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("Select * from products where title like '" + title + "';");
        int price = resultSet.getInt(4);
        return price;
    }

    public static void changePriceOfGood(String title, String price) throws SQLException {
        if (title == null || price == null) throw new SQLException();
        try {
            Integer.parseInt(price);
        } catch (NumberFormatException e) {
            System.out.println("Wrong price!");
            return;
        }
        Statement statement = connection.createStatement();
        statement.execute("UPDATE products SET cost = '" + price + "' WHERE title = '" + title + "'");
        System.out.println("Price has changed");
    }

    public static ResultSet getGoodsBetween(String from, String to) throws SQLException {
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

    public static void printResultSet(ResultSet rs) throws SQLException {
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
