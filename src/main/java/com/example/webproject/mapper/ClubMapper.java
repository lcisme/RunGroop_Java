package com.example.webproject.mapper;

import com.example.webproject.dto.ClubDTO;
import com.example.webproject.entity.Club;

import java.util.stream.Collectors;

import static com.example.webproject.mapper.EventMapper.mapToEventDTO;

public class ClubMapper {
        public static Club mapToClub(ClubDTO club) {
            Club clubDTO = Club.builder()
                    .id(club.getId())
                    .title(club.getTitle())
                    .photoUrl(club.getPhotoUrl())
                    .content(club.getContent())
                    .createdBy(club.getCreatedBy())
                    .createdOn(club.getCreatedOn())
                    .updatedOn(club.getUpdatedOn())
                    .build();
            return  clubDTO;
        }

        public static ClubDTO mapToClubDTO(Club club) {
            ClubDTO clubDto = ClubDTO.builder()
                    .id(club.getId())
                    .title(club.getTitle())
                    .photoUrl(club.getPhotoUrl())
                    .content(club.getContent())
                    .createdBy(club.getCreatedBy())
                    .createdOn(club.getCreatedOn())
                    .updatedOn(club.getUpdatedOn())
                    .events(club.getEvents().stream().map((event) -> mapToEventDTO(event)).collect(Collectors.toList()))
                    .build();
            return clubDto;
        }
}
