package com.Tean.ofM.Model;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Office {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="office_id")
	private Long id;
	private String name;
	private String price;
	private String description;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "building_id", referencedColumnName = "building_id")
	private Building building;
	
	private Date contractDate;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "company_id", referencedColumnName = "company_id")		
	private Company company;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "office")
	private List<Invoice> invoices;
	
}
