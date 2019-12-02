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
@Table(name = "complaints", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
          "information"
        }),
        @UniqueConstraint(columnNames = {
          "id"
        })
})

@ApiModel(description = "All details about a complaint. ")
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "The database generated complaint ID")
    private Long id;
   
    @Column(name = "whistleblower")
    @ApiModelProperty(notes = "The whistleblower of the complaint")
    private String whistleblower;

    @Column(name = "information")
    @ApiModelProperty(notes = "Information about the complaint, can be any notes")
    private String information;

    @Column(name = "type")
    @ApiModelProperty(notes = "Type about the complaint, can be any notes")
    private String type;

    @Column(name = "imageLink")
    @ApiModelProperty(notes = "the link to an image that can be added to the complaint")
    private String imageLink;
    
    public Complaint() {
    }
   
    public Complaint(String whistleblower, String type, String information) {
      this.whistleblower = whistleblower;
      this.type = type;
      this.information = information;

    }

    public Long getId() {
        return id;
      }
    
      // nombre de la sub clasificacion
      public void setWhistleblower(String whistleblower) {
        this.whistleblower = whistleblower;
      }
     
      public String getWhistleblower() {
        return this.whistleblower;
      }

      // informacion de la sub clasificacion
      public void setInformation(String information) {
        this.information = information;
      }
     
      public String getInformation() {
        return this.information;
      }

      // type
      public void setType(String type) {
        this.type = type;
      }
     
      public String getType() {
        return this.type;
      }


    //Link de una imagen que se puede adjuntar a la queja
    public void setImageLink(String imageLink) {
      this.imageLink = imageLink;
    }
   
    public String getImageLink() {
      return this.imageLink;
    }


      @Override
    public String toString() {
        return "Complaint [id=" + id + ", whistleblower=" + whistleblower + ", information=" + information +", type="+type+", imageLink" + imageLink + "]";
    }

}