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
import com.SistemaMedioAmbiental.SistemaAmbiental.Models.Clasification;
import com.SistemaMedioAmbiental.SistemaAmbiental.Repositories.ClasificationRepository;
import com.SistemaMedioAmbiental.SistemaAmbiental.Repositories.SubDistrictRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ClasificationController {
    @Autowired
    ClasificationRepository clasificationRepository;
    @Autowired
    SubDistrictRepository subDistrictRepository;

    @GetMapping("/clasification")
    public List<Clasification> showClasification() {
        return clasificationRepository.findAll();
    }

    @GetMapping("/clasification/{id}")
    public Optional<Clasification> showClasification(@PathVariable("id") Long id) {
        return clasificationRepository.findById(id);
    }

    @PostMapping("/{id}/clasification")
    @ResponseStatus(HttpStatus.CREATED)
    public Clasification create(@RequestBody Clasification cl,@PathVariable("id") Long id) {
        return subDistrictRepository.findById(id).map(sd ->{
            cl.setSubDistrict(sd);
            return clasificationRepository.save(cl);
        }).orElseThrow(() -> new ResourceNotFoundException("Sub District " + id + " not found"));
    }

    @PutMapping("/clasification/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Clasification update(@PathVariable( "id" ) Long id, @RequestBody Clasification cl) {
        
        return clasificationRepository.findById(id)
                .map(clT -> {
                    clT.setName(cl.getName());
                    clT.setInformation(cl.getInformation());
                    clT.setSubDistrict(cl.getSubDistrict());
                    return clasificationRepository.save(cl);
                }).orElseThrow(() -> new ResourceNotFoundException("Clasification not found with id " + id));
    }
    
    @DeleteMapping(value = "/clasification/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        clasificationRepository.deleteById(id);
    }


}