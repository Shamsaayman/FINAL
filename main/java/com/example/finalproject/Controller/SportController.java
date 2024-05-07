package com.example.finalproject.Controller;

import com.example.finalproject.API.ApiResponse;
import com.example.finalproject.DTO.SportDTO;
import com.example.finalproject.Model.Sport;
import com.example.finalproject.Service.SportService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/api/v1/sports")
@RestController
public class SportController {
    private SportService sportService;


    @PostMapping("/add")
    public ResponseEntity addSports(@RequestBody @Valid SportDTO sport) {
        sportService.addSport(sport);
        return ResponseEntity.status(200).body(new ApiResponse("Sports added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateSports(@RequestBody @Valid SportDTO sportDTO) {
        sportService.updateSport(sportDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Sports updated successfully"));
    }

    @GetMapping("/get")
    public List<Sport> getAllSports() {
        return sportService.getSports();
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteSports(@PathVariable Integer id){
        sportService.deleteSport(id);
        return ResponseEntity.status(200).body(new ApiResponse("Sports deleted successfully"));
    }

}
