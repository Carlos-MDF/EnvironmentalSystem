package com.SistemaMedioAmbiental.SistemaAmbiental.Models;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "customer")
public class User {
 
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
 
  @Column(name = "name")
  private String name;
 
  @Column(name = "password")
  private String password;
 
  @Column(name = "passwordConfirm")
  private String passwordConfirm;

  @Column(name = "email")
  private String email;

  @Column(name = "phone")
  private String phone;

  @Column(name = "ci")
  private long ci;

  @Column(name = "address")
  private String address;

  @Column(name = "referencePerson")
  private ArrayList<String> referencePerson = new ArrayList<String>();


  public User() {
  }
 
  public User(String name, String password, String passwordConfirm, String email, String phone, long ci, String address, ArrayList<String> referencePerson) {
    this.name = name;
    this.password = password;
    this.passwordConfirm = passwordConfirm;
    this.email = email;
    this.phone = phone;
    this.ci = ci;
    this.address = address;
    this.referencePerson = referencePerson;
  }
 
  public long getId() {
    return id;
  }

  //Nombre
  public void setName(String name) {
    this.name = name;
  }
 
  public String getName() {
    return this.name;
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

 public long getCi(){
   return this.ci;
 }

 public void setCi(long ci){
   this.ci = ci;
 }

 //Direccion

 public String getAddres(){
   return this.address;
 }

 public void setAddress(String address){
   this.address = address;
 }

 //Persona de referencia
 public ArrayList<String> getReferencePerson(){
   return this.referencePerson;
 }

 public void setReferencePerson(ArrayList<String> referencePerson){
   this.referencePerson = referencePerson;
 }

 @Override
    public String toString() {
        return "User [id=" + id + ", password=" + password + ", passwordConfirm=" + passwordConfirm + ", email=" + email + ", phone=" + phone + ", ci=" + ci + ", address=" + address + ", referencePerson=" + referencePerson
       + "]";
    }
}