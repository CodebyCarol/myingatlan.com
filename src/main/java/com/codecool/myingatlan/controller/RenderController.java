package com.codecool.meetup.triangels.controller;

import com.codecool.meetup.triangels.services.*;
import main.java.com.codecool.myingatlan.services.RealEstateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@Controller
public class RenderController {

    @Autowired
    private RealEstateService realEstateService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String renderIndexPage(Model model, HttpSession session) {
        //TODO
        model.addAttribute("isLogged", session.getAttribute("name")); // ha ez true, van session, ha nem, akkor nincs -
        model.addAttribute("list", true);
        model.addAttribute("realestatelist", RealEstateService.getAll());
        return "index";
    }

    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public String renderEvents(Model model, HttpSession session) {
        //TODO
        model.addAttribute("isLogged", session.getAttribute("name"));
        if (session.getAttribute("name") != null) {
            model.addAttribute("userInGroups", userService.getGroupsWhichUserIn((String) session.
                    getAttribute("name")));
        }
        model.addAttribute("list",true);
        model.addAttribute("list", true);
        model.addAttribute("events", true);
        model.addAttribute("eventlist", eventService.sortByMostRecent());

        return "index";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String renderUsers(Model model, HttpSession session) {
        //TODO
        model.addAttribute("isLogged", session.getAttribute("name"));
        model.addAttribute("list", true);
        model.addAttribute("user", true);
        model.addAttribute("users", userService.getAll());

        return "index";
    }



    @RequestMapping(value = "/groups", method = RequestMethod.GET)
    public String renderGroups(Model model, HttpSession session) {
        //TODO
        model.addAttribute("isLogged", session.getAttribute("name"));
        model.addAttribute("list", true);
        model.addAttribute("groups", true);
        model.addAttribute("groups", groupService.getAll());

        return "index";
    }



    @RequestMapping(value = "sign-up", method = RequestMethod.GET)
    public String renderSignUp(Model model) {
        model.addAttribute("signup", true);
        return "index";
    }

//TODO
    @RequestMapping(value = "/sign-up", method = RequestMethod.POST)
    public String signUp(@RequestParam("email") String email,
                         @RequestParam("password") String password,
                         @RequestParam("intro") String intro,
                         @RequestParam("name") String name,
                         Model model) {
        Boolean isSuccess = userService.ifCreateUser(name,
                email,password,intro);
        if (isSuccess) {
            groupService.joinToGroup(userService.getUserByName(name),
                    1L); // JOIN TO EVERYONES GROUP
            return "redirect:login";
        }
        model.addAttribute("signUpResult", true);
        model.addAttribute("isSuccess", isSuccess);
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login( HttpSession session,
                        @RequestParam("name") String name,
                        @RequestParam("password") String password) {
        if (userService.canLogIn(name, password)) {
            session.setAttribute("name", name);
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.removeAttribute("name");
        return "redirect:/";
    }


    @RequestMapping(value = "/group/{id}", method = RequestMethod.GET)
    public String renderGroup(@PathVariable("id") Long id, Model model, HttpSession session) {
        model.addAttribute("isSingleItem", true);
        //TODO
        model.addAttribute("isLogged", session.getAttribute("name"));
        if (session.getAttribute("name") != null) {
            model.addAttribute("canJoin", groupService.canJoin(
                    userService.getUserByName((String) session.getAttribute("name")),
                    groupService.getGroupById(id)));
        } else {
            model.addAttribute("canJoin", false);
        }
        model.addAttribute("group", groupService.getGroupById(
                id));
        model.addAttribute("usersInGroup", userService.getUsersByGroup(id));
        return "index";

    }

    @RequestMapping(value = "/event/{id}", method = RequestMethod.GET)
    public String renderEvent(@PathVariable("id") Long id, Model model, HttpSession session) {
        Event event = eventService.getEventById(id);
        //TODO
        model.addAttribute("isSingleItem", true);
        model.addAttribute("isLogged", session.getAttribute("name"));
        if (session.getAttribute("name") != null) {
            model.addAttribute("canJoinByGroup",eventService.canJoinByGroup(
                    userService.getUserByName((String) session.getAttribute("name")),
                    eventService.getEventById(id)));
            model.addAttribute("ifUserInEvent", eventService.ifUserInEvent(
                    userService.getUserByName((String) session.getAttribute("name")),
                    eventService.getEventById(id)));
        }
        model.addAttribute("comments", event.getComments());
        model.addAttribute("event", event);
        model.addAttribute("userInEvent", userService.getUsersByEvent(id));
        model.addAttribute("remainingTime", eventService.getRemainingTime(event));

        return "index";
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public String renderUser(@PathVariable("id") String id, Model model, HttpSession session) {
        model.addAttribute("isSingleItem", true);
        //TODO
        model.addAttribute("isLogged", session.getAttribute("name"));
        model.addAttribute("user", userService.getUserById(
                Long.parseLong(id)));
        return "index";
    }

    @RequestMapping(value = "/join/group/{id}", method = RequestMethod.POST)
    public String joinToGroup(@PathVariable("id") Long id, HttpSession session) {
        //TODO
        groupService.joinToGroup(userService.getUserByName(
                (String) session.getAttribute("name")), id);
        return String.format("redirect:/group/%s", id);
    }

    @RequestMapping(value = "/join/event/{id}", method = RequestMethod.POST)
    public String joinToEvent(@PathVariable("id") Long id, HttpSession session) {
        //TODO
        eventService.joinToEvent(userService.getUserByName(
                (String) session.getAttribute("name")),id);
        return String.format("redirect:/event/%s", id);
    }

    @RequestMapping(value = "/create/event", method = RequestMethod.POST)
    public String createEvent(@RequestParam("name") String name,
                              @RequestParam("descr") String descr,
                              @RequestParam("place") String place,
                              @RequestParam("time") String time,
                              @RequestParam("groupEvent") String groupEvent) {
        eventService.createEvent(
                name,descr,place,time,
                groupService.getGroupById(Long.parseLong(groupEvent)));
        return "redirect:events";
    }

    @RequestMapping(value = "/create/group", method = RequestMethod.POST)
    public String createGroup(@RequestParam("name") String name,
                              @RequestParam("descr") String descr) {
        groupService.createGroup(
                name, descr,
                userService.getUserByName(name));
        return "redirect: groups";
    }

    @RequestMapping(value = "/create/comment/{id}", method = RequestMethod.POST)
    public String createComment(@PathVariable("id") Long id,
                                @RequestParam("comment") String comment,
                                HttpSession session) {
        //TODO
        commentService.createComment(
                userService.getUserByName((String) session.getAttribute("name")),
                comment,
                eventService.getEventById(id));
        eventService.addCommentToEvent(commentService.getLastComment(),id);

        return String.format("redirect:/event/%s", id);
    }

    
}

