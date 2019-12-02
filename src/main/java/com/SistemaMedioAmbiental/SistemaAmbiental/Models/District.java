package com.SistemaMedioAmbiental.SistemaAmbiental.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "districts", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
          "name"
        }),
        @UniqueConstraint(columnNames = {
          "information"
        })
})

@ApiModel(description = "All details about a District. ")
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "The database generated district ID")

    private Long id;

    @Size(min = 1, max = 50)
    @Column(name = "name")
    @ApiModelProperty(notes = "The name of the district")
    private String name;


    @Size(min = 3, max = 50)
    @Column(name = "information")
    @ApiModelProperty(notes = "The information of a district")
    private String information;

    @Column(name = "imageLink")
    @ApiModelProperty(notes = "the link to an image of this district")
    private String imageLink;


    public District() {
    }

    public District(String name, String information) {
      this.name = name;
      this.information = information;
    }

    public Long getId() {
        return id;
      }
    
      // nombre de la sub clasificacion
      public void setName(String name) {
        this.name = name;
      }
     
      public String getName() {
        return this.name;
      }


      // informacion de la sub clasificacion
      public void setInformation(String information) {
        this.information = information;
      }
     
      public String getInformation() {
        return this.information;
      }

          //Link de la imagen del distrito
    public void setImageLink(String imageLink) {
      this.imageLink = imageLink;
    }
   
    public String getImageLink() {
      return this.imageLink;
    }

      @Override
    public String toString() {
        return "District [id=" + id + ", name=" + name +", information=" + information + ", imageLink=" + imageLink +"]";
    }

}