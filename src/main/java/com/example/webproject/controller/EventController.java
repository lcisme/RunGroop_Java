package com.example.webproject.controller;

import com.example.webproject.dto.EventDTO;
import com.example.webproject.entity.Event;
import com.example.webproject.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/events")
public class EventController {
    private EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }
    //getAll
    @GetMapping
    public String eventList(Model model){
     List<EventDTO> events = eventService.findAllEvents();
     model.addAttribute("events", events);
     return "event/events-list";
    }
    //viewById
    @GetMapping("/{eventId}")
    public String viewEvent(@PathVariable("eventId") Long eventId, Model model){
        EventDTO eventDTO = eventService.findByEventId(eventId);
        model.addAttribute("event", eventDTO);
        return "event/events-detail";
    }
    //update
    @GetMapping("/{eventId}/edit")
    public String editEventForm(@PathVariable("eventId") Long eventId, Model model){
        EventDTO event = eventService.findByEventId(eventId);
        model.addAttribute("event", event);
        return "event/events-edit";
    }
    @PostMapping("{eventId}/edit")
    public String updateEvent(@PathVariable("eventId") Long eventId,
                              @Valid @ModelAttribute("event") EventDTO event,
                              BindingResult result,Model model){
        if (result.hasErrors()){
            model.addAttribute("event", event);
            return "event/events-edit";
        }
        EventDTO eventDTO = eventService.findByEventId(eventId);
        event.setId(eventId);
        event.setClub(eventDTO.getClub());
        eventService.updateEvent(event);
        return "redirect:/events";
    }

    //create
    @GetMapping("/{clubId}/new")
    public String createEventForm(@PathVariable("clubId") Long clubId, Model model){
        Event event = new Event();
        model.addAttribute("clubId", clubId);
        model.addAttribute("event", event);
        return "event/events-create";
    }

    @PostMapping("/{clubId}")
    public String createEvent(@PathVariable("clubId") Long clubId,
                              @ModelAttribute("event")EventDTO event,
                              BindingResult result,Model model){
        if (result.hasErrors()){
            model.addAttribute("event", event);
            return "club/clubs-create";
        }
        eventService.createEvent(clubId, event);
        return "redirect:/clubs/" + clubId;
    }

    //delete
    @GetMapping("/{eventId}/delete")
    public String deleteEvent(@PathVariable("eventId") Long eventId){
        eventService.deleteEvent(eventId);
        return "redirect:/events";
    }
}
