package services.DBService.dao;

import services.DBService.dataSets.UsersDataSet;
import services.DBService.executor.Executor;

import java.sql.Connection;
import java.sql.SQLException;

public class UserDAO {
    private Executor executor;

    public UserDAO(Connection connection) {
        this.executor = new Executor(connection);
    }

    public UsersDataSet get(String name) throws SQLException {
        return executor.execQuery("select * from users where user_name='" + name + "'", result -> {
            result.next();
            return new UsersDataSet(result.getInt(1), result.getString(2));
        });
    }

    public long getUserId(String name) throws SQLException {
        return executor.execQuery("select * from users where user_name='" + name + "'", result -> {
            result.next();
            return result.getLong(1);
        });
    }

    public void insertUser( String name, String pass, String email) throws SQLException {
        executor.execUpdate("insert into users (user_name, user_pass, user_email) values ('" + name + "', '" + pass + "', '" + email + "')");
    }

    public void createTable() throws SQLException {
        executor.execUpdate("create table if not exists users (id serial, user_name varchar(256), user_pass varchar(256), user_email varchar(256), primary key (id))");
    }
}
