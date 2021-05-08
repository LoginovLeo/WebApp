package services.DBService;

import services.DBService.dao.UserDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBService {

    static final String DB_URL ="jdbc:postgresql://127.0.0.1:5432/simpleWS";
    static final String LOGIN = "postgres";
    static final String PASS = "1111";

    private final Connection connection;

    public DBService() {
        this.connection = getConnection();
    }

    public void addUser(String name,String pass,String email) throws DBException {
        try {
            connection.setAutoCommit(false);
            UserDAO dao = new UserDAO(connection);
            dao.createTable();
            dao.insertUser(name,pass,email);
            connection.commit();

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {
            }
            throw new DBException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ignore) {
            }
        }
    }

    public static Connection getConnection() {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            return DriverManager.getConnection(DB_URL,LOGIN,PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
