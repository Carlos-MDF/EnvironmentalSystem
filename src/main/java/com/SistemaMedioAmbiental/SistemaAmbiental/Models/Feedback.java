package com.SistemaMedioAmbiental.SistemaAmbiental.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "feedbacks", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
          "id"
        })
})

@ApiModel(description = "All details about a feedback.")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "The database generated feedback ID")
    private Long id;

    @Column(name = "person")
    @ApiModelProperty(notes = "The person which the feedback belongs to")
    private String person;
   
    @Column(name = "treeSpecies")
    @ApiModelProperty(notes = "The species which the tree belongs to")
    private String treeSpecies;

    @Column(name = "flowering")
    @ApiModelProperty(notes = "The flowering that the tree has")
    private String flowering;

    @Column(name = "fructification")
    @ApiModelProperty(notes = "The fructification that the tree has")
    private String fructification;

    @Column(name = "defoliation")
    @ApiModelProperty(notes = "The defoliation of the tree")
    private String defoliation;

    @Column(name = "foliation")
    @ApiModelProperty(notes = "The foliation of the tree")
    private String foliation;

    @Column(name = "imageLink")
    @ApiModelProperty(notes = "The link to an image that can be added to the suggestion")
    private String imageLink;
    
    public Feedback() {
    }
   
    public Feedback(String person, String treeSpecies, String flowering, String fructification, String defoliation, String foliation) {
      this.person = person;
      this.treeSpecies = treeSpecies;
      this.flowering = flowering;
      this.fructification = fructification;
      this.defoliation = defoliation;
      this.foliation = foliation;
    }

    public Long getId() {
        return id;
      }

      // Usuario que hace la retralimentacion
      public void setPerson(String person) {
        this.person = person;
      }
     
      public String getPerson() {
        return this.person;
      }
    
     //Especie del arbol
      public void setTreeSpecies(String treeSpecies) {
        this.treeSpecies = treeSpecies;
      }
     
      public String getTreeSpecies() {
        return this.treeSpecies;
      }

      // Informacion de la sugerencia
      public void setFlowering(String flowering) {
        this.flowering = flowering;
      }
     
      public String getFlowering() {
        return this.flowering;
      }

       // Informacion de la sugerencia
      public void setFructification(String fructification) {
        this.fructification = fructification;
      }
     
      public String getFructification() {
        return this.fructification;
      }

      // Tipo de sugerencia proporcionada
      public void setDefoliation(String defoliation) {
        this.defoliation = defoliation;
      }
     
      public String getDefoliation() {
        return this.defoliation;
      }

       // Tipo de sugerencia proporcionada
       public void setFoliation(String foliation) {
        this.foliation = foliation;
      }
     
      public String getFoliation() {
        return this.foliation;
      }


    //Link de una imagen que se puede adjuntar a la sugerencia
    public void setImageLink(String imageLink) {
      this.imageLink = imageLink;
    }
   
    public String getImageLink() {
      return this.imageLink;
    }


    @Override
    public String toString() {
        return "Feedback [id=" + id + ", person=" + person + ", treeSpecies=" + treeSpecies + ", flowering=" + flowering +", fructification=" + fructification + ", defoliation=" + foliation + ", foliation=" + defoliation + ", imageLink" + imageLink + "]";
    }

}