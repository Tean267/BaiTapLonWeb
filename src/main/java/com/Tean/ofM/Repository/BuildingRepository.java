package com.Tean.ofM.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Tean.ofM.Model.Building;


@Repository
public interface BuildingRepository extends JpaRepository<Building,Long> {
	List<Building> findAll();
	
	/*Kiểu trả về của phương thức save là Product bởi vì sau khi lưu trữ thành công, phương thức sẽ trả về đối tượng 
	 * Product đã được lưu trữ. Điều này có thể hữu ích khi cần truy cập thông tin của đối tượng Product đã được lưu trữ 
	 * hoặc xác nhận rằng nó đã được lưu trữ thành công. */
	Building save(Building building);
	Optional<Building> findById(Long id);
	void delete(Building building);
	
}
