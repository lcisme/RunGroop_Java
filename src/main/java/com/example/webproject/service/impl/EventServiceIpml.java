package com.example.webproject.service.impl;

import com.example.webproject.dto.EventDTO;
import com.example.webproject.entity.Club;
import com.example.webproject.entity.Event;
import com.example.webproject.repository.ClubRepository;
import com.example.webproject.repository.EventRepository;
import com.example.webproject.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.webproject.mapper.EventMapper.mapToEvent;
import static com.example.webproject.mapper.EventMapper.mapToEventDTO;

@Service
public class EventServiceIpml implements EventService {
    private EventRepository eventRepository;
    private ClubRepository clubRepository;

    @Autowired
    public EventServiceIpml(EventRepository eventRepository, ClubRepository clubRepository) {
        this.eventRepository = eventRepository;
        this.clubRepository = clubRepository;
    }

    @Override
    public void createEvent(Long clubId, EventDTO eventDTO) {
        Club club = clubRepository.findById(clubId).get();
        Event event = mapToEvent(eventDTO);
        event.setClub(club);
        eventRepository.save(event);

    }

    @Override
    public List<EventDTO> findAllEvents() {
        List<Event> events = eventRepository.findAll();
        return events.stream().map((event)-> mapToEventDTO(event)).collect(Collectors.toList());
    }

    @Override
    public EventDTO findByEventId(Long eventId) {
        Event event = eventRepository.findById(eventId).get();
        return mapToEventDTO(event);
    }

    @Override
    public void updateEvent(EventDTO eventDTO) {
        Event event = mapToEvent(eventDTO);
        eventRepository.save(event);
    }

    @Override
    public void deleteEvent(Long eventId) {
        eventRepository.deleteById(eventId);
    }

}
