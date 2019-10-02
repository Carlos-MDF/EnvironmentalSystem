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
@Table(name = "subClasifications", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
          "name"
        }),
        @UniqueConstraint(columnNames = {
          "information"
        })
})

@ApiModel(description = "All details about a sub-classification. ")
public class SubClasification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "The database generated sub-classification ID")
    private Long id;
   
    @Size(min=3, max = 50)
    @Column(name = "name")
    @ApiModelProperty(notes = "The name of the sub-classification")
    private String name;

    @Size(min=3, max = 50)
    @Column(name = "information")
    @ApiModelProperty(notes = "Information about the sub-classification, can be any notes")
    private String information;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clasification_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ApiModelProperty(notes = "The classification this sub-classification belongs to")
    private Clasification clasification;


    public SubClasification() {
    }
   
    public SubClasification(String name, String information) {
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

      // clasification
      public void setClasification(Clasification clasification) {
        this.clasification = clasification;
      }
     
      public Clasification getClasification() {
        return this.clasification;
      }

      @Override
    public String toString() {
        return "SubClasification [id=" + id + ", name=" + name + ", information=" + information +"clasification"+clasification+"]";
    }

}