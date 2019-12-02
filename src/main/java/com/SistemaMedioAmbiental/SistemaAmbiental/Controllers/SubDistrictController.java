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
import com.SistemaMedioAmbiental.SistemaAmbiental.Models.SubDistrict;
import com.SistemaMedioAmbiental.SistemaAmbiental.Repositories.DistrictRepository;
import com.SistemaMedioAmbiental.SistemaAmbiental.Repositories.SubDistrictRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
@Api(value = "Sub-District Management", description = "Operations pertaining to Sub-districts in Sub-district Management")

public class SubDistrictController {
    @Autowired
    SubDistrictRepository subDistrictRepository;

    @Autowired
    DistrictRepository districtRepository;

    @ApiOperation(value = "View a list of available sub-districts", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list of sub-districts"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach has not been found") })
    @GetMapping("/subDistrict")
    public List<SubDistrict> showSubDistrict() {
        return subDistrictRepository.findAll();
    }

    @ApiOperation(value = "Get a sub-district by Id")
    @GetMapping("/subDistrict/{id}")
    public Optional<SubDistrict> showSubDistrict(@PathVariable("id") Long id) {
        return subDistrictRepository.findById(id);
    }

    @ApiOperation(value = "View a list of sub-districts of it's respective district", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list of sub-districts"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach has not been found") })
    @GetMapping("/{id}/subDistrict")
    public List<SubDistrict> showSubDistrictsOfDistrict(@PathVariable("id") Long id) {
       List<SubDistrict> subDIS= subDistrictRepository.findAll();
       List<SubDistrict> aux= new ArrayList<>();
        for(Integer i = 0;i< subDIS.size();i++){
            if(subDIS.get(i).getDistrict().getId() == id ){
                aux.add(subDIS.get(i));
            }
        } 
        return aux;
    }

    @ApiOperation(value = "Add a sub-district")
    @PostMapping("/{id}/subDistrict")
    @ResponseStatus(HttpStatus.CREATED)
    public SubDistrict create(@RequestBody SubDistrict sd,@PathVariable("id") Long id) {
        return districtRepository.findById(id).map(ds ->{
            sd.setDistrict(ds);
            return subDistrictRepository.save(sd);
        }).orElseThrow(() -> new ResourceNotFoundException("Sub District " + id + " not found"));
    }

    @ApiOperation(value = "Update a sub-district")
    @PutMapping("/subDistrict/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SubDistrict update(@PathVariable( "id" ) Long id, @RequestBody SubDistrict sd) {
        
        return subDistrictRepository.findById(id)
                .map(sdT -> {
                    sdT.setName(sd.getName());
                    sdT.setInformation(sd.getInformation());
                    sdT.setCod(sd.getCod());
                    sdT.setImageLink(sd.getImageLink());
                    return subDistrictRepository.save(sd);
                }).orElseThrow(() -> new ResourceNotFoundException("Sub District not found with id " + id));
    }
    
    @ApiOperation(value = "Delete a sub-district")
    @DeleteMapping(value = "/subDistrict/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        subDistrictRepository.deleteById(id);
    }


}