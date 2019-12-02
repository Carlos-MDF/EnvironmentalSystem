package com.SistemaMedioAmbiental.SistemaAmbiental.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import org.springframework.http.HttpStatus;
import com.SistemaMedioAmbiental.SistemaAmbiental.Message.Exception.ResourceNotFoundException;
import com.SistemaMedioAmbiental.SistemaAmbiental.Models.SubClasification;
import com.SistemaMedioAmbiental.SistemaAmbiental.Repositories.ClasificationRepository;
import com.SistemaMedioAmbiental.SistemaAmbiental.Repositories.SubClasificationRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
@Api(value = "Sub-Classification Management", description = "Operations pertaining to Sub-classifications in Sub-Classification Management")

public class SubClasificationController {
    @Autowired
    SubClasificationRepository subClasificationRepository;

    @Autowired
    ClasificationRepository clasificationRepository;

    @ApiOperation(value = "View a list of available sub-classifications", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list of sub-classifications"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach has not been found") })
    @GetMapping("/subClasification")
    public List<SubClasification> showSubClasification() {
        return subClasificationRepository.findAll();
    }

    @ApiOperation(value = "Get a sub-classification by Id")
    @GetMapping("/subClasification/{id}")
    public Optional<SubClasification> showSubClasification(@PathVariable("id") Long id) {
        return subClasificationRepository.findById(id);
    }

    @ApiOperation(value = "View a list of sub-classifications of it's respective classification", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list of sub-classifications"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach has not been found") })
    @GetMapping("/{id}/subClasification")
    public List<SubClasification> showSubClassesClasification(@PathVariable("id") Long id) {
       List<SubClasification> subclas= subClasificationRepository.findAll();
       List<SubClasification> aux= new ArrayList<>();
        for(Integer i = 0;i< subclas.size();i++){
            if(subclas.get(i).getClasification().getId() == id ){
                aux.add(subclas.get(i));
            }
        } 
        return aux;
    }

    @ApiOperation(value = "Add a sub-classification")
    @PostMapping("/{id}/subClasification")
    @ResponseStatus(HttpStatus.CREATED)
    public SubClasification create(@RequestBody SubClasification sb,@PathVariable("id") Long id) {
        return clasificationRepository.findById(id).map(cl ->{
            sb.setClasification(cl);
            return subClasificationRepository.save(sb);
        }).orElseThrow(() -> new ResourceNotFoundException("Clasification " + id + " not found"));
    }

    @ApiOperation(value = "Update a sub-classification")
    @PutMapping("/subClasification/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SubClasification update(@PathVariable( "id" ) Long id, @RequestBody SubClasification sb) {
        
        return subClasificationRepository.findById(id)
                .map(sbC -> {
                    sbC.setName(sb.getName());
                    sbC.setInformation(sb.getInformation());
                    sbC.setClasification(sb.getClasification());
                    sbC.setImageLink(sb.getImageLink());
                    return subClasificationRepository.save(sb);
                }).orElseThrow(() -> new ResourceNotFoundException("Sub Clasification not found with id " + id));
    }
    
    @ApiOperation(value = "Delete a sub-classification")
    @DeleteMapping(value = "/subClasification/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        subClasificationRepository.deleteById(id);
    }


}