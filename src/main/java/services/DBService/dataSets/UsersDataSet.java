package services.DBService.dataSets;

public class UsersDataSet {
    private final long id;
    private final String name;
    private final String email;

    public UsersDataSet(long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;

    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "UsersDataSet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}