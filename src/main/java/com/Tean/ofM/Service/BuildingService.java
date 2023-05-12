package com.Tean.ofM.Service;

import java.util.List;

import com.Tean.ofM.Model.Building;


public interface BuildingService {
	List<Building> findAllBuilding();
	Building saveBuilding(Building building);
	Building findByIdBuilding(Long id);
	void UpdateBuilding(Building building,Long id);
	void DeleteBuilding(Long id);
	
}
