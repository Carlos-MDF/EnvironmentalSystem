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
@Table(name = "clasifications", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
          "name"
        }),
        @UniqueConstraint(columnNames = {
          "information"
        })
})

public class Clasification {

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
    @JoinColumn(name = "subDistrict_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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

      // sub distrito
      public void setSubDistrict(SubDistrict subDistrict) {
        this.subDistrict = subDistrict;
      }
     
      public SubDistrict getSubDistrict() {
        return this.subDistrict;
      }

      @Override
    public String toString() {
        return "Clasification [id=" + id + ", name=" + name + ", information=" + information +", subDistrict="+subDistrict+"]";
    }

}