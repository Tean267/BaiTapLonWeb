package com.Tean.ofM.Controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Tean.ofM.Model.AllService;
import com.Tean.ofM.Model.Building;
import com.Tean.ofM.Model.Company;
import com.Tean.ofM.Model.Invoice;
import com.Tean.ofM.Model.Office;
import com.Tean.ofM.Repository.AllServiceRepository;
import com.Tean.ofM.Repository.InvoiceRepository;
import com.Tean.ofM.Service.AllServiceService;
import com.Tean.ofM.Service.BuildingService;
import com.Tean.ofM.Service.CompanyService;
import com.Tean.ofM.Service.InvoiceService;
import com.Tean.ofM.Service.OfficeService;
import com.Tean.ofM.Service.Impl.BuildingServiceImpl;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class OfficeController {
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
	
	@RequestMapping("/DetailOffice")
	public String Detail(@RequestParam Long id,Model model) {
		List<Office> OfficeOfBuilding  = officeService.officeOfBuilding(id);
		model.addAttribute("OfficeOfBuilding",OfficeOfBuilding);
		model.addAttribute("office_id",id);
		return "Office";
	}
	
	@RequestMapping("/Contract")
	public String Contract(@RequestParam Long id,Model model) {
		Office office = officeService.findById(id);
		Company NewCompany = new Company();
		List<AllService> allService = allServiceService.findAll();
		model.addAttribute("office",office);
		model.addAttribute("allService",allService);	
		model.addAttribute("NewCompany",NewCompany);	
	
		return "Contract";
	}
	
	@PostMapping("/ToContract")
	public String ToContract(@ModelAttribute("NewCompany") Company NewCompany,
							@RequestParam Long office_id,							
						    HttpServletRequest request
							,Model model) {	
		
		Office office = officeService.findById(office_id);
		String[] selectedServices = request.getParameterValues("selectedServices");
		
		String contractDate = request.getParameter("contractDate");	
		List<AllService> allService = new ArrayList<AllService>() ;
		for(String s : selectedServices ) {
			allService.add(allServiceService.findById(Long.valueOf(s)));	
		}
		NewCompany.setServices(allService);
		companyService.save(NewCompany);
		office.setCompany(NewCompany);
		// chuyển ngày từ kiểu String sang kiểu java.sql.Date 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			java.util.Date utilDate;
			utilDate = dateFormat.parse(contractDate);
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			office.setContractDate(sqlDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		officeService.update(office);
		/*
		 * for(String s: selectedServices) { System.out.println(s); }
		 */
//		List<Company> company = companyService.findAll();
		model.addAttribute("office",office);
	// láy id_building toa nha de tra ve danh sach office
	List<Building> building = buildingServiceImpl.findAllBuilding();	
	Long building_id =(long) 0;		
			for(Building b : building) {	
				for(Office o : b.getOffices()) {
					if(o.equals(office)) {
						building_id = b.getId();
						break;
					}
				}
				
			}
			return "redirect:/DetailOffice?id="+building_id;
	
	}
	
	@RequestMapping("/Invoice")
	public String Invoice(@RequestParam Long id,Model model) {
		Invoice invoice = new Invoice();
		Office office = officeService.findById(id);
		invoice.setOffice(office);
		List<AllService> serviceUse = allServiceService.serviceUse(office.getCompany().getId());
		Double totalMoney = (double) 0;
		for(AllService allservice : serviceUse) {
			totalMoney=totalMoney+allservice.getPrice();
		}
		totalMoney = totalMoney+ Integer.parseInt(office.getPrice());
		invoice.setTotalMoney(totalMoney);
		model.addAttribute("totalMoneyy",totalMoney);
		model.addAttribute("serviceUse",serviceUse);
		model.addAttribute("invoice",invoice);
		return "Invoice";
	}
	@PostMapping("/ToInvoice")
	public String ToInvoice(@ModelAttribute("invoice") Invoice invoice ,
			@RequestParam Long office_id,
			 Model model) {
	Office office = officeService.findById(office_id);
	
		invoice.setOffice(office);
		invoiceService.save(invoice);
		List<Building> building = buildingServiceImpl.findAllBuilding();
		Long building_id =(long) 0;
		
		for(Building b : building) {	
			for(Office o : b.getOffices()) {
				if(o.equals(office)) {
					building_id = b.getId();
					break;
				}
			}
			
		}
		return "redirect:/DetailOffice?id="+building_id;
	}
	@RequestMapping("/ListInvoice")
	public String ListInvoice(Model model) {
		List<Invoice> listInvoice = invoiceService.findAll();
		model.addAttribute("listInvoice",listInvoice);
		return "ListInvoice";
	}
	@RequestMapping("/Enable")
	public String Enable(@RequestParam Long invoice_id ,
			 			Model model) {

		
		Invoice invoice = invoiceService.findById(invoice_id);
		invoice.setEnable(false);
		invoiceService.save(invoice);
		return "redirect:/ListInvoice";
	}
	@RequestMapping("/UnEnable")
	public String UnEnable(@RequestParam Long invoice_id ,
			 			Model model) {

		LocalDate currentDate = LocalDate.now();
		Date paymentDate = Date.valueOf(currentDate);
		Invoice invoice = invoiceService.findById(invoice_id);
		invoice.setPaymentDate(paymentDate);
		invoice.setEnable(true);
		invoiceService.save(invoice);
		return "redirect:/ListInvoice";
		
	}
	@RequestMapping("/Stat")
	public String ThongKe(Model model) {
		List<Invoice> listInvoices = invoiceService.InvoiceOfMonth(5,2023);
		model.addAttribute("listInvoices",listInvoices);
		return "ThongKe";
		
	}
	@PostMapping("/SearchStat")
	public String ThongKee(   HttpServletRequest request,Model model) {
		String dateSearch = request.getParameter("Date");
		int date_month;
		if(dateSearch.charAt(0)=='0') {
			date_month = Integer.parseInt(dateSearch.substring(1, 2));
		}
		else {
			date_month = Integer.parseInt(dateSearch.substring(0, 2));
		}
		int date_year = Integer.parseInt(dateSearch.substring(3));
		List<Invoice> listInvoices = invoiceService.InvoiceOfMonth(date_month,date_year);
		model.addAttribute("listInvoices",listInvoices);
		return "ThongKe";
		
	}

	@RequestMapping("/DeleteContract/{office_id}")
	public String DeleteContract(@PathVariable("office_id") Long office_id, @RequestParam Long id,Model model) {
		Office office = officeService.findById(id);
		office.setCompany(null);
		office.setContractDate(null);
		officeService.save(office);		
		return "redirect:/DetailOffice?id="+office_id;
	}
	@RequestMapping("/AddOffice/{building_id}")
	public String AddOffice(@PathVariable("building_id") Long building_id,Model model) {
		Office NewOffice = new Office();
		model.addAttribute("NewOffice",NewOffice);
		model.addAttribute("building_id",building_id);
		return "AddOffice";
		
	}
	@PostMapping("/To-AddOffice")
	public String To_AddOffice(@RequestParam("building_id") Long building_id,@ModelAttribute("NewOffice") Office NewOffice,Model model) {
		Building building = buildingServiceImpl.findByIdBuilding(building_id);
		NewOffice.setBuilding(building);
		officeService.save(NewOffice);
		return "redirect:/DetailOffice?id="+building_id;
		
	}
	@RequestMapping("/EditOffice/{building_id}")
	public String EditOffice(@PathVariable("building_id") Long building_id, @RequestParam Long id,Model model) {
		Office EditOffice = officeService.findById(id);
		model.addAttribute("EditOffice",EditOffice);
		model.addAttribute("building_id",building_id);
		return "EditOffice";
		
	}
	@PostMapping("/To-EditOffice")
	public String To_EditOffice(@RequestParam("building_id") Long building_id,@ModelAttribute("EditOffice") Office EditOffice,Model model) {
//		List<AllService> serviceUse = companyService.serviceUse(EditOffice.getCompany().getId());	
//		EditOffice.getCompany().setServices(serviceUse);
		officeService.save(EditOffice);
		return "redirect:/DetailOffice?id="+building_id;
		
	}
	

}
