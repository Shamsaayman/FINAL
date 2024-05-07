package com.example.finalproject.Service;


import com.example.finalproject.API.ApiException;
import com.example.finalproject.Model.SportAdmin;
import com.example.finalproject.Model.SportAdminRequest;
import com.example.finalproject.Model.TeamAdmin;
import com.example.finalproject.Repository.SportAdminRepository;
import com.example.finalproject.Repository.SportAdminRequestRepository;
import com.example.finalproject.Repository.TeamAdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SportAdminRequestService {

    private final SportAdminRequestRepository sportAdminRequestRepository;
    private final SportAdminRepository sportAdminRepository;
    private  final TeamAdminRepository teamAdminRepository;

    public List<SportAdminRequest> getAllRequests() {
        return sportAdminRequestRepository.findAll();
    }

    public SportAdminRequest getRequestById(Integer id) {
        SportAdminRequest sportAdminRequest = sportAdminRequestRepository.findSportAdminRequestByRequestId(id);
        if (sportAdminRequest == null) {
            throw new ApiException("invalid request id");
        }
        return sportAdminRequest;
    }

    public void addRequest(SportAdminRequest sportAdminRequest) {

        sportAdminRequestRepository.save(sportAdminRequest);
    }

    public void updateRequest(Integer requestId,SportAdminRequest sportAdminRequest) {
        SportAdminRequest SportAdminRequests= sportAdminRequestRepository.findSportAdminRequestByRequestId(requestId);
        if (SportAdminRequests==null){
            throw new ApiException("Invalid ID");
        }
        SportAdminRequests.setStatus(sportAdminRequest.getStatus());
        sportAdminRequestRepository.save(sportAdminRequest);
    }

    public void deleteRequest(Integer requestId) {
        SportAdminRequest SportAdminRequests= sportAdminRequestRepository.findSportAdminRequestByRequestId(requestId);
        sportAdminRequestRepository.delete(SportAdminRequests);
    }

    public void askToJoinToSportAdmin(Integer requestId) {
        SportAdminRequest SportAdminRequests= sportAdminRequestRepository.findSportAdminRequestByRequestId(requestId);
        SportAdmin sportAdmin=sportAdminRepository.findSportAdminBySportType(SportAdminRequests.getSportType());
        TeamAdmin teamAdmin=teamAdminRepository.findTeamAdminByteamName(SportAdminRequests.getTeamName());
        if(sportAdmin==null||teamAdmin==null){
            throw new ApiException("Invalid");
        }
        teamAdmin.setSportAdmin(sportAdmin);
        sportAdmin.getTeamAdminSet().add(teamAdmin);
        teamAdminRepository.save(teamAdmin);
        sportAdminRepository.save(sportAdmin);
    }





}
