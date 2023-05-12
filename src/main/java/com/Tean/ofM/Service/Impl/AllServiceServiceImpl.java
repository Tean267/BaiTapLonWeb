package com.Tean.ofM.Service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Tean.ofM.Model.AllService;
import com.Tean.ofM.Model.Office;
import com.Tean.ofM.Repository.AllServiceRepository;
import com.Tean.ofM.Service.AllServiceService;

@Service
public class AllServiceServiceImpl implements  AllServiceService{

	@Autowired
	public AllServiceRepository allServiceRepository;
	@Override
	public List<AllService> serviceUse(Long id) {
		return allServiceRepository.serviceUse(id);
	}
	@Override
	public List<AllService> findAll() {
		return allServiceRepository.findAll();
	}
	@Override
	public AllService findById(Long id) {
		Optional<AllService> allService = allServiceRepository.findById(id);
		if(allService.isPresent()){
			return allService.get();
		}
		else {
			
		}
		return null;
	}
	@Override
	public AllService save(AllService allService) {
		return allServiceRepository.save(allService);
	}
	@Override
	public void delete(Long id) {
		 allServiceRepository.deleteById(id);;
		
	}

}
