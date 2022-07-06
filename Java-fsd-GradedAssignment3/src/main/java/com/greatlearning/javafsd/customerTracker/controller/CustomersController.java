package com.greatlearning.javafsd.customerTracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.greatlearning.javafsd.customerTracker.entity.Customer;
import com.greatlearning.javafsd.customerTracker.service.CustomerService;

@Controller
@RequestMapping("/customers")
public class CustomersController {
	
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping("/list")
	public String handleListCustomers(Model themodel) {
		List<Customer> customer = customerService.listAll();
		
		themodel.addAttribute("customer", customer);
		return "customers-lister";
	}

}
