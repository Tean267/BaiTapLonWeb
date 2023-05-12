package com.Tean.ofM.Service;

import java.util.List;

import com.Tean.ofM.Model.Admin;

public interface AdminService {
	  Admin findStaffByUsernameAndPassword(String username, String password);
	  Admin saveStaff(Admin admin);       
	  List<Admin> findAll();
}
