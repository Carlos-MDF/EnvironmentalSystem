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
@Table(name = "subDistricts", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
          "name"
        }),
        @UniqueConstraint(columnNames = {
          "cod"
        })
})

public class SubDistrict {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 3, max = 50)
    @Column(name = "name")
    private String name;

    @Size(min = 1, max = 50)
    @Column(name = "cod")
    private String cod;

    @Size(min = 3, max = 50)
    @Column(name = "information")
    private String information;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subDistrict_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private District district;

    public SubDistrict() {
    }

    public SubDistrict(String name, String cod ,String information,District district) {
      this.name = name;
      this.information = information;
      this.cod=cod;
      this.district=district;
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

    // nombre de la sub Codigo
    public void setCod(String cod) {
        this.cod = cod;
    }
         
    public String getCod() {
        return this.cod;
    }

      // informacion de la sub clasificacion
      public void setInformation(String information) {
        this.information = information;
      }
     
      public String getInformation() {
        return this.information;
      }

      // distrito
      public void setDistrict(District district) {
        this.district= district;
      }
     
      public District getDistrict() {
        return this.district;
      }


      @Override
    public String toString() {
        return "SubDistrict [id=" + id + ", name=" + name + "cod="+cod+", information=" + information +", district="+district+"]";
    }

}