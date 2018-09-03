package ru.grigorev.javacore3.ThirdTask.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Dmitriy Grigorev
 */
public interface DAO {
    void insertStudent(String lastname, int points) throws SQLException;

    void insertStudent(int id, String lastname, int points) throws SQLException;

    void deleteStudentById(int id) throws SQLException;

    void updateStudentsPointsById(int id, int points) throws SQLException;

    ResultSet getStudentById(int id) throws SQLException;

    ResultSet getAllStudents() throws SQLException;

    void printResultSet(ResultSet resultSet) throws SQLException;

    int getLastId() throws SQLException;

    int getLastPoints() throws SQLException;

    String getStringFromResultSet(ResultSet resultSet) throws SQLException;
}
