package com.example.webproject.mapper;

import com.example.webproject.dto.EventDTO;
import com.example.webproject.entity.Event;

public class EventMapper {
// chuyen doi tuong tu dto ve entity
    public static Event mapToEvent(EventDTO eventDTO){
        return Event.builder()
                .id((eventDTO.getId()))
                .name(eventDTO.getName())
                .startTime(eventDTO.getStartTime())
                .endTime(eventDTO.getEndTime())
                .type(eventDTO.getType())
                .photoUrl(eventDTO.getPhotoUrl())
                .createdOn(eventDTO.getCreatedOn())
                .updatedOn(eventDTO.getUpdatedOn())
                .club(eventDTO.getClub())
                .build();
    }
// chuyen tu entity ve dto
    public static EventDTO mapToEventDTO(Event event){
        return EventDTO.builder()
                .id((event.getId()))
                .name(event.getName())
                .startTime(event.getStartTime())
                .endTime(event.getEndTime())
                .type(event.getType())
                .photoUrl(event.getPhotoUrl())
                .createdOn(event.getCreatedOn())
                .updatedOn(event.getUpdatedOn())
                .club(event.getClub())
                .build();
    }
}
