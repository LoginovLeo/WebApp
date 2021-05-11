package services.DBService;

import services.DBService.dao.MessageDao;
import services.DBService.dao.UserDAO;
import services.DBService.dataSets.MessageDataSet;
import services.DBService.dataSets.UsersDataSet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class DBService {

    static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/simpleWS";
    static final String LOGIN = "postgres";
    static final String PASS = "1111";

    private final Connection connection;

    public DBService() {
        this.connection = getConnection();
    }

    public void addUser(String name, String pass, String email) throws DBException {
        try {
            connection.setAutoCommit(false);
            UserDAO dao = new UserDAO(connection);
            dao.createTable();
            dao.insertUser(name, pass, email);
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

    public UsersDataSet getUser(String name, String pass) throws DBException {
        try {
            return (new UserDAO(connection).getUser(name, pass));
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public List<UsersDataSet> getUsers() throws DBException {
        try {
            return (new UserDAO(connection).getUsers());
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public void addMessage(String message, String messageTag, String login) throws DBException {
        try {
            connection.setAutoCommit(false);
            MessageDao messageSet = new MessageDao(connection);
            messageSet.addMessage(message, messageTag, login);
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

    public List<MessageDataSet> getMessages() throws DBException {
        try {
            return (new MessageDao(connection).getMessages());
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public List<MessageDataSet> getMessagesByTag(String tag) throws DBException {
        try {
            return (new MessageDao(connection).getMessageByTag(tag));
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public static Connection getConnection() {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            return DriverManager.getConnection(DB_URL, LOGIN, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
