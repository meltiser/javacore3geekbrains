package ru.grigorev.javacore3.ThirdTask.service;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Dmitriy Grigorev
 */
public interface DBInitInterface {
    Connection initialize();

    void createAndCopyTestTable() throws SQLException;

    void dropTestTable() throws SQLException;
}
