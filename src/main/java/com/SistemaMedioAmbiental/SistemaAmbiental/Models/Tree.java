package com.SistemaMedioAmbiental.SistemaAmbiental.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "trees", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
          "id"
        })
})

@ApiModel(description = "All details about a tree. ")
public class Tree {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "The database generated tree ID")
    private Long id;
   
    @Size(min=3, max = 50)
    @Column(name = "codeTree")
    @ApiModelProperty(notes = "The code assigned to a tree by authorities")
    private String codeTree;
   
    
    @Size(min=3, max = 50)
    @Column(name = "commonName")
    @ApiModelProperty(notes = "The common name this tree is referred as")
    private String commonName;
  
    
    @Size(min=6, max = 100)
    @Column(name = "scientificName")
    @ApiModelProperty(notes = "The scientific name of the tree")
    private String scientificName;
   
    @Column(name = "treeHeight")
    @ApiModelProperty(notes = "The height of the tree")
    private Integer treeHeight;
  
    @Column(name = "cupSize")
    @ApiModelProperty(notes = "The size of this tree's cup")
    private Integer cupSize;

    @Column(name = "species")
    @ApiModelProperty(notes = "The species to which the tree belongs")
    private String species;

    @Column(name = "responsable")
    @ApiModelProperty(notes = "the person responsible for this tree")
    private String responsable;

    @Column(name = "imageLink")
    @ApiModelProperty(notes = "the link to an image of this tree")
    private String imageLink;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "locationTree_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ApiModelProperty(notes = "The information about this tree's location")
    private LocationTree locationTree;

    public Tree() {
    }
   
    public Tree(String codeTree, String commonName, String scientificName, Integer treeHeight, Integer cupSize, String responsable,String imageLink, String species, LocationTree locationTree) {
      this.codeTree = codeTree;
      this.commonName = commonName;
      this.scientificName = scientificName;
      this.treeHeight = treeHeight;
      this.cupSize = cupSize;
      this.species = species;
      this.responsable = responsable;
      this.imageLink = imageLink;
      this.locationTree = locationTree;
    }

    public Long getId() {
        return id;
      }
    
      // codigo del arbol
      public void setcodeTree(String codeTree) {
        this.codeTree = codeTree;
      }
     
      public String getCodeTree() {
        return this.codeTree;
      }
    
      //nombre comun del arbol
      public String getCommonName() {
        return commonName;
      }
    
      public void setCommonName(String commonName) {
        this.commonName = commonName;
      }
    
      //Nombre centifico del arbol
      public String getScientificName(){
        return this.scientificName;
      }
    
      public void setScientificName(String scientificName){
        this.scientificName = scientificName;
      }
    
      //Tamaño del arbol
      public Integer getTreeHeight(){
        return this.treeHeight;
      }
    
      public void setTreeHeight(Integer treeHeight){
        this.treeHeight = treeHeight;
      }
    
      //Tamaño de Copa del Arbol
    
      public Integer getCupSize(){
        return this.cupSize;
      }
    
      public void setCupSize(Integer cupSize){
        this.cupSize = cupSize;
      }

     // Responsable del Arbol
     public void setResponsable(String responsable) {
      this.responsable = responsable;
    }
   
    public String getResponsable() {
      return this.responsable;
    }

    //Link de la imagen del arbol
    public void setImageLink(String imageLink) {
      this.imageLink = imageLink;
    }
   
    public String getImageLink() {
      return this.imageLink;
    }

    //Especie del Arbol
    public void setSpecies(String species) {
      this.species = species;
    }

    public String getSpecies() {
      return this.species;
    }

    //locationTreeId
    public void setLocationTree(LocationTree l) {
      this.locationTree=l;
    }
   
    public LocationTree getLocationTree() {
      return this.locationTree;
    }

     @Override
    public String toString() {
        return "Tree [id=" + id + ", codeTree=" + codeTree + ", commonName=" + commonName + ", scientificName=" + scientificName + ", treeHeight=" + treeHeight + ", cupSize=" + cupSize + ", species=" + species + ", responsable:" + responsable + ", imageLink:" + imageLink + ", locationTree" + locationTree +"]";
    }
}