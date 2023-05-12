package com.Tean.ofM.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Tean.ofM.Model.Admin;

public interface AdminRepository  extends JpaRepository<Admin,Long>{
	
	 @Query("SELECT u FROM Admin u WHERE u.username = :username AND u.password = :password")
	    Admin findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
	 
	
}
