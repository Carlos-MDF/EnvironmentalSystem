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
@Table(name = "subDistricts", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
          "name"
        }),
        @UniqueConstraint(columnNames = {
          "cod"
        })
})

@ApiModel(description = "All details about a sub-district. ")
public class SubDistrict {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "The database generated sub-district ID")
    private Long id;

    @Size(min = 3, max = 50)
    @Column(name = "name")
    @ApiModelProperty(notes = "The name of the sub-district")
    private String name;

    @Size(min = 1, max = 50)
    @Column(name = "cod")
    @ApiModelProperty(notes = "The code/number assigned to this sub-district by authorities")
    private String cod;

    @Size(min = 3, max = 50)
    @Column(name = "information")
    @ApiModelProperty(notes = "Information about the sub-district, can be any notes")
    private String information;

    @Column(name = "imageLink")
    @ApiModelProperty(notes = "the link to an image of this subdistrict")
    private String imageLink;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subDistrict_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ApiModelProperty(notes = "The district this sub-district belongs to")
    private District district;

    public SubDistrict() {
    }

    public SubDistrict(String name, String cod ,String information,String imageLink,District district) {
      this.name = name;
      this.information = information;
      this.cod=cod;
      this.district=district;
      this.imageLink = imageLink;
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

      //Link de la imagen del subDistrito
    public void setImageLink(String imageLink) {
      this.imageLink = imageLink;
    }
   
    public String getImageLink() {
      return this.imageLink;
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
        return "SubDistrict [id=" + id + ", name=" + name + "cod=" + cod + ", information=" + information + ", imageLink=" + imageLink + ", district=" + district + "]";
    }
}