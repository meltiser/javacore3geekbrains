package ru.grigorev.javacore3.ThirdTask.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Dmitriy Grigorev
 */
public class DBInitImpl implements DBInitInterface {
    private static Connection connection;

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to find JDBC driver", e);
        }
    }

    @Override
    public Connection initialize() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:myDb.db");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create connection to DB", e);
        }
        return connection;
    }

    @Override
    public void createAndCopyTestTable() throws SQLException {
        dropTestTable();
        Statement createStatement = connection.createStatement();
        createStatement.execute(
                "CREATE TABLE IF NOT EXISTS students_test (\n" +
                        "    id       INTEGER PRIMARY KEY AUTOINCREMENT\n" +
                        "                     DEFAULT (1) \n" +
                        "                     NOT NULL,\n" +
                        "    lastname TEXT    NOT NULL,\n" +
                        "    points   INTEGER NOT NULL\n" +
                        ");");
        createStatement.close();
        Statement copyStatement = connection.createStatement();
        copyStatement.execute("INSERT INTO students_test\n" +
                "SELECT * FROM students");
        copyStatement.close();
    }

    @Override
    public void dropTestTable() throws SQLException {
        Statement deleteStatement = connection.createStatement();
        deleteStatement.execute("DROP TABLE IF EXISTS students_test;");
        deleteStatement.close();
    }
}
