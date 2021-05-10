package services.DBService.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MessageDao {
    private  Connection connection;

    public MessageDao(Connection connection) {
        this.connection = connection;
    }

    public void addMessage(String message,String messageTag, String user_name) throws SQLException {
        String req = "insert into message (message, messageTag, user_name) values (?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(req);
        preparedStatement.setString(1,message);
        preparedStatement.setString(2,messageTag);
        preparedStatement.setString(3,user_name);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
}
