package services.users;

import services.DBService.DBException;
import services.DBService.DBService;
import services.DBService.dataSets.UsersDataSet;

import java.util.HashMap;
import java.util.Map;

public class UserService {
    private final Map<String, UserProfile> loginToProfile;
    private final Map<String, UserProfile> sessionIdToProfile;
    DBService dbService = new DBService();

    public UserService() {
        loginToProfile = new HashMap<>();
        sessionIdToProfile = new HashMap<>();

    }

    public void addNewUser(UserProfile userProfile) throws DBException {
        dbService.addUser(userProfile.getLogin(),userProfile.getPass(), userProfile.getEmail());
        loginToProfile.put(userProfile.getLogin(), userProfile);
    }




    public UserProfile getUserBySessionId(String sessionId) {
        return sessionIdToProfile.get(sessionId);
    }

    public void addSession(String sessionId, UserProfile userProfile) {
        sessionIdToProfile.put(sessionId, userProfile);
    }

    public void deleteSession(String sessionId) {
        sessionIdToProfile.remove(sessionId);
    }
}
