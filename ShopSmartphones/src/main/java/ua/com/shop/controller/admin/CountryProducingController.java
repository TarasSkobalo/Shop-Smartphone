package ua.com.shop.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import ua.com.shop.entity.CountryProducing;
import ua.com.shop.service.CountryProducingService;

@Controller
@RequestMapping("/admin/countryProducing")
@SessionAttributes("countryProducing")
public class CountryProducingController {

	@Autowired
	private CountryProducingService countryProducingService;
	
	@ModelAttribute("countryProducing")
	public CountryProducing getForm(){
		return new CountryProducing();
	}
	
	@GetMapping
	public String show(Model model){
		model.addAttribute("countryProducings", countryProducingService.findAll());
		return "admin-countryProducing";
	}
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable int id, Model model){
		model.addAttribute("countryProducing", countryProducingService.findOne(id));
		show(model);
		return "admin-countryProducing";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id){
		countryProducingService.delete(id);
		return "redirect:/admin/countryProducing";
	}
	
	@PostMapping
	public String save(@ModelAttribute("countryProducing")CountryProducing countryProducing, SessionStatus status){
		countryProducingService.save(countryProducing);
		status.setComplete();
		return "redirect:/admin/countryProducing";
	}
}