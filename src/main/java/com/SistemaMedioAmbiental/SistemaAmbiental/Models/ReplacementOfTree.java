package com.SistemaMedioAmbiental.SistemaAmbiental.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "replacementOfTrees", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
          "id"
        })
})

@ApiModel(description = "All details about a replacementOfTree.")
public class ReplacementOfTree {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "The database generated replacementOfTree ID")
    private Long id;
   
    @Column(name = "typeOfReplacement")
    @ApiModelProperty(notes = "The type of replacement the tree has")
    private String typeOfReplacement;

    @Column(name = "eliminationOfCompetition")
    @ApiModelProperty(notes = "The reason for the elimination of the competition")
    private String eliminationOfCompetition;

    @Column(name = "dateOfReplacement")
    @ApiModelProperty(notes = "The date of the replacement")
    private String dateOfReplacement;

    @Column(name = "reasonForTheReplacement")
    @ApiModelProperty(notes = "The reason for the replacement")
    private String reasonForTheReplacement;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tree_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ApiModelProperty(notes = "The tree this replacement belongs to")
    private Tree tree;
    
    public ReplacementOfTree() {
    }
   
    public ReplacementOfTree(String typeOfReplacement, String eliminationOfCompetition, String dateOfReplacement, String reasonForTheReplacement) {
      this.typeOfReplacement = typeOfReplacement;
      this.eliminationOfCompetition = eliminationOfCompetition;
      this.dateOfReplacement = dateOfReplacement;
      this.reasonForTheReplacement = reasonForTheReplacement;
    }

    public Long getId() {
        return id;
    }

    // Tipo de poda del arbol
    public void setTypeOfReplacement(String typeOfReplacement) {
        this.typeOfReplacement = typeOfReplacement;
    }
     
    public String getTypeOfReplacement() {
        return this.typeOfReplacement;
    }
    
    //Tratamiento sugerido para el arbol
    public void setEliminationOfCompetition(String eliminationOfCompetition) {
        this.eliminationOfCompetition = eliminationOfCompetition;
    }
     
    public String getEliminationOfCompetition() {
        return this.eliminationOfCompetition;
    }

    // Tratamiento realizado al arbol
    public void setReasonForTheReplacement(String reasonForTheReplacement) {
        this.reasonForTheReplacement = reasonForTheReplacement;
    }
     
    public String getReasonForTheReplacement() {
        return this.reasonForTheReplacement;
    }

    // Tratamiento realizado al arbol
    public void setDateOfReplacement(String dateOfReplacement) {
        this.dateOfReplacement = dateOfReplacement;
    }
     
    public String getDateOfReplacement() {
        return this.dateOfReplacement;
    }

    // Arbol al que le pertenece el tratamiento
    public void setTree(Tree tree) {
        this.tree = tree;
    }
     
    public Tree getTree() {
        return this.tree;
    }

    @Override
    public String toString() {
        return "ReplacementOfTree [id=" + id + ", typeOfReplacement=" + typeOfReplacement + ", eliminationOfCompetition=" + eliminationOfCompetition + ", dateOfReplacement=" + dateOfReplacement + ", reasonForTheReplacement=" + reasonForTheReplacement + "]";
    }
}