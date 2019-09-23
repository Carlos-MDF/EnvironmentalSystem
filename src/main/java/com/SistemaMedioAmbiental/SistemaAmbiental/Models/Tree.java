package com.SistemaMedioAmbiental.SistemaAmbiental.Models;

import java.util.Optional;

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


@Entity
@Table(name = "trees", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
          "codeTree"
        }),
        @UniqueConstraint(columnNames = {
          "scientificName"
        })
})

public class Tree {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
   
    
    @Size(min=3, max = 50)
    @Column(name = "codeTree")
    private String codeTree;
   
    
    @Size(min=3, max = 50)
    @Column(name = "commonName")
    private String commonName;
  
    
    @Size(min=6, max = 100)
    @Column(name = "scientificName")
    private String scientificName;
   
    

    @Column(name = "treeHeight")
    private Integer treeHeight;
  
    @Column(name = "cupSize")
    private Integer cupSize;
  
    @Column(name = "silviTreatPer")
    private String silviTreatPer;
  
    @Size(min=6, max = 100)
    @Column(name = "silviTreatSugg")
    private String silviTreatSugg;

    @Column(name = "responsable")
    private String responsable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "locationTree_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private LocationTree locationTree;

    public Tree() {
    }
   
    public Tree(String codeTree, String commonName, String scientificName, Integer treeHeight, Integer cupSize, String silviTreatPer, String silviTreatSugg, String responsable, LocationTree locationTree) {
      this.codeTree = codeTree;
      this.commonName = commonName;
      this.scientificName = scientificName;
      this.treeHeight = treeHeight;
      this.cupSize = cupSize;
      this.silviTreatPer = silviTreatPer;
      this.silviTreatSugg = silviTreatSugg;
      this.responsable = responsable;
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
    
      //Tratamientos silviculturales Realizados
    
      public String getSilviTreatPer(){
        return this.silviTreatPer;
     }
    
     public void setSilviTreatPer(String silviTreatPer){
       this.silviTreatPer = silviTreatPer;
     }
    
     //Tratamientos silviculturales sugeridos
    
     public String getSilviTreatSugg(){
       return this.silviTreatSugg;
     }
    
     public void setSilviTreatSugg(String silviTreatSugg){
       this.silviTreatSugg = silviTreatSugg;
     }

     // Responsable del Arbol
     public void setResponsable(String responsable) {
      this.responsable = responsable;
    }
   
    public String getResponsable() {
      return this.responsable;
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
        return "Tree [id=" + id + ", codeTree=" + codeTree + ", commonName=" + commonName + ", scientificName=" + scientificName + ", treeHeight=" + treeHeight + ", cupSize=" + cupSize + ", silviTreatPer=" + silviTreatPer 
       + ", silviTreatSugg=" + silviTreatSugg + ", responsable:" + responsable + ", locationTree" + locationTree +"]";
    }
}