package com.codecool.meetup.triangels.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.temporal.ChronoUnit;
import java.util.*;

import static java.time.temporal.ChronoUnit.DAYS;

import java.util.List;


@Service
public class EventService {

    @Autowired
    private EventRepo eventRepo;
    @Autowired
    private UserRepo userRepo;

    public List<Event> getAll() {
        return eventRepo.findAll();
    }

    public Event getEventById(long id) {
        return eventRepo.findFirstById(id);
    }

    public void createEvent(String name, String descr, String place, String time, Group group) {
        Event event = new Event(name, descr, place, time);
        event.addGroupToEvent(group);
        eventRepo.save(event);
    }

    public void joinToEvent(User user, Long eventId) {
        Event event = getEventById(eventId);
        event.addUserToEvent(user);
        eventRepo.save(event);
        userRepo.save(user);
    }

    public void addCommentToEvent(Comment comment, Long eventId) {
        Event event = getEventById(eventId);
        event.addComment(comment);
        eventRepo.save(event);
    }

    public boolean canJoinByGroup(User user, Event event) {
        return user.getInGroups().contains(event.getGroup());
    }

    public boolean ifUserInEvent(User user, Event event) {
        return event.getUsers().contains(user);
    }

    public List<Event> isUpcoming() {
        List<Event> upcoming = new ArrayList<>();
        List<Event> all = getAll();
        for (Event event: all) {
            if (event.checkEventTime()) {
                upcoming.add(event);
            }
        }
        return upcoming;
    }

    public HashMap<String, String> getRemainingTime(Event event) {
        HashMap<String, String> timedistance = new HashMap<>();
        Calendar current= Calendar.getInstance();
        Calendar eventTime = event.getCalendarTime(event.getTime());
        Long days = Math.abs(DAYS.between(current.toInstant(), eventTime.toInstant()));
        Long hours = Math.abs(ChronoUnit.HOURS.between(eventTime.toInstant(), current.toInstant()));
        timedistance.put("days", Long.toString(days));
        timedistance.put("hours", Long.toString(hours-(24*days)));
        timedistance.put("minutes", Long.toString(Math.abs(ChronoUnit.MINUTES.between(eventTime.toInstant(), current.toInstant())) - (60*hours)));
        return timedistance;
    }

    public List<Event> sortByMostRecent() {
        List<Event> upcoming = isUpcoming();
        Collections.sort(upcoming, new Comparator<Event>() {
                    @Override
                    public int compare(Event o1, Event o2) {
                        if (o1.getCalendarTime(o1.getTime()).before(o2.getCalendarTime(o2.getTime()))) {
                            return -1;
                        } else {
                            return 1;
                        }
                    }
                }
        );
        return upcoming;

    }
}
