package com.Tean.ofM.Service;

import java.util.List;
import java.util.Optional;

import com.Tean.ofM.Model.Office;

public interface  OfficeService {
	List<Office> officeOfBuilding(Long id);
	
	Office findById(Long id);
	
	Office save(Office office);
	
	Office update(Office office);
	
}
