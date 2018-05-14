package com.codecool.myingatlan;


import com.codecool.myingatlan.repository.RealEstateRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.codecool.myingatlan.services.RealEstateService;
import com.codecool.myingatlan.repository.RealEstateRepo;
import com.codecool.myingatlan.controller.RenderController;

import com.codecool.myingatlan.PopDB;

import static spark.Spark.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;



@SpringBootApplication
public class MyIngatlan {


    public static void main(String[] args) {


        // setting up instances
        RealEstateService realEstateService = new RealEstateService();
        RenderController renderController = new RenderController();


        SpringApplication.run(MyIngatlan.class, args);
        PopDB populated = new PopDB();


    }
}




//public class Server {
//    private static final Logger logger = LoggerFactory.getLogger(Server.class);
//
//    public static void main(String[] args) throws IllegalArgumentException {
//
//       PopDB popDB = new PopDB(entityManager);
//       popDB.populateDB();
//        ///////////////////////////////////////////////////////////////////////////////
//
//        before("/join/*", (request, response) -> {
//            if (request.session().attribute("name") == null) {
//                halt(401, "nono, senior hacker");
//            }
//        });
//
//        before("/create/*", (request, response) -> {
//            if (request.session().attribute("name") == null) {
//                halt(401, "nono, senior hacker");
//            }
//        });
//
//        get("/", renderController:: renderIndexPage, new ThymeleafTemplateEngine());
//
//        get("/events", renderController::renderEvents, new ThymeleafTemplateEngine());
//
//        get("/users", renderController::renderUsers, new ThymeleafTemplateEngine());
//
//        get("/groups", renderController::renderGroups, new ThymeleafTemplateEngine());
//
//        get("/sign-up", renderController::renderSignUp, new ThymeleafTemplateEngine());
//
//        post("/sign-up", renderController :: signUp, new ThymeleafTemplateEngine());
//
//        post("/login", renderController :: login, new ThymeleafTemplateEngine());
//
//        get("/logout", renderController :: logout, new ThymeleafTemplateEngine());
//
//        get("/group/:id", renderController :: renderGroup, new ThymeleafTemplateEngine());
//
//        get("/event/:id", renderController :: renderEvent, new ThymeleafTemplateEngine());
//
//        get("/user/:id", renderController :: renderUser, new ThymeleafTemplateEngine());
//
//        post("/join/group/:id", renderController :: joinToGroup, new ThymeleafTemplateEngine());
//
//        post ("/join/event/:id", renderController :: joinToEvent, new ThymeleafTemplateEngine());
//
//        post("create/event", renderController :: createEvent, new ThymeleafTemplateEngine());
//
//        post("create/group", renderController :: createGroup, new ThymeleafTemplateEngine());
//
//        post("/create/comment/:id", renderController :: createComment, new ThymeleafTemplateEngine());
//    }
//}
