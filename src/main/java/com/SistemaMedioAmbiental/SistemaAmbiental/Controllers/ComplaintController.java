package com.SistemaMedioAmbiental.SistemaAmbiental.Controllers;

import java.util.List;
import java.util.Optional;

import com.SistemaMedioAmbiental.SistemaAmbiental.Message.Exception.ResourceNotFoundException;
import com.SistemaMedioAmbiental.SistemaAmbiental.Models.Complaint;
import com.SistemaMedioAmbiental.SistemaAmbiental.Repositories.ComplaintRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
@Api(value = "Complaint Management", description = "Operations pertaining to complaint in Complaint Management")

public class ComplaintController {
    @Autowired
    ComplaintRepository complaintRepository;

    @ApiOperation(value = "View a list of available complaints", response = List.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list of complaints"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach has not been found") })
    @GetMapping("/complaint")
    public List<Complaint> showComplaints() {
        return complaintRepository.findAll();
    }

    @ApiOperation(value = "Get a complaint by Id")
    @GetMapping("/complaint/{id}")
    public Optional<Complaint> showComplaint(@PathVariable("id") Long id) {
        return complaintRepository.findById(id);
    }

    @ApiOperation(value = "Add a complaint")
    @PostMapping("/complaint")
    @ResponseStatus(HttpStatus.CREATED)
    public Complaint create(@RequestBody Complaint cm) {
        return complaintRepository.save(cm);
    }

    @ApiOperation(value = "Update a complaint")
    @PutMapping("/complaint/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Complaint update(@PathVariable( "id" ) Long id, @RequestBody Complaint cl) {
        
        return complaintRepository.findById(id)
                .map(clT -> {
                    clT.setWhistleblower(cl.getWhistleblower());
                    clT.setInformation(cl.getInformation());
                    clT.setType(cl.getType());
                    clT.setImageLink(cl.getImageLink());
                    clT.setLatitude(cl.getLatitude());
                    clT.setLongitude(cl.getLongitude());
                    return complaintRepository.save(cl);
                }).orElseThrow(() -> new ResourceNotFoundException("Complaint not found with id " + id));
    }
    
    @ApiOperation(value = "Delete a complaint")
    @DeleteMapping(value = "/complaint/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        complaintRepository.deleteById(id);
    }
}