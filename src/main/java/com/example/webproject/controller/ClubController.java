package com.example.webproject.controller;

import com.example.webproject.dto.ClubDTO;
import com.example.webproject.entity.Club;
import com.example.webproject.entity.UserEntity;
import com.example.webproject.security.SecurityUtil;
import com.example.webproject.service.ClubService;
import com.example.webproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/clubs")
public class ClubController {
    private ClubService clubService;
    private UserService userService;

    @Autowired
    public ClubController(ClubService clubService,UserService userService) {
        this.clubService = clubService;
        this.userService = userService;
    }
    // getAll
    @GetMapping
    public String listClubs(Model model){
        UserEntity user = new UserEntity();
        List<ClubDTO> clubs = clubService.findAllClubs();
        String username = SecurityUtil.getSessionUser();
        if (username != null){
            user = userService.findByUsername(username);
            model.addAttribute("user",user);
        }
        model.addAttribute("user",user);
        model.addAttribute("clubs", clubs);
        return "club/clubs-list";
    }
    //create
    @GetMapping("/new")
    public String createClubFrom(Model model){
        Club club = new Club();
        model.addAttribute("club", club);
        return "club/clubs-create";
    }

    @PostMapping("/new")
    public String saveClub(@Valid @ModelAttribute("club") ClubDTO clubDTO,
                           BindingResult result,
                           Model model){
        if (result.hasErrors()){
            model.addAttribute("club", clubDTO);
            return "club/clubs-create";
        }
        clubService.saveClub(clubDTO);
        return "redirect:/clubs";
    }
    // search
    @GetMapping("/search")
    public String searchClub(@RequestParam(value = "query")String query, Model model){
        List<ClubDTO> clubs = clubService.searchClubs(query);
        model.addAttribute("clubs", clubs);
        return "club/clubs-list";
    }

    //delete
    @GetMapping("/{clubId}/delete")
    public String deleteClub(@PathVariable("clubId") Long clubId){
        clubService.delete(clubId);
        return "redirect:/clubs";
    }

    // detail
    @GetMapping("/{clubId}")
    public String clubDetail(@PathVariable("clubId") Long clubId, Model model){
        UserEntity user = new UserEntity();
        String username = SecurityUtil.getSessionUser();
        if (username != null){
            user = userService.findByUsername(username);
            model.addAttribute("user",user);
        }
        model.addAttribute("user",user);
        ClubDTO clubDTO = clubService.findClubById(clubId);
        model.addAttribute("club", clubDTO);
        return "club/clubs-detail";
    }
    // update
    @GetMapping("/{clubId}/edit")
    public String editClubForm(@PathVariable("clubId") Long clubId, Model model){
        ClubDTO club = clubService.findClubById(clubId);
        model.addAttribute("club", club);
        return "club/clubs-edit";
    }

    @PostMapping("/{clubId}/edit")
    public String updateClub(@PathVariable("clubId") Long clubId,
                             @Valid @ModelAttribute("club") ClubDTO club,
                             BindingResult result, Model model){
        if (result.hasErrors()){
            model.addAttribute("club", club);
            return "club/clubs-edit";
        }
        club.setId(clubId);
        clubService.updateClub(club);
        return "redirect:/clubs";
    }
}
