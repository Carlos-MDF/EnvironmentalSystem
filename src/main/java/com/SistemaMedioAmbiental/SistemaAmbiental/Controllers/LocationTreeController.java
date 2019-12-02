package com.SistemaMedioAmbiental.SistemaAmbiental.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.SistemaMedioAmbiental.SistemaAmbiental.Models.LocationTree;
import com.SistemaMedioAmbiental.SistemaAmbiental.Repositories.LocationTreeRepository;
import com.SistemaMedioAmbiental.SistemaAmbiental.Repositories.SubClasificationRepository;

import org.springframework.beans.factory.annotation.Autowired;
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

import org.springframework.http.HttpStatus;
import com.SistemaMedioAmbiental.SistemaAmbiental.Message.Exception.ResourceNotFoundException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
@Api(value = "Management of the locations of trees", description = "Operations pertaining to locations of trees")
public class LocationTreeController {
    @Autowired
    LocationTreeRepository locationTreeRepository;

    @Autowired
    SubClasificationRepository subClasificationRepository;


    @ApiOperation(value = "View a list of available locations of trees", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list of locations"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach has not been found")
    })
    @GetMapping("/locationTree")
    public List<LocationTree> showLocationTree() {
        return locationTreeRepository.findAll();
    }

    @ApiOperation(value = "Get a the location of a Tree by Id")
    @GetMapping("/locationTree/{id}")
    public Optional<LocationTree> showLocationTree(@PathVariable("id") Long id) {
        return locationTreeRepository.findById(id);
    }

    @ApiOperation(value = "View a list of locations of trees by their respective subclassifications", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach has not been found")
    })
    @GetMapping("/{id}/locationTree")
    public List<LocationTree> showLocationsSubClasification(@PathVariable("id") Long id) {
       List<LocationTree> locations= locationTreeRepository.findAll();
       List<LocationTree> aux= new ArrayList<>();
        for(Integer i = 0;i< locations.size();i++){
            if(locations.get(i).getSubClasification().getId() == id ){
                aux.add(locations.get(i));
            }
        } 
        return aux;
    }

    @ApiOperation(value = "Add a location of a tree")
    @PostMapping("/{id}/locationTree")
    @ResponseStatus(HttpStatus.CREATED)
    public LocationTree create(@RequestBody LocationTree lc,@PathVariable("id") Long id) {
        return subClasificationRepository.findById(id).map(sb ->{
            lc.setSubClasification(sb);
            return locationTreeRepository.save(lc);
        }).orElseThrow(() -> new ResourceNotFoundException("Sub Clasification " + id + " not found"));    
    }

    @ApiOperation(value = "Update the location of a tree")
    @PutMapping("/locationTree/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LocationTree update(@PathVariable( "id" ) Long id, @RequestBody LocationTree lc) {
        
        return locationTreeRepository.findById(id)
                .map(lcT -> {
                    lcT.setName(lc.getName());
                    lcT.setInformation(lc.getInformation());
                    lcT.setImageLink(lc.getImageLink());
                    lcT.setSubClasification(lc.getSubClasification());
                    return locationTreeRepository.save(lc);
                }).orElseThrow(() -> new ResourceNotFoundException("Location Tree not found with id " + id));
    }
    
    @ApiOperation(value = "Delete a location of a tree")
    @DeleteMapping(value = "/locationTree/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        locationTreeRepository.deleteById(id);
    }


}