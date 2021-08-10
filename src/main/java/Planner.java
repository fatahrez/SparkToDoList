import org.sql2o.Connection;

import java.util.List;

public class Planner {
    private int id;
    private String name;
    private String email;
    private String password;

    public Planner(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public void save() {
        try (Connection connection = DB.sql2o.open()){
            String sql = "INSERT INTO planner(name, email, password) VALUES (:name, :email, :password);";
            this.id = (int) connection.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("email", this.email)
                    .addParameter("password", this.password)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static List<Planner> all() {
        String sql = "SELECT * FROM planner;";
        try(Connection connection = DB.sql2o.open()) {
            return connection.createQuery(sql).executeAndFetch(Planner.class);
        }
    }

//    public Planner find(int id) {
//
//    }

    public void update() {

    }

    public void delete() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
