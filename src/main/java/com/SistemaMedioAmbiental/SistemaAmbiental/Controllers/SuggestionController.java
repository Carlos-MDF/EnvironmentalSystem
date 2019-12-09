package com.SistemaMedioAmbiental.SistemaAmbiental.Controllers;

import java.util.List;
import java.util.Optional;

import com.SistemaMedioAmbiental.SistemaAmbiental.Message.Exception.ResourceNotFoundException;
import com.SistemaMedioAmbiental.SistemaAmbiental.Models.Suggestion;
import com.SistemaMedioAmbiental.SistemaAmbiental.Repositories.SuggestionRepository;

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
@Api(value = "Suggestion Management", description = "Operations pertaining to suggestion in Suggestion Management")

public class SuggestionController {
    @Autowired
    SuggestionRepository suggestionRepository;

    @ApiOperation(value = "View a list of available suggestions", response = List.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list of complaints"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach has not been found") })
    @GetMapping("/suggestion")
    public List<Suggestion> showSuggestions() {
        return suggestionRepository.findAll();
    }

    @ApiOperation(value = "Get a suggestion by Id")
    @GetMapping("/suggestion/{id}")
    public Optional<Suggestion> showSuggestion(@PathVariable("id") Long id) {
        return suggestionRepository.findById(id);
    }

    @ApiOperation(value = "Add a suggestion")
    @PostMapping("/suggestion")
    @ResponseStatus(HttpStatus.CREATED)
    public Suggestion create(@RequestBody Suggestion cm) {
        return suggestionRepository.save(cm);
    }

    @ApiOperation(value = "Update a suggestion")
    @PutMapping("/suggestion/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Suggestion update(@PathVariable( "id" ) Long id, @RequestBody Suggestion cl) {
        
        return suggestionRepository.findById(id)
                .map(clT -> {
                    clT.setUser(cl.getUser());
                    clT.setInformation(cl.getInformation());
                    clT.setType(cl.getType());
                    return suggestionRepository.save(cl);
                }).orElseThrow(() -> new ResourceNotFoundException("Suggestion not found with id " + id));
    }
    
    @ApiOperation(value = "Delete a suggestion")
    @DeleteMapping(value = "/suggestion/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        suggestionRepository.deleteById(id);
    }
}