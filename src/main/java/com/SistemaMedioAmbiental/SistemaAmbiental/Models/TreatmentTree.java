package com.SistemaMedioAmbiental.SistemaAmbiental.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "treatmentTrees", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
          "id"
        })
})

@ApiModel(description = "All details about a treatmentTree.")
public class TreatmentTree {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "The database generated treatmentTree ID")
    private Long id;
   
    @Column(name = "typeOfPruning")
    @ApiModelProperty(notes = "The type of pruning the tree has")
    private String typeOfPruning;

    @Column(name = "suggestedTreatment")
    @ApiModelProperty(notes = "The treatment that is suggested to realize in the tree")
    private String suggestedTreatment;

    @Column(name = "realizedTreatment")
    @ApiModelProperty(notes = "The treatment that has been realized to the tree")
    private String realizedTreatment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "locationTree_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ApiModelProperty(notes = "The information about this tree's location")
    private LocationTree locationTree;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tree_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ApiModelProperty(notes = "The tree this treatment belongs to")
    private Tree tree;
    
    public TreatmentTree() {
    }
   
    public TreatmentTree(String typeOfPruning, String suggestedTreatment, String realizedTreatment) {
      this.typeOfPruning = typeOfPruning;
      this.suggestedTreatment = suggestedTreatment;
      this.realizedTreatment = realizedTreatment;
    }

    public Long getId() {
        return id;
    }

      // Tipp de poda del arbol
    public void setTypeOfPruning(String typeOfPruning) {
        this.typeOfPruning = typeOfPruning;
    }
     
    public String getTypeOfPruning() {
        return this.typeOfPruning;
    }
    
     //Tratamiento sugerido para el arbol
    public void setSuggestedTreatment(String suggestedTreatment) {
        this.suggestedTreatment = suggestedTreatment;
    }
     
    public String getSuggestedTreatment() {
        return this.suggestedTreatment;
    }

      // Tratamiento realizado al arbol
    public void setRealizedTreatment(String realizedTreatment) {
        this.realizedTreatment = realizedTreatment;
    }
     
    public String getRealizedTreatment() {
        return this.realizedTreatment;
    }

    // Arbol al que le pertenece el tratamiento
    public void setTree(Tree tree) {
        this.tree = tree;
    }
     
    public Tree getTree() {
        return this.tree;
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
        return "TreatmentTree [id=" + id + ", typeOfPruning=" + typeOfPruning + ", suggestedTreatment=" + suggestedTreatment + ", realizedTreatment=" + realizedTreatment + "]";
    }
}