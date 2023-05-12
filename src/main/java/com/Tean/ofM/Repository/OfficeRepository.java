package com.Tean.ofM.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Tean.ofM.Model.Office;

@Repository
public interface OfficeRepository  extends JpaRepository<Office,Long> {
	
	/* @Query( "select o from Office o where building = ?1") */
	@Query(value = "select * from office o where building_id=?1 ", nativeQuery = true)
	List<Office> officeOfBuilding(Long id );
}
