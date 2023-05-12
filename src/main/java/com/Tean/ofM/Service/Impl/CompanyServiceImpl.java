package com.Tean.ofM.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Tean.ofM.Model.AllService;
import com.Tean.ofM.Model.Company;
import com.Tean.ofM.Repository.CompanyRepository;
import com.Tean.ofM.Service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService{
	@Autowired
	public CompanyRepository companyRepository;
	@Override
	public List<AllService> serviceUse(Long id) {
		return companyRepository.serviceUse(id);
	}
	@Override
	public Company save(Company c) {
		return companyRepository.save(c);
	}
	@Override
	public List<Company> findAll() {
		return companyRepository.findAll();
	}

}
