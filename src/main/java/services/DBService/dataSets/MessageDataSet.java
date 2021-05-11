package services.DBService.dataSets;

public class MessageDataSet {
    private final int id;
    private final String userLogin;
    private final String message;
    private final String messageTag;

    public MessageDataSet(int id, String userLogin, String message, String messageTag) {
        this.id = id;
        this.userLogin = userLogin;
        this.message = message;
        this.messageTag = messageTag;
    }

    public int getId() {
        return id;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public String getMessage() {
        return message;
    }

    public String getMessageTag() {
        return messageTag;
    }

    @Override
    public String toString() {
        return "MessageDataSet{" +
                "id=" + id +
                ", userLogin='" + userLogin + '\'' +
                ", message='" + message + '\'' +
                ", messageTag='" + messageTag + '\'' +
                '}';
    }
}
