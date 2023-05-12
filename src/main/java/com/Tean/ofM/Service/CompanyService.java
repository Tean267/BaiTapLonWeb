package com.Tean.ofM.Service;


import java.util.List;

import com.Tean.ofM.Model.AllService;
import com.Tean.ofM.Model.Company;

public interface CompanyService {
	List<AllService> serviceUse(Long id);
	
	Company save(Company c);
	
	List<Company> findAll();
}
