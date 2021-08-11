import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javax.swing.JOptionPane.showMessageDialog;
import static spark.Spark.*;
//import static spark.debug.DebugScreen.enableDebugScreen;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
//        enableDebugScreen();

        get("/", ((request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("tasks", Task.all());
            return new ModelAndView(model, "index.hbs");
        }), new HandlebarsTemplateEngine());

        get("/login", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "login.hbs");
        }, new HandlebarsTemplateEngine());

        get("/signup", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "signup.hbs");
        }, new HandlebarsTemplateEngine());

        post("/login",((request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String email = request.queryParams("email");
            String password = request.queryParams("password");

            Planner plannerEmail = Planner.findByEmail(email);
            List<Planner> planners = Planner.all();
            if (planners.contains(plannerEmail)) {
                showMessageDialog(null, "Login");
            }
            return new ModelAndView(model, "login.hbs");
        }));

        post("/signup",((request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            String email = request.queryParams("email");
            String password = request.queryParams("password");

            Planner planner = new Planner(name, email, password);
            planner.save();
            return new ModelAndView(model, "signup.hbs");
        }));
    }
}
