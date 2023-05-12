package com.Tean.ofM.Service;

import java.util.List;

import com.Tean.ofM.Model.AllService;

public interface AllServiceService {
	
	List<AllService> serviceUse(Long id);
	AllService findById(Long id);
	List<AllService> findAll();
	AllService save(AllService allService);
	void delete(Long id);
}
