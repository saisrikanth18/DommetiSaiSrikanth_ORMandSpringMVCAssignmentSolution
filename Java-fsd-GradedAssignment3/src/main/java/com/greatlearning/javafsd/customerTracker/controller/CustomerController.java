package com.greatlearning.javafsd.customerTracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greatlearning.javafsd.customerTracker.entity.Customer;
import com.greatlearning.javafsd.customerTracker.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@RequestMapping("/begin-add")
	public String handleBeginAdd(Model theModel) {
		Customer customer = new Customer();

		theModel.addAttribute("customer", customer);

		return "customer-details";
	}

	@PostMapping("/save")
	public String handleSave(@RequestParam("id") int id, @RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("email") String email) {

		Customer customer = null;
		if (id != 0) {
			// Update Customer
			customer = customerService.findById(id);
			customer.setFirstName(firstName);
			customer.setLastName(lastName);
			customer.setEmail(email);
		} else {

			// Add Customer
			customer = new Customer(firstName, lastName, email);
		}
		customerService.save(customer);
		return "redirect:/customers/list";
	}

	@RequestMapping("/begin-update")
	public String handleBeginUpdate(@RequestParam("customerId") int theId, Model theModel) {

		Customer customer = customerService.findById(theId);
		theModel.addAttribute("customer", customer);
		return "customer-details";
	}

	@RequestMapping("/delete")
	public String handleDelete(@RequestParam("customerId") int theId) {

		customerService.deleteById(theId);

		return "redirect:/customers/list";
	}

}
