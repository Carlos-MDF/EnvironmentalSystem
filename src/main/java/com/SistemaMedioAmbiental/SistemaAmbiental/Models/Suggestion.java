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
   
    @Column(name = "user")
    @ApiModelProperty(notes = "The user of the suggestion")
    private String user;

    @Column(name = "information")
    @ApiModelProperty(notes = "Information about the suggestion, can be any notes")
    private String information;

    @Column(name = "type")
    @ApiModelProperty(notes = "Type about the suggestion, can be any notes")
    private String type;
    
    public Suggestion() {
    }
   
    public Suggestion(String user, String type, String information) {
      this.user = user;
      this.type = type;
      this.information = information;

    }

    public Long getId() {
        return id;
      }
    
      // Usuario que hace la sugerencia
      public void setUser(String user) {
        this.user = user;
      }
     
      public String getUser() {
        return this.user;
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
        return "Suggestion [id=" + id + ", user=" + user + ", information=" + information +", type=" + type + "]";
    }
}