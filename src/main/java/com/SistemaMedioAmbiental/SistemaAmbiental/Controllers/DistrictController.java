package com.SistemaMedioAmbiental.SistemaAmbiental.Controllers;

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

import com.SistemaMedioAmbiental.SistemaAmbiental.Models.District;

import com.SistemaMedioAmbiental.SistemaAmbiental.Repositories.DistrictRepository;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class DistrictController {
    @Autowired
    DistrictRepository districtRepository;


    @GetMapping("/district")
    public List<District> showClasification() {
        return districtRepository.findAll();
    }

    @GetMapping("/district/{id}")
    public Optional<District> showClasification(@PathVariable("id") Long id) {
        return districtRepository.findById(id);
    }

    @PostMapping("/district")
    @ResponseStatus(HttpStatus.CREATED)
    public District create(@RequestBody District ds) {
        return districtRepository.save(ds);
    }

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
    
    @DeleteMapping(value = "/district/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        districtRepository.deleteById(id);
    }


}