package services.DBService;

import services.DBService.dao.MessageDao;
import services.DBService.dao.UserDao;
import services.DBService.dataSets.MessageDataSet;
import services.DBService.dataSets.UsersDataSet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class DBService {
    private final Connection connection;
    private final UserDao userDAO;
    private final MessageDao messageDao;
    private String connectionUrl;
    private String login;
    private String pass;

    public DBService() {
        getProperties();
        this.connection = getConnection(connectionUrl, login, pass);
        this.userDAO = new UserDao(connection);
        this.messageDao = new MessageDao(connection);
    }

    private void getProperties() {
        try {
            Properties properties = new Properties();
            properties.load(DBService.class.getClassLoader().getResourceAsStream("postgres.properties"));
            connectionUrl = properties.getProperty("database.url");
            login = properties.getProperty("database.username");
            pass = properties.getProperty("database.password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addUser(String name, String pass, String email) throws DBException {
        try {
            connection.setAutoCommit(false);
            userDAO.createTable();
            userDAO.insertUser(name, pass, email);
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
            return userDAO.getUser(name, pass);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public List<UsersDataSet> getUsers() throws DBException {
        try {
            return userDAO.getUsers();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public void addMessage(String message, String messageTag, String login) throws DBException {
        try {
            connection.setAutoCommit(false);
            messageDao.addMessage(message, messageTag, login);
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
            return messageDao.getMessages();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public List<MessageDataSet> getMessagesByTag(String tag) throws DBException {
        try {
            return messageDao.getMessageByTag(tag);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public static Connection getConnection(String url, String loggin, String pass) {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            return DriverManager.getConnection(url, loggin, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
