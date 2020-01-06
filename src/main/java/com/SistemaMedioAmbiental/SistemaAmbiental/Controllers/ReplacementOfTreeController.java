package com.SistemaMedioAmbiental.SistemaAmbiental.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.SistemaMedioAmbiental.SistemaAmbiental.Message.Exception.ResourceNotFoundException;
import com.SistemaMedioAmbiental.SistemaAmbiental.Models.ReplacementOfTree;
import com.SistemaMedioAmbiental.SistemaAmbiental.Repositories.LocationTreeRepository;
import com.SistemaMedioAmbiental.SistemaAmbiental.Repositories.ReplacementOfTreeRepository;

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
@Api(value = "ReplacementOfTree Management", description = "Operations pertaining to ReplacementOfTree in ReplacementOfTree Management")

public class ReplacementOfTreeController {
    @Autowired
    ReplacementOfTreeRepository replacementOfTreeRepository;
    @Autowired
    LocationTreeRepository locationTreeRepository;



    @ApiOperation(value = "View a list of available ReplacementsOfTrees", response = List.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list of complaints"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach has not been found") })
    @GetMapping("/replacementOfTree")
    public List<ReplacementOfTree> showReplacementsOfTrees() {
        return replacementOfTreeRepository.findAll();
    }

    @ApiOperation(value = "View a list of available ReplacementsOfTrees", response = List.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list of complaints"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach has not been found") })
    @GetMapping("/{id}/replacementOfTree")
    public List<ReplacementOfTree> showReplacementsOfTreesLocations(@PathVariable("id") Long id) {
        List<ReplacementOfTree> arboles= replacementOfTreeRepository.findAll();
       List<ReplacementOfTree> aux= new ArrayList<>();
        for(Integer i = 0;i< arboles.size();i++){
            if(arboles.get(i).getLocationTree().getId() == id ){
                aux.add(arboles.get(i));
            }
        } 
        return aux;
    }

    @ApiOperation(value = "Get a treatmentTree by Id")
    @GetMapping("/replacementOfTree/{id}")
    public Optional<ReplacementOfTree> showReplacementTree(@PathVariable("id") Long id) {
        return replacementOfTreeRepository.findById(id);
    }

    @ApiOperation(value = "Add a treatmentTree")
    @PostMapping("/{id}/replacementOfTree")
    @ResponseStatus(HttpStatus.CREATED)
    public ReplacementOfTree create(@RequestBody ReplacementOfTree cm,@PathVariable("id") Long id) {
        return locationTreeRepository.findById(id).map(lc ->{
            cm.setLocationTree(lc);
            return replacementOfTreeRepository.save(cm);
        }).orElseThrow(() -> new ResourceNotFoundException("Location Tree " + id + " not found"));    
        
    }

    @ApiOperation(value = "Update a treatmentTree")
    @PutMapping("/replacementOfTree/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReplacementOfTree update(@PathVariable( "id" ) Long id, @RequestBody ReplacementOfTree cl) {
        
        return replacementOfTreeRepository.findById(id)
                .map(clT -> {
                    clT.setTypeOfReplacement(cl.getTypeOfReplacement());
                    clT.setEliminationOfCompetition(cl.getEliminationOfCompetition());
                    clT.setDateOfReplacement(cl.getDateOfReplacement());
                    clT.setTree(cl.getTree());
                    clT.setLocationTree(cl.getLocationTree());
                    return replacementOfTreeRepository.save(cl);
                }).orElseThrow(() -> new ResourceNotFoundException("ReplacementOfTree not found with id " + id));
    }
    
    @ApiOperation(value = "Delete a replacementOfTree")
    @DeleteMapping(value = "/replacementOfTree/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        replacementOfTreeRepository.deleteById(id);
    }
}