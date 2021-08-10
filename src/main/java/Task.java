import org.sql2o.Connection;

import java.sql.Timestamp;
import java.util.List;

public class Task {
    private int id;
    private String title;
    private String body;
    private Timestamp completion;
    private int planner_id;


    public Task(String title, String body, Timestamp completion, int planner_id) {
        this.title = title;
        this.body = body;
        this.completion = completion;
        this.planner_id = planner_id;
    }

    public void save() {
        try (Connection connection = DB.sql2o.open()){
            String sql = "INSERT INTO tasks(title, body, completion, planner_id) VALUES (:title, :body, now(), :planner_id);";
            this.id = (int) connection.createQuery(sql, true)
                    .addParameter("title", this.title)
                    .addParameter("body", this.body)
                    .addParameter("planner_id", this.planner_id)
                    .executeUpdate()
                    .getKey();
        }
    }
    public static List<Task> all() {
        String sql = "SELECT * FROM tasks;";
        try(Connection connection = DB.sql2o.open()) {
            return connection.createQuery(sql).executeAndFetch(Task.class);
        }
    }

    public String getBody() {
        return body;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Timestamp getCompletion() {
        return completion;
    }

    public void setCompletion(Timestamp completion) {
        this.completion = completion;
    }

    public int getPlanner_id() {
        return planner_id;
    }

    public void setPlanner_id(int planner_id) {
        this.planner_id = planner_id;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
