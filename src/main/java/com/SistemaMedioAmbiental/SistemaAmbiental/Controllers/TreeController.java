package com.SistemaMedioAmbiental.SistemaAmbiental.Controllers;

import java.util.ArrayList;
import java.util.List;

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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
@Api(value = "Tree Management", description = "Operations pertaining to tree in Tree Management")
public class TreeController {

    @Autowired
    TreeRepository treeRepository;
    @Autowired
    LocationTreeRepository locationTreeRepository;

    @ApiOperation(value = "View a list of available trees", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list of trees"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach has not been found")
    })
    @GetMapping("/tree")
    public List<Tree> showTree() {
        return treeRepository.findAll();
    }

    @ApiOperation(value = "Get a tree by Id")
    @GetMapping("/tree/{id}")
    public Tree showTrees(@PathVariable("id") Long id) {
        return treeRepository.findById(id).orElse(null);
    }

    
    @ApiOperation(value = "View a list of the tress in their respective location", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    @GetMapping("/{id}/tree")
    public List<Tree> showTreesLocation(@PathVariable("id") Long id) {
       List<Tree> arboles= treeRepository.findAll();
       List<Tree> aux= new ArrayList<>();
        for(Integer i = 0;i< arboles.size();i++){
            if(arboles.get(i).getLocationTree().getId() == id ){
                aux.add(arboles.get(i));
            }
        } 
        return aux;
    }

    @ApiOperation(value = "Add a tree")
    @PostMapping("/{id}/tree")
    @ResponseStatus(HttpStatus.CREATED)
    public Tree create(@RequestBody Tree tree,@PathVariable("id") Long id) {
        return locationTreeRepository.findById(id).map(lc ->{
            tree.setLocationTree(lc);
            return treeRepository.save(tree);
        }).orElseThrow(() -> new ResourceNotFoundException("Location Tree " + id + " not found"));    
    }

    @ApiOperation(value = "Update a tree")
    @PutMapping("/tree/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Tree update(@PathVariable( "id" ) Long id, @RequestBody Tree t) {
        
        return treeRepository.findById(id)
                .map(tree -> {
                    tree.setcodeTree(t.getCodeTree());
                    tree.setCommonName(t.getCommonName());
                    tree.setScientificName(t.getScientificName());
                    tree.setTreeHeight(t.getTreeHeight());
                    tree.setSpecies(t.getSpecies());
                    tree.setResponsable(t.getResponsable());
                    tree.setImageLink(t.getImageLink());
                    tree.setLocationTree(t.getLocationTree());
                    return treeRepository.save(tree);
                }).orElseThrow(() -> new ResourceNotFoundException("Tree not found with id " + id));
    }
    
    @ApiOperation(value = "Delete a tree")
    @DeleteMapping(value = "/tree/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        treeRepository.deleteById(id);
    }
}