package services.DBService.dataSets;

public class UsersDataSet {
    private long id;
    private String name;

    public UsersDataSet(long id, String name, String pass) {
        this.id = id;
        this.name = name;
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