import org.junit.*;
import org.sql2o.Connection;

import java.sql.Timestamp;
import java.util.Date;

public class TaskTest {
    @Rule
    public DatabaseRule databaseRule = new DatabaseRule();

//    @After
//    public void tearDown() {
//        try(Connection con= DB.sql2o.open()) {
//            String deleteTaskQuery = "DELETE FROM tasks *";
//            con.createQuery(deleteTaskQuery).executeUpdate();
//        }
//    }

    Task task = new Task("Task1", "Task1 Description", Timestamp.valueOf("2021-08-10 00:12:10"), 1);
    Task task2 = new Task("Task2", "Task2 Description", Timestamp.valueOf("2021-08-10 00:12:10"), 1);

    @Test
    public void task_CorrectlyInstantiates_true() {
        Assert.assertTrue(task instanceof Task);
    }

    @Test
    public void task_SavedInDBCorrectly_true() {
        task.save();
        Assert.assertEquals("Task1", Task.all().get(0).getTitle());
    }

    @Test
    public void task_SavedAnotherInDBCorrectly_true() {
        task.save();
        task2.save();
        Assert.assertEquals("Task2", Task.all().get(1).getTitle());
    }
}
