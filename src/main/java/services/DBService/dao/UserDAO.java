package services.DBService.dao;

import services.DBService.dataSets.UsersDataSet;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private final Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }


    public UsersDataSet getUser(String name, String pass) throws SQLException {
        String req = "select * from users where user_name=? AND user_pass=?";
        PreparedStatement preparedStatement = connection.prepareStatement(req);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, pass);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        UsersDataSet user = new UsersDataSet(resultSet.getLong(1),
                resultSet.getString(2),
                resultSet.getString(3));
        resultSet.close();
        preparedStatement.close();
        return user;

    }

    public List<UsersDataSet> getUsers() throws SQLException {
        List<UsersDataSet> usersDataSets = new ArrayList<>();
        String req = "select id, user_name, user_email from users";
        PreparedStatement preparedStatement = connection.prepareStatement(req);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            usersDataSets.add(new UsersDataSet(resultSet.getLong(1),
                    resultSet.getString(2),
                    resultSet.getString(3)));
        }
        resultSet.close();
        preparedStatement.close();
        return usersDataSets;
    }

    public void insertUser(String name, String pass, String email) throws SQLException {
        String req = "insert into users (user_name, user_pass, user_email) values (?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(req);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, pass);
        preparedStatement.setString(3, email);
        preparedStatement.executeUpdate();
        preparedStatement.close();

    }

    public void createTable() throws SQLException {
        String req = "create table if not exists users (id serial, user_name varchar(256), user_pass varchar(256), user_email varchar(256), primary key (id))";
        PreparedStatement preparedStatement = connection.prepareStatement(req);
        preparedStatement.executeUpdate();
        preparedStatement.close();

    }
}
