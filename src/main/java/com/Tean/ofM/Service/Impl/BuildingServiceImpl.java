package com.Tean.ofM.Service.Impl;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Tean.ofM.Model.Building;
import com.Tean.ofM.Repository.BuildingRepository;
import com.Tean.ofM.Service.BuildingService;


@Service
public class BuildingServiceImpl implements BuildingService{

		@Autowired
		public BuildingRepository buildingRepository;

		public List<Building> findAllBuilding() {
			return buildingRepository.findAll();
		}

		@Override
		public Building saveBuilding(Building building) {
			return buildingRepository.save(building);
		}

		@Override
		public Building findByIdBuilding(Long id) {
			Optional<Building> product = buildingRepository.findById(id);
			if(product.isPresent()){
				return product.get();
			}
			else {
				
			}
			return null;
		}

		@Override
		public void UpdateBuilding(Building building,Long id) {
//			Building p = findByIdBuilding(code);
//			p.setCode(building.getId());
//			p.setDescription(building.getDescription());
//			p.setPrice(building.getPrice());
//			saveBuilding(p);
//			
		}

		@Override
		public void DeleteBuilding(Long id) {
			Building product = findByIdBuilding(id);
			buildingRepository.delete(product);
			
		}

	

	
}
