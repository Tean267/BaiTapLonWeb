package com.Tean.ofM.Model;


import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Company {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="company_id")
	private Long id;
	private String name;
	private String description;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "company")
	private List<Office> offices;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name ="company_service", joinColumns = @JoinColumn(name ="company_id",referencedColumnName ="company_id"),
			  inverseJoinColumns = @JoinColumn(name ="service_id",referencedColumnName = "service_id"))
	private List<AllService> services;
}
