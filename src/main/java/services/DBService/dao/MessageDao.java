package services.DBService.dao;

import services.DBService.dataSets.MessageDataSet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageDao {
    private final Connection connection;

    public MessageDao(Connection connection) {
        this.connection = connection;
    }

    public void addMessage(String message, String messageTag, String userLogin) throws SQLException {
        String req = "insert into messages (message, messageTag, user_login) values (?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(req);
        preparedStatement.setString(1, message);
        preparedStatement.setString(2, messageTag);
        preparedStatement.setString(3, userLogin);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public List<MessageDataSet> getMessages() throws SQLException {
        List<MessageDataSet> messagesDataSet = new ArrayList<>();
        String req = "select *  from messages";
        PreparedStatement preparedStatement = connection.prepareStatement(req);
        return getMessageDataSets(messagesDataSet, preparedStatement);
    }

    public List<MessageDataSet> getMessageByTag(String tag) throws SQLException {
        List<MessageDataSet> messagesDataSet = new ArrayList<>();
        String req = "select *  from messages where messagetag = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(req);
        preparedStatement.setString(1,tag);
        return getMessageDataSets(messagesDataSet, preparedStatement);
    }

    private List<MessageDataSet> getMessageDataSets(List<MessageDataSet> messagesDataSet, PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            messagesDataSet.add(new MessageDataSet(resultSet.getInt(1),
                    resultSet.getString(4),
                    resultSet.getString(2),
                    resultSet.getString(3)));
        }
        resultSet.close();
        preparedStatement.close();
        return messagesDataSet;
    }

    public void createTable() throws SQLException {
        String req = "create table if not exists messages (id serial, message varchar(256), messageTag varchar(256), user_login varchar(256), primary key (id))";
        PreparedStatement preparedStatement = connection.prepareStatement(req);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
}
