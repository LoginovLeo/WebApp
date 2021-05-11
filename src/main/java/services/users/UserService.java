package services.users;

import services.DBService.DBException;
import services.DBService.DBService;
import services.DBService.dataSets.MessageDataSet;
import services.DBService.dataSets.UsersDataSet;

import java.util.List;


public class UserService {

    private final DBService dbService = new DBService();

    public void addNewUser(UserProfile userProfile) throws DBException {
        dbService.addUser(userProfile.getLogin(), userProfile.getPass(), userProfile.getEmail());

    }

    public UserProfile getUserByLoginPass(String login, String pass) throws DBException {
        UsersDataSet user = dbService.getUser(login, pass);
        String name = user.getName();
        return new UserProfile(name);
    }

    public List<UsersDataSet> getUsers() throws DBException {
        return dbService.getUsers();
    }

    public void addMessage(String message, String messageTag, String login) throws DBException {
        dbService.addMessage(message, messageTag, login);
    }

    public List<MessageDataSet> getMessages() throws DBException {
        return dbService.getMessages();
    }

    public List<MessageDataSet> getMessagesByTag(String tag) throws DBException {
        return dbService.getMessagesByTag(tag);
    }
}