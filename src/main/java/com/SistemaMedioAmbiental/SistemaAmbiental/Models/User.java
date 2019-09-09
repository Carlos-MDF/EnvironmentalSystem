package com.SistemaMedioAmbiental.SistemaAmbiental.Models;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.persistence.JoinColumn;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.NaturalId;


@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
          "username"
        }),
        @UniqueConstraint(columnNames = {
          "email"
        })
})
public class User {
 
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
 
  @NotBlank
  @Size(min=3, max = 50)
  @Column(name = "name")
  private String name;
 
  @NotBlank
  @Size(min=3, max = 50)
  @Column(name = "username")
  private String username;

  @NotBlank
  @Size(min=6, max = 100)
  @Column(name = "password")
  private String password;
 
  @NotBlank
  @Size(min=6, max = 100)
  @Column(name = "passwordConfirm")
  private String passwordConfirm;

  @NaturalId
  @NotBlank
  @Size(max = 50)
  @Email
  @Column(name = "email")
  private String email;

  @Size(min=3, max = 50)
  @Column(name = "phone")
  private String phone;

  @Column(name = "ci")
  private Long ci;

  @Size(min=6, max = 100)
  @Column(name = "address")
  private String address;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "user_roles", 
    joinColumns = @JoinColumn(name = "user_id"), 
    inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();


  public User() {
  }
 
  public User(String name, String username, String password, String passwordConfirm, String email, String phone, Long ci, String address) {
    this.name = name;
    this.username = username;
    this.password = password;
    this.passwordConfirm = passwordConfirm;
    this.email = email;
    this.phone = phone;
    this.ci = ci;
    this.address = address;
  }
 
  public Long getId() {
    return id;
  }

  //Nombre
  public void setName(String name) {
    this.name = name;
  }
 
  public String getName() {
    return this.name;
  }

  //Nombre de usuario
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  //Contraseña
  public String getPassword(){
    return this.password;
  }

  public void setPassword(String password){
    this.password = password;
  }

  //Confirmar Contraseña
  public String getPasswordConfirm(){
    return this.password;
  }

  public void setPasswordConfirm(String passwordConfirm){
    this.passwordConfirm = passwordConfirm;
  }

  //Email

  public String getEmail(){
    return this.email;
  }

  public void setEmail(String email){
    this.email = email;
  }

  //Telefono

  public String getPhone(){
    return this.phone;
 }

 public void setPhone(String phone){
   this.phone = phone;
 }

 //Ci

 public Long getCi(){
   return this.ci;
 }

 public void setCi(Long ci){
   this.ci = ci;
 }

 //Direccion

 public String getAddres(){
   return this.address;
 }

 public void setAddress(String address){
   this.address = address;
 }

 //Rol

 public Set<Role> getRoles() {
  return roles;
}

public void setRoles(Set<Role> roles) {
  this.roles = roles;
}

 @Override
    public String toString() {
        return "User [id=" + id + ", password=" + password + ", passwordConfirm=" + passwordConfirm + ", email=" + email + ", phone=" + phone + ", ci=" + ci + ", address=" + address 
       + "]";
    }
}