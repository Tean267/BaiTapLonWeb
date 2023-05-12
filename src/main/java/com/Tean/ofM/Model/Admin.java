package com.Tean.ofM.Model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

 @Data 
 @NoArgsConstructor 
 @AllArgsConstructor
 @Entity 
 public class Admin {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name ="admin_id") 
  private Long id; 
  private String username; 
  private String password;
  
  @OneToMany(cascade = CascadeType.ALL,mappedBy = "admin")
  private List<Building> buildings;
}