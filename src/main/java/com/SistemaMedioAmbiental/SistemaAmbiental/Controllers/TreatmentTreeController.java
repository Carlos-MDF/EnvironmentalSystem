package com.SistemaMedioAmbiental.SistemaAmbiental.Controllers;

import java.util.List;
import java.util.Optional;

import com.SistemaMedioAmbiental.SistemaAmbiental.Message.Exception.ResourceNotFoundException;
import com.SistemaMedioAmbiental.SistemaAmbiental.Models.TreatmentTree;
import com.SistemaMedioAmbiental.SistemaAmbiental.Repositories.TreatmentTreeRepository;

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
@Api(value = "TreatmentTree Management", description = "Operations pertaining to TreatmentTree in TreatmentTree Management")

public class TreatmentTreeController {
    @Autowired
    TreatmentTreeRepository treatmentTreeRepository;

    @ApiOperation(value = "View a list of available treatmentsTree", response = List.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list of complaints"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach has not been found") })
    @GetMapping("/treatmentTree")
    public List<TreatmentTree> showTretmentsTree() {
        return treatmentTreeRepository.findAll();
    }

    @ApiOperation(value = "Get a treatmentTree by Id")
    @GetMapping("/treatmentTree/{id}")
    public Optional<TreatmentTree> showTreatmentTree(@PathVariable("id") Long id) {
        return treatmentTreeRepository.findById(id);
    }

    @ApiOperation(value = "Add a treatmentTree")
    @PostMapping("/treatmentTree")
    @ResponseStatus(HttpStatus.CREATED)
    public TreatmentTree create(@RequestBody TreatmentTree cm) {
        return treatmentTreeRepository.save(cm);
    }

    @ApiOperation(value = "Update a treatmentTree")
    @PutMapping("/treatmentTree/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TreatmentTree update(@PathVariable( "id" ) Long id, @RequestBody TreatmentTree cl) {
        
        return treatmentTreeRepository.findById(id)
                .map(clT -> {
                    clT.setTypeOfPruning(cl.getTypeOfPruning());
                    clT.setSuggestedTreatment(cl.getSuggestedTreatment());
                    clT.setRealizedTreatment(cl.getRealizedTreatment());
                    clT.setTree(cl.getTree());
                    return treatmentTreeRepository.save(cl);
                }).orElseThrow(() -> new ResourceNotFoundException("TreatmentTree not found with id " + id));
    }
    
    @ApiOperation(value = "Delete a treatmentTree")
    @DeleteMapping(value = "/treatmentTree/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        treatmentTreeRepository.deleteById(id);
    }
}