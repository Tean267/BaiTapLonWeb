package com.Tean.ofM.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Tean.ofM.Model.Admin;
import com.Tean.ofM.Model.Building;
import com.Tean.ofM.Model.Invoice;
import com.Tean.ofM.Model.Office;
import com.Tean.ofM.Service.AdminService;

@Controller
public class AdminController {
	@Autowired
	public AdminService adminService;
	@RequestMapping("/")
	public String home(Model model) {
		model.addAttribute("admin",new Admin());
		return "Login.html";
	}
	
	@PostMapping("/ToLogin")
	public String ToInvoice(@ModelAttribute("admin") Admin admin ,Model model, RedirectAttributes attributes) {
		Admin adminCheck = adminService.findStaffByUsernameAndPassword(admin.getUsername(), admin.getPassword());
		if(adminCheck == null) {
			attributes.addFlashAttribute("mess","Tên đăng nhập hoặc mật khẩu sai");
			return "redirect:/";
		}
		return "redirect:/ShowBulding";
	}
}
/*
 * @RestController public class StaffController {
 * 
 * @Autowired public StaffService staffService;
 * 
 * @GetMapping("/api/example") public List<Staff> getExample() { List<Staff>
 * ListStaff = staffService.findAll(); return ListStaff;
 * 
 * } }
 * 
 */