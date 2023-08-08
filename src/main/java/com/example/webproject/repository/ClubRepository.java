package com.example.webproject.repository;

import com.example.webproject.entity.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
public interface ClubRepository extends JpaRepository<Club, Long> {
    Optional<Club> findByTitle(String url);
    @Query("select c from Club c Where c.title like CONCAT('%', :query, '%')")
    List<Club> searchClubs(String query);
}