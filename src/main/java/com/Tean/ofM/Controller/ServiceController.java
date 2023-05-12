package com.Tean.ofM.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Tean.ofM.Model.AllService;
import com.Tean.ofM.Model.Invoice;
import com.Tean.ofM.Model.Office;
import com.Tean.ofM.Service.AllServiceService;
import com.Tean.ofM.Service.CompanyService;
import com.Tean.ofM.Service.InvoiceService;
import com.Tean.ofM.Service.OfficeService;
import com.Tean.ofM.Service.Impl.BuildingServiceImpl;

@Controller
public class ServiceController {
	@Autowired
	public BuildingServiceImpl buildingServiceImpl;
	
	@Autowired
	public CompanyService companyService;
	@Autowired
	public OfficeService officeService;
	
	@Autowired
	public AllServiceService allServiceService;
	
	@Autowired
	public InvoiceService invoiceService;
	@RequestMapping("/ListService")
	public String ListSerivce(Model model) {
		List<AllService> ListService = allServiceService.findAll();
		model.addAttribute("ListService",ListService);
		return "AllService";
		
	}
	
	@RequestMapping("/AddService")
	public String AddService(Model model) {
		AllService allService = new AllService();
		model.addAttribute("allService",allService);
		return "AddService";
		
	}
	@PostMapping("/ToAddService")
	public String ToAddService(@ModelAttribute("allService") AllService allService ,		
			 Model model) {
		allServiceService.save(allService);
		return "redirect:/ListService";
	}
	@RequestMapping("/EditService")
	public String EditService(Model model,@RequestParam Long service_id) {
		AllService editService = allServiceService.findById(service_id);
		model.addAttribute("editService",editService);
		return "EditService";
		
	}
	
	@PostMapping("/ToEditService")
	public String ToEditService(@ModelAttribute("editService") AllService editService ,		
			 Model model) {
		allServiceService.save(editService);
		return "redirect:/ListService";
	}
	
	
}
