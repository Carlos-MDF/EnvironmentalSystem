package com.SistemaMedioAmbiental.SistemaAmbiental.Controllers;

import java.util.List;
import java.util.Optional;

import com.SistemaMedioAmbiental.SistemaAmbiental.Models.LocationTree;
import com.SistemaMedioAmbiental.SistemaAmbiental.Models.Tree;
import com.SistemaMedioAmbiental.SistemaAmbiental.Repositories.LocationTreeRepository;
import com.SistemaMedioAmbiental.SistemaAmbiental.Repositories.TreeRepository;
import com.SistemaMedioAmbiental.SistemaAmbiental.Message.Exception.ResourceNotFoundException;
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

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class TreeController {

    @Autowired
    TreeRepository treeRepository;
    @Autowired
    LocationTreeRepository locationTreeRepository;

    @GetMapping("/tree")
    public List<Tree> showTree() {
        return treeRepository.findAll();
    }

    @GetMapping("/tree/{id}")
    public Tree showTrees(@PathVariable("id") Long id) {
        return treeRepository.findById(id).orElse(null);
    }

    @PostMapping("/{id}/tree")
    @ResponseStatus(HttpStatus.CREATED)
    public Tree create(@RequestBody Tree tree,@PathVariable("id") Long id) {
        return locationTreeRepository.findById(id).map(lc ->{
            tree.setLocationTree(lc);
            return treeRepository.save(tree);
        }).orElseThrow(() -> new ResourceNotFoundException("Location Tree " + id + " not found"));    
    }

    @PutMapping("/tree/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Tree update(@PathVariable( "id" ) Long id, @RequestBody Tree t) {
        
        return treeRepository.findById(id)
                .map(tree -> {
                    tree.setcodeTree(t.getCodeTree());
                    tree.setCommonName(t.getCommonName());
                    tree.setScientificName(t.getScientificName());
                    tree.setTreeHeight(t.getTreeHeight());
                    tree.setSilviTreatPer(t.getSilviTreatPer());
                    tree.setSilviTreatSugg(t.getSilviTreatSugg());
                    tree.setResponsable(t.getResponsable());
                    tree.setLocationTree(t.getLocationTree());
                    return treeRepository.save(tree);
                }).orElseThrow(() -> new ResourceNotFoundException("Tree not found with id " + id));
    }
    
    @DeleteMapping(value = "/tree/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        treeRepository.deleteById(id);
    }
}