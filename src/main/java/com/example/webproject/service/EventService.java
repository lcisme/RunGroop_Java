package com.example.webproject.service;

import com.example.webproject.dto.EventDTO;
import com.example.webproject.entity.Event;

import java.util.List;

public interface EventService {
    void createEvent(Long clubId, EventDTO eventDTO);

    List<EventDTO> findAllEvents();

    EventDTO findByEventId(Long eventId);

    void updateEvent(EventDTO event);

    void deleteEvent(Long eventId);
}
