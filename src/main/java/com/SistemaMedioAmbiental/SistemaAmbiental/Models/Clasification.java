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
@Table(name = "clasifications", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
          "name"
        }),
        @UniqueConstraint(columnNames = {
          "information"
        })
})

@ApiModel(description = "All details about a classification. ")
public class Clasification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "The database generated classification ID")
    private Long id;
   
    @Size(min=3, max = 50)
    @Column(name = "name")
    @ApiModelProperty(notes = "The name of the classification")
    private String name;

    @Size(min=3, max = 50)
    @Column(name = "information")
    @ApiModelProperty(notes = "Information about the classification, can be any notes")
    private String information;

    @Column(name = "imageLink")
    @ApiModelProperty(notes = "the link to an image of this clasification")
    private String imageLink;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subDistrict_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ApiModelProperty(notes = "The sub-district this classification belongs to")
    private SubDistrict subDistrict;
    
    public Clasification() {
    }
   
    public Clasification(String name, String information) {
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

      //Link de la imagen del arbol
      public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
      }
    
      public String getImageLink() {
        return this.imageLink;
      }
      // sub distrito
      public void setSubDistrict(SubDistrict subDistrict) {
        this.subDistrict = subDistrict;
      }
     
      public SubDistrict getSubDistrict() {
        return this.subDistrict;
      }

      @Override
    public String toString() {
        return "Clasification [id=" + id + ", name=" + name + ", information=" + information + ", imageLink" + imageLink +", subDistrict="+subDistrict+"]";
    }

}