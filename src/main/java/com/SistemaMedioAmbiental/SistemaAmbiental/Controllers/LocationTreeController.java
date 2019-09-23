package com.SistemaMedioAmbiental.SistemaAmbiental.Controllers;

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
import org.springframework.http.HttpStatus;
import com.SistemaMedioAmbiental.SistemaAmbiental.Message.Exception.ResourceNotFoundException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class LocationTreeController {
    @Autowired
    LocationTreeRepository locationTreeRepository;

    @Autowired
    SubClasificationRepository subClasificationRepository;

    @GetMapping("/locationTree")
    public List<LocationTree> showLocationTree() {
        return locationTreeRepository.findAll();
    }

    @GetMapping("/locationTree/{id}")
    public Optional<LocationTree> showLocationTree(@PathVariable("id") Long id) {
        return locationTreeRepository.findById(id);
    }

    @PostMapping("/{id}/locationTree")
    @ResponseStatus(HttpStatus.CREATED)
    public LocationTree create(@RequestBody LocationTree lc,@PathVariable("id") Long id) {
        return subClasificationRepository.findById(id).map(sb ->{
            lc.setSubClasification(sb);
            return locationTreeRepository.save(lc);
        }).orElseThrow(() -> new ResourceNotFoundException("Sub Clasification " + id + " not found"));    
    }

    @PutMapping("/locationTree/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LocationTree update(@PathVariable( "id" ) Long id, @RequestBody LocationTree lc) {
        
        return locationTreeRepository.findById(id)
                .map(lcT -> {
                    lcT.setName(lc.getName());
                    lcT.setInformation(lc.getInformation());
                    lcT.setSubClasification(lc.getSubClasification());
                    return locationTreeRepository.save(lc);
                }).orElseThrow(() -> new ResourceNotFoundException("Location Tree not found with id " + id));
    }
    
    @DeleteMapping(value = "/locationTree/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        locationTreeRepository.deleteById(id);
    }


}