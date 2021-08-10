import org.junit.rules.ExternalResource;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class DatabaseRule extends ExternalResource {
    @Override
    protected void before() throws Throwable {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/todo_list_test", "abdulfatahmohamed",
                "3285abdi");
    }

    @Override
    protected void after() {
        try(Connection con= DB.sql2o.open()) {
            String deletePlannerQuery = "DELETE FROM planner *";
            String deleteTaskQuery = "DELETE FROM tasks *";
            con.createQuery(deletePlannerQuery).executeUpdate();
            con.createQuery(deleteTaskQuery).executeUpdate();
        }
    }


}
