package ru.grigorev.javacore3.ThirdTask.dao;

import ru.grigorev.javacore3.ThirdTask.dao.DAO;

import java.sql.*;

/**
 * @author Dmitriy Grigorev
 */
public class DAOimpl implements DAO {
    private Connection connection;
    private String table;

    public DAOimpl(Connection connection, String table) {
        this.connection = connection;
        this.table = table;
    }

    @Override
    public void insertStudent(String lastname, int points) throws SQLException {
        String sql = "INSERT INTO " + table + " (lastname, points) VALUES (?, ?);";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, lastname);
        ps.setInt(2, points);
        ps.execute();
        ps.close();
    }

    @Override
    public void insertStudent(int id, String lastname, int points) throws SQLException {
        String sql = "INSERT INTO " + table + " (id, lastname, points) VALUES (?, ?, ?);";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ps.setString(2, lastname);
        ps.setInt(3, points);
        ps.execute();
        /*try {
            ps.execute();
        } catch (SQLiteException e) {
            System.err.println("Student with such id is already exists");
        }*/
        ps.close();
    }

    @Override
    public void deleteStudentById(int id) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("DELETE FROM " + table + " WHERE id = \'" + id + "\';");
        statement.close();
    }

    @Override
    public void updateStudentsPointsById(int id, int points) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("UPDATE " + table + " SET points = '" + points + "' WHERE id = '" + id + "'");
        statement.close();
    }

    @Override
    public ResultSet getStudentById(int id) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(
                "SELECT * FROM " + table + " WHERE id = " + id + ";");
        return resultSet;
    }

    @Override
    public ResultSet getAllStudents() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM " + table + ";");
        return resultSet;
    }

    @Override
    public void printResultSet(ResultSet rs) throws SQLException {
        if (rs == null) throw new SQLException();
        while (rs.next()) {
            int id = rs.getInt(1);
            String lastname = rs.getString(2);
            int points = rs.getInt(3);
            System.out.println("id: " + id + " lastname: " + lastname + " points: " + points);
        }
    }

    @Override
    public String getStringFromResultSet(ResultSet resultSet) throws SQLException {
        if (resultSet == null) throw new SQLException();
        int id = resultSet.getInt(1);
        String lastname = resultSet.getString(2);
        int points = resultSet.getInt(3);
        return "id: " + id + " lastname: " + lastname + " points: " + points;
    }

    @Override
    public int getLastId() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT id FROM " + table + " ORDER BY id DESC LIMIT 1");
        return resultSet.getInt(1);
    }

    @Override
    public int getLastPoints() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT points FROM " + table + " ORDER BY id DESC LIMIT 1");
        return resultSet.getInt(1);
    }
}
