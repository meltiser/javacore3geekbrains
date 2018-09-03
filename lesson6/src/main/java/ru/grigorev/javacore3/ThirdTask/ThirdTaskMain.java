package ru.grigorev.javacore3.ThirdTask;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.grigorev.javacore3.ThirdTask.dao.DAO;
import ru.grigorev.javacore3.ThirdTask.dao.DAOimpl;
import ru.grigorev.javacore3.ThirdTask.service.DBInitImpl;
import ru.grigorev.javacore3.ThirdTask.service.DBInitInterface;

import java.sql.Connection;
import java.sql.SQLException;


/**
 * @author Dmitriy Grigorev
 */
public class ThirdTaskMain {
    private static final Logger LOGGER = LogManager.getLogger(ThirdTaskMain.class);
    private static Connection connection;
    private static DAO dao;
    private static DBInitInterface dbInitImpl;

    static {
        dbInitImpl = new DBInitImpl();
        connection = dbInitImpl.initialize();
        dao = new DAOimpl(connection, "students");
    }

    public static void main(String[] args) {
        try {
            LOGGER.debug("DEBUG");
            dbInitImpl.createAndCopyTestTable();
            LOGGER.info("Test table created");
            dbInitImpl.dropTestTable();
            LOGGER.info("Test table dropped");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
