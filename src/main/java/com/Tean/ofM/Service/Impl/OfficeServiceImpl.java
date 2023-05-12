package com.Tean.ofM.Service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Tean.ofM.Model.Office;
import com.Tean.ofM.Repository.OfficeRepository;
import com.Tean.ofM.Service.OfficeService;

@Service
public class OfficeServiceImpl implements OfficeService{
	
	@Autowired
	public OfficeRepository officeRepository;
	@Override
	public List<Office> officeOfBuilding(Long id) {
		return officeRepository.officeOfBuilding(id);
	}
	
	public Office findById(Long id) {
		Optional<Office> office = officeRepository.findById(id);
		if(office.isPresent()){
			return office.get();
		}
		else {
			
		}
		return null;
	}
	@Override
	public Office save(Office office) {
		return officeRepository.save(office);
	}
	@Override
	public Office update(Office office) {
		Office officeUpdate = findById(office.getId());
		officeUpdate.setCompany(office.getCompany());
		officeUpdate.setName(office.getName());
		officeUpdate.setDescription(office.getDescription());
		officeUpdate.setPrice(office.getPrice());
		officeUpdate.setContractDate(office.getContractDate());
		return save(officeUpdate);
		
	}

}
