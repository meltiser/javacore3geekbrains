package ru.grigorev.javacore3;

import ru.grigorev.javacore3.DAO.DAO;
import ru.grigorev.javacore3.DAO.DAOimpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Dmitriy Grigorev
 */
public class JdbcMain {
    private static Connection connection;
    private static DAO dao;

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:myDb.db");
        dao = new DAOimpl(connection);

        try {
            dao.deleteTableIfExist();
            dao.createTableIfNotExists();
            dao.fillTableWithGoods(10_000);
            getCommandFromConsole();
        } finally {
            connection.close();
        }
    }

    public static void getCommandFromConsole() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Type command: (/стоп - finishes the program)");
            while (true) {
                String command = br.readLine();
                String[] parts = command.split("\\s+");
                if (command.equalsIgnoreCase("/стоп")) {
                    break;
                }
                if (command.startsWith("/цена ")) {
                    if (parts.length < 2) continue;
                    try {
                        System.out.println("Price of " + parts[1] + " is " + dao.getPriceByTitle(parts[1]));
                    } catch (SQLException e) {
                        System.out.println("Такого товара нет");
                    }
                }
                if (command.startsWith("/сменитьцену ")) {
                    if (parts.length < 3) continue;
                    String title = parts[1];
                    String price = parts[2];
                    try {
                        dao.changePriceOfGood(title, price);
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
                        ResultSet rs = dao.getGoodsBetween(from, to);
                        dao.printResultSet(rs);
                    } catch (SQLException e) {
                        System.out.println("Wrong SQL query!");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
