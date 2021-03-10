package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FormController {
	
	@Autowired
	CustomersRepo repo;
	@RequestMapping("/")
	public String form() {
		return "Form";
	}
	
	@RequestMapping("/details")
	public String form(Customers customers) {
		repo.save(customers);
		return "Form";
	}
	
	@RequestMapping("/getdetails")
	public String getdetails() {
		return "ViewCustomers";
	}

@PostMapping("/getdetails")
	public ModelAndView getdetails(@RequestParam int cid) {
	ModelAndView mv = new ModelAndView("Retrieve");
	Customers customers = repo.findById(cid).orElse(null);
	mv.addObject(customers);
	return mv;
}

@RequestMapping("/customers") 
@ResponseBody
public List<Customers> getCustomers() {
	return repo.findAll();
}

@RequestMapping("/customers/{cid}") 
@ResponseBody
public Optional<Customers> getCustomersAll(@PathVariable("cid") int cid) {
	return repo.findById(cid);
}
}
