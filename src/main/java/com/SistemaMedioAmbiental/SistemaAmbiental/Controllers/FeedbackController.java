package com.SistemaMedioAmbiental.SistemaAmbiental.Controllers;

import java.util.List;
import java.util.Optional;

import com.SistemaMedioAmbiental.SistemaAmbiental.Message.Exception.ResourceNotFoundException;
import com.SistemaMedioAmbiental.SistemaAmbiental.Models.Feedback;
import com.SistemaMedioAmbiental.SistemaAmbiental.Repositories.FeedbackRepository;

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
@Api(value = "Feedback Management", description = "Operations pertaining to feedback in Feedback Management")

public class FeedbackController {
    @Autowired
    FeedbackRepository feedbackRepository;

    @ApiOperation(value = "View a list of available feedbacks", response = List.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list of complaints"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach has not been found") })
    @GetMapping("/feedback")
    public List<Feedback> showFeedbacks() {
        return feedbackRepository.findAll();
    }

    @ApiOperation(value = "Get a feedback by Id")
    @GetMapping("/feedback/{id}")
    public Optional<Feedback> showFeedback(@PathVariable("id") Long id) {
        return feedbackRepository.findById(id);
    }

    @ApiOperation(value = "Add a feedback")
    @PostMapping("/feedback")
    @ResponseStatus(HttpStatus.CREATED)
    public Feedback create(@RequestBody Feedback cm) {
        return feedbackRepository.save(cm);
    }

    @ApiOperation(value = "Update a feedback")
    @PutMapping("/feedback/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Feedback update(@PathVariable( "id" ) Long id, @RequestBody Feedback cl) {
        
        return feedbackRepository.findById(id)
                .map(clT -> {
                    clT.setPerson(cl.getPerson());
                    clT.setTreeSpecies(cl.getTreeSpecies());
                    clT.setFlowering(cl.getFlowering());
                    clT.setFructification(cl.getFructification());
                    clT.setDefoliation(cl.getDefoliation());
                    clT.setFoliation(cl.getFoliation());
                    clT.setImageLink(cl.getImageLink());
                    return feedbackRepository.save(cl);
                }).orElseThrow(() -> new ResourceNotFoundException("Suggestion not found with id " + id));
    }
    
    @ApiOperation(value = "Delete a feedback")
    @DeleteMapping(value = "/feedback/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        feedbackRepository.deleteById(id);
    }
}