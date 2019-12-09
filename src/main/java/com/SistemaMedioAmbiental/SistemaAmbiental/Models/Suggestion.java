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
@Table(name = "suggestions", uniqueConstraints = {
    @UniqueConstraint(columnNames = {
        "id"
    })
})

@ApiModel(description = "All details about a suggestion.")
public class Suggestion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "The database generated suggestion ID")
    private Long id;
   
    @Column(name = "person")
    @ApiModelProperty(notes = "The person of the suggestion")
    private String person;

    @Column(name = "information")
    @ApiModelProperty(notes = "Information about the suggestion, can be any notes")
    private String information;

    @Column(name = "type")
    @ApiModelProperty(notes = "Type about the suggestion, can be any notes")
    private String type;
    
    public Suggestion() {
    }
   
    public Suggestion(String person, String type, String information) {
      this.person = person;
      this.type = type;
      this.information = information;

    }

    public Long getId() {
        return id;
      }
    
      // Usuario que hace la sugerencia
      public void setPerson(String person) {
        this.person = person;
      }
     
      public String getPerson() {
        return this.person;
      }

      // Informacion de la sugerencia
      public void setInformation(String information) {
        this.information = information;
      }
     
      public String getInformation() {
        return this.information;
      }

      // Tipo de sugerencia proporcionada
      public void setType(String type) {
        this.type = type;
      }
     
      public String getType() {
        return this.type;
      }


      @Override
    public String toString() {
        return "Suggestion [id=" + id + ", person=" + person + ", information=" + information +", type=" + type + "]";
    }
}