package com.Tean.ofM.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Tean.ofM.Model.Admin;
import com.Tean.ofM.Repository.AdminRepository;
import com.Tean.ofM.Service.AdminService;

@Service 
  public class AdminServiceImpl implements AdminService{
  
	 @Autowired private AdminRepository adminRepository;
	
	@Override
	public Admin findStaffByUsernameAndPassword(String username, String password) {
		return adminRepository.findByUsernameAndPassword(username, password);
	}
	
	@Override
	public Admin saveStaff(Admin admin) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Admin> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
  
  
 