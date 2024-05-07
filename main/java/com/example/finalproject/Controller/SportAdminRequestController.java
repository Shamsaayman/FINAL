package com.example.finalproject.Controller;

import com.example.finalproject.API.ApiResponse;
import com.example.finalproject.Model.SportAdminRequest;
import com.example.finalproject.Service.SportAdminRequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sport-request")
@RequiredArgsConstructor
public class SportAdminRequestController {

    private final SportAdminRequestService sportAdminRequestService;



    @GetMapping("/get")
    public ResponseEntity getAllRequests(){
        return ResponseEntity.status(200).body(sportAdminRequestService.getAllRequests());
    }


    @PostMapping("/add/{teamId}/{sportID}")
    public ResponseEntity addRequest(@RequestBody @Valid SportAdminRequest request){
        sportAdminRequestService.addRequest(request);
        return ResponseEntity.status(200).body(new ApiResponse("Request added successfully"));
    }


    @PutMapping("/update/{requestId}")
    public ResponseEntity updateRequests(@PathVariable Integer requestId , @RequestBody @Valid SportAdminRequest request){
        sportAdminRequestService.updateRequest(requestId,request);
        return ResponseEntity.status(200).body(new ApiResponse("Request updated successfully"));
    }


    @DeleteMapping("/delete/{requestId}")
    public ResponseEntity deleteRequests(@PathVariable Integer requestId ){
        sportAdminRequestService.deleteRequest(requestId);
        return ResponseEntity.status(200).body(new ApiResponse("Request deleted successfully"));
    }


    @PutMapping("/ask-join/{requestId}")
    public ResponseEntity askToJoinToSportAdmin(@PathVariable Integer requestId) {
        sportAdminRequestService.askToJoinToSportAdmin(requestId);
        return ResponseEntity.status(200).body("join successfully");
    }


}
