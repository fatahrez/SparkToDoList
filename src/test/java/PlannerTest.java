import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.sql.Timestamp;

public class PlannerTest {
    @Rule
    public DatabaseRule databaseRule = new DatabaseRule();
    Planner planner = new Planner("planner1", "planner1@email.com", "plannerpassword");
    Planner planner2 = new Planner("planner2", "planner2@email.com", "plannerpassword");

    @Test
    public void planner_InstantiatesCorrectly_true() {
        Assert.assertTrue(planner instanceof Planner);
    }

    @Test
    public void planner_savesNewPlannerToDB_true() {
        planner.save();
        Assert.assertEquals(Planner.all().get(0).getName(), "planner1");
    }

    @Test
    public void planner_savesAnotherPlannerToDB_true() {
        planner.save();
        planner2.save();
        Assert.assertEquals(Planner.all().get(1).getName(), "planner2");
    }

    @Test
    public void planner_updatePlannerData_name() {
        planner.save();
        planner.update("John Doe", planner.getEmail(), planner.getPassword());
        Assert.assertEquals("John Doe", Planner.all().get(0).getName());
    }

    @Test
    public void planner_deletePlanner_null() {
        planner.save();
        planner.delete();
        Assert.assertEquals(0, Planner.all().size());
    }

    @Test
    public void planner_getAllTasksByAPlanner_tasks() {
        planner.save();
        planner2.save();

        Task task = new Task("Task1", "Task1 Description", Timestamp.valueOf("2021-08-10 00:12:10"), planner.getId());
        Task task2 = new Task("Task2", "Task2 Description", Timestamp.valueOf("2021-08-10 00:12:10"), planner.getId());

        Task task3 = new Task("Task3", "Task2 Description", Timestamp.valueOf("2021-08-10 00:12:10"), planner2.getId());

        task.save();
        task2.save();
        task3.save();
        Assert.assertEquals(2, planner.getTasks().size());
        Assert.assertEquals(1, planner2.getTasks().size());
    }

}
