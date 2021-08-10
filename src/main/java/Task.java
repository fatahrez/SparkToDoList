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
            String sql = "INSERT INTO tasks(title, body, completion, planner_id) VALUES (:title, :body, :completion , :planner_id);";
            this.id = (int) connection.createQuery(sql, true)
                    .addParameter("title", this.title)
                    .addParameter("body", this.body)
                    .addParameter("planner_id", this.planner_id)
                    .addParameter("completion", this.completion)
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

    public void update(String title, String body, Timestamp completion, int planner_id) {
        try(Connection connection = DB.sql2o.open()) {
            String sql = "UPDATE tasks SET title=:title, body=:body, completion:completion, planner_id=:planner_id WHERE id=:id;";
            connection.createQuery(sql)
                    .addParameter("title", title)
                    .addParameter("body", body)
                    .addParameter("completion", completion)
                    .addParameter("planner_id", planner_id)
                    .addParameter("id", this.id)
                    .executeUpdate();
        }
    }

    public void delete() {
        try(Connection connection = DB.sql2o.open()) {
            String sql = "DELETE FROM tasks WHERE id:id;";
            connection.createQuery(sql)
                    .addParameter("id", this.id)
                    .executeUpdate();
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
