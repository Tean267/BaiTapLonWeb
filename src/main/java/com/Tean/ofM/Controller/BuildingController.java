package com.Tean.ofM.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Tean.ofM.Model.Building;
import com.Tean.ofM.Service.Impl.BuildingServiceImpl;

@Controller
public class BuildingController {
	@Autowired
	public BuildingServiceImpl buildingServiceImpl;

	@GetMapping("/ShowBulding")
	public String ShowProduct(Model model) {
		List<Building> list = buildingServiceImpl.findAllBuilding();
		model.addAttribute("listInvoices",list);
		return "Building";
	}
	@GetMapping("/AddBuilding")
	public String PageAddProduct(Model model) {
		Building newBuilding = new Building();
		model.addAttribute("newBuilding",newBuilding);
		return  "AddBuilding";
	}
	/*@GetMapping("/ShowPtoEdit")
	public String ShowPtoEdit(@RequestParam String productCode,Model model) {
		Building p = productServiceImpl.findByIdBuilding(productCode);
		model.addAttribute("ProductEdit",p);
		return "Edit";
	}*/
//	@PostMapping("/AddP")
	@RequestMapping("/To-AddBulding")
	public String addnewProduct(@ModelAttribute("newBuilding") Building newBuilding) {
		buildingServiceImpl.saveBuilding(newBuilding);
		return "redirect:/ShowBulding";

}
	@RequestMapping("/Edit")
	public String Edit(@RequestParam Long id,Model model) {
		Building editBuilding = buildingServiceImpl.findByIdBuilding(id);
		model.addAttribute("editBuilding",editBuilding);
		return "EditBuilding";
	}
	@RequestMapping("/ToEditBuilding")
	public String ToEdit(@ModelAttribute("editBuilding") Building editBuilding,Model model) {
		buildingServiceImpl.saveBuilding(editBuilding);
		return "redirect:/ShowBulding";
	}
	/*
//	@GetMapping("/Delete")
	@RequestMapping("/Delete")
	public String Delete(@RequestParam Long id) {
		buildingServiceImpl.DeleteBuilding(id);
		return "redirect:/ShowBulding";
	}
	/*@RequestMapping("/Detail")
	public String Detail(@RequestParam Long id) {
		productServiceImpl.DeleteBuilding(id);
		return "redirect:/ShowProduct";
	}*/
}