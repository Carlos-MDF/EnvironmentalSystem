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

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class SubDistrictController {
    @Autowired
    SubDistrictRepository subDistrictRepository;

    @Autowired
    DistrictRepository districtRepository;

    @GetMapping("/subDistrict")
    public List<SubDistrict> showSubDistrict() {
        return subDistrictRepository.findAll();
    }

    @GetMapping("/subDistrict/{id}")
    public Optional<SubDistrict> showSubDistrict(@PathVariable("id") Long id) {
        return subDistrictRepository.findById(id);
    }

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

    @PostMapping("/{id}/subDistrict")
    @ResponseStatus(HttpStatus.CREATED)
    public SubDistrict create(@RequestBody SubDistrict sd,@PathVariable("id") Long id) {
        return districtRepository.findById(id).map(ds ->{
            sd.setDistrict(ds);
            return subDistrictRepository.save(sd);
        }).orElseThrow(() -> new ResourceNotFoundException("Sub District " + id + " not found"));
    }

    @PutMapping("/subDistrict/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SubDistrict update(@PathVariable( "id" ) Long id, @RequestBody SubDistrict sd) {
        
        return subDistrictRepository.findById(id)
                .map(sdT -> {
                    sdT.setName(sd.getName());
                    sdT.setInformation(sd.getInformation());
                    sdT.setCod(sd.getCod());
                    return subDistrictRepository.save(sd);
                }).orElseThrow(() -> new ResourceNotFoundException("Sub District not found with id " + id));
    }
    
    @DeleteMapping(value = "/subDistrict/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        subDistrictRepository.deleteById(id);
    }


}