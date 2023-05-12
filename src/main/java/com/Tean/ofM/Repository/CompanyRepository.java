package com.Tean.ofM.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Tean.ofM.Model.Company;
import com.Tean.ofM.Model.Office;
import com.Tean.ofM.Model.AllService;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
	
	@Query(value = "SELECT * FROM all_service s JOIN company_service cs on s.service_id = cs.service_id  WHERE cs.company_id = ?1 ", nativeQuery = true)
	List<AllService> serviceUse(Long id);
	
	/*
	 * @Query(value = "SELECT company_id FROM office where office_id ?1 ",
	 * nativeQuery = true) List<Office> officeOfBuilding(Long id );
	 */
}
