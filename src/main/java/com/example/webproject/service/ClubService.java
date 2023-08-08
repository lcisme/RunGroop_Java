package com.example.webproject.service;

import com.example.webproject.dto.ClubDTO;
import com.example.webproject.entity.Club;

import java.util.List;

public interface ClubService {
    List<ClubDTO> findAllClubs();

    Club saveClub(ClubDTO clubDTO);

    ClubDTO findClubById(Long clubId);

    void updateClub(ClubDTO club);

    void delete(Long clubId);

    List<ClubDTO> searchClubs(String query);
}
