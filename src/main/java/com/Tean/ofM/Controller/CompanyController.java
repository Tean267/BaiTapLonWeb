package com.Tean.ofM.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Tean.ofM.Model.Office;
import com.Tean.ofM.Model.AllService;
import com.Tean.ofM.Service.AllServiceService;
import com.Tean.ofM.Service.CompanyService;
import com.Tean.ofM.Service.OfficeService;

@Controller
public class CompanyController {

	@Autowired
	public OfficeService officeService;
	@Autowired
	public AllServiceService allServiceService;
	@RequestMapping("/DetailService")
	public String Detail(@RequestParam Long id,Model model) {
		Office office = officeService.findById(id);
		if(office.getCompany() == null) {
			return "Service";
		}
		else {
			List<AllService> serviceUse = allServiceService.serviceUse(office.getCompany().getId());
			model.addAttribute("serviceUse",serviceUse);
			return "Service";
		}		

	}
}
