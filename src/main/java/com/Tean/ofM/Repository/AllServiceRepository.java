package com.Tean.ofM.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Tean.ofM.Model.AllService;

@Repository
public interface AllServiceRepository extends JpaRepository<AllService, Long>{
	
	@Query(value = "SELECT s.* FROM all_service s JOIN company_service cs on s.service_id = cs.service_id WHERE cs.company_id = ?1 ", nativeQuery = true)
	List<AllService> serviceUse(Long id);
	
	
}
