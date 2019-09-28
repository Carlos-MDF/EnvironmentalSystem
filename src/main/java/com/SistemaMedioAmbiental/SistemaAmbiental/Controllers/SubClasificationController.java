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
import com.SistemaMedioAmbiental.SistemaAmbiental.Models.Clasification;
import com.SistemaMedioAmbiental.SistemaAmbiental.Models.SubClasification;
import com.SistemaMedioAmbiental.SistemaAmbiental.Repositories.ClasificationRepository;
import com.SistemaMedioAmbiental.SistemaAmbiental.Repositories.SubClasificationRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class SubClasificationController {
    @Autowired
    SubClasificationRepository subClasificationRepository;

    @Autowired
    ClasificationRepository clasificationRepository;


    @GetMapping("/subClasification")
    public List<SubClasification> showSubClasification() {
        return subClasificationRepository.findAll();
    }

    @GetMapping("/subClasification/{id}")
    public Optional<SubClasification> showSubClasification(@PathVariable("id") Long id) {
        return subClasificationRepository.findById(id);
    }

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

    @PostMapping("/{id}/subClasification")
    @ResponseStatus(HttpStatus.CREATED)
    public SubClasification create(@RequestBody SubClasification sb,@PathVariable("id") Long id) {
        return clasificationRepository.findById(id).map(cl ->{
            sb.setClasification(cl);
            return subClasificationRepository.save(sb);
        }).orElseThrow(() -> new ResourceNotFoundException("Clasification " + id + " not found"));
    }

    @PutMapping("/subClasification/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SubClasification update(@PathVariable( "id" ) Long id, @RequestBody SubClasification sb) {
        
        return subClasificationRepository.findById(id)
                .map(sbC -> {
                    sbC.setName(sb.getName());
                    sbC.setInformation(sb.getInformation());
                    sbC.setClasification(sb.getClasification());
                    return subClasificationRepository.save(sb);
                }).orElseThrow(() -> new ResourceNotFoundException("Sub Clasification not found with id " + id));
    }
    
    @DeleteMapping(value = "/subClasification/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        subClasificationRepository.deleteById(id);
    }


}