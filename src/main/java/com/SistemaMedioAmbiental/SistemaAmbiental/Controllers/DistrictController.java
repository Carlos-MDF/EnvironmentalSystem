package com.SistemaMedioAmbiental.SistemaAmbiental.Controllers;

import java.util.List;
import java.util.Optional;

import com.SistemaMedioAmbiental.SistemaAmbiental.Message.Exception.ResourceNotFoundException;
import com.SistemaMedioAmbiental.SistemaAmbiental.Models.District;
import com.SistemaMedioAmbiental.SistemaAmbiental.Repositories.DistrictRepository;

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
@Api(value = "District Management", description = "Operations pertaining to districts in District Management")

public class DistrictController {
    @Autowired
    DistrictRepository districtRepository;

    @ApiOperation(value = "View a list of available districts", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list of districts"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach has not been found") })
    @GetMapping("/district")
    public List<District> showClasification() {
        return districtRepository.findAll();
    }

    @ApiOperation(value = "Get a district by Id")
    @GetMapping("/district/{id}")
    public Optional<District> showClasification(@PathVariable("id") Long id) {
        return districtRepository.findById(id);
    }

    @ApiOperation(value = "Add a district")
    @PostMapping("/district")
    @ResponseStatus(HttpStatus.CREATED)
    public District create(@RequestBody District ds) {
        return districtRepository.save(ds);
    }

    @ApiOperation(value = "Update a district")
    @PutMapping("/district/{id}")
    @ResponseStatus(HttpStatus.OK)
    public District update(@PathVariable( "id" ) Long id, @RequestBody District cl) {
        
        return districtRepository.findById(id)
                .map(clT -> {
                    clT.setName(cl.getName());
                    clT.setInformation(cl.getInformation());
                    return districtRepository.save(cl);
                }).orElseThrow(() -> new ResourceNotFoundException("District not found with id " + id));
    }
    
    @ApiOperation(value = "Delete a district")
    @DeleteMapping(value = "/district/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        districtRepository.deleteById(id);
    }

}