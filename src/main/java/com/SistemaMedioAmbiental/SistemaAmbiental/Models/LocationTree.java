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

@Entity
@Table(name = "locationTrees", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
          "name"
        }),
        @UniqueConstraint(columnNames = {
          "information"
        })
})

public class LocationTree {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
   
    
    @Size(min=3, max = 50)
    @Column(name = "name")
    private String name;

    @Size(min=3, max = 50)
    @Column(name = "information")
    private String information;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subClasification_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private SubClasification subClasification;


    public LocationTree() {
    }
   
    public LocationTree(String name, String information,SubClasification subClasification) {
      this.name = name;
      this.information = information;
      this.subClasification=subClasification;
    }

    public Long getId() {
        return id;
      }
    
      // nombre de la locacion
      public void setName(String name) {
        this.name = name;
      }
     
      public String getName() {
        return this.name;
      }

      // informacion de la locacion
      public void setInformation(String information) {
        this.information = information;
      }
     
      public String getInformation() {
        return this.information;
      }
      
      // sub clasificacion
      public void setSubClasification(SubClasification subClasification) {
        this.subClasification = subClasification;
      }
     
      public SubClasification getSubClasification() {
        return this.subClasification;
      }

      @Override
    public String toString() {
        return "LocationTree [id=" + id + ", name=" + name + ", information=" + information +", subClasification:"+subClasification+"]";
    }

}