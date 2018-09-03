package ru.grigorev.javacore3;

import org.junit.*;
import org.sqlite.SQLiteException;
import ru.grigorev.javacore3.ThirdTask.dao.DAO;
import ru.grigorev.javacore3.ThirdTask.dao.DAOimpl;
import ru.grigorev.javacore3.ThirdTask.service.DBInitImpl;
import ru.grigorev.javacore3.ThirdTask.service.DBInitInterface;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Dmitriy Grigorev
 */
public class ThirdTaskMainTest {
    private static Connection connection;
    private static DAO dao;
    private static final String TEST_TABLE = "students_test";
    private static DBInitInterface dbInitialization;

    @BeforeClass
    public static void initConnection() {
        dbInitialization = new DBInitImpl();
        connection = dbInitialization.initialize();
        try {
            dbInitialization.createAndCopyTestTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dao = new DAOimpl(connection, TEST_TABLE);
    }

    @AfterClass
    public static void closeConnection() {
        try {
            connection.close();
            connection = dbInitialization.initialize();
            dbInitialization.dropTestTable();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void insertStudent() {
        try {
            dao.insertStudent("petrov", 22);
            int lastId = dao.getLastId();
            String result = dao.getStringFromResultSet(dao.getStudentById(lastId));
            String expected = "id: " + lastId + " lastname: petrov points: 22";
            Assert.assertEquals(result, expected);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteStudentById() {
        try {
            int lastId = dao.getLastId();
            dao.deleteStudentById(lastId);
            int expected = dao.getLastId();
            Assert.assertNotEquals(expected, lastId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateStudentsPointsById() {
        try {
            int lastId = dao.getLastId();
            dao.updateStudentsPointsById(lastId, 99);
            int expected = dao.getLastPoints();
            Assert.assertEquals(expected,99);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = SQLiteException.class)
    public void printResultSet() throws SQLException {
        dao.insertStudent(1, "Petrov", 70);
    }
}
