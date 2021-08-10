import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

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


}
