package com.demo.springmvc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.demo.springmvc.entity.Customer;

@Controller
public class HelloWorldController {

	List<Customer> customers = null;
	int maxId = 0;
	
	@RequestMapping(value = "hello", method = RequestMethod.GET)
	public ModelAndView showForm(ModelAndView model) {
		
		ModelAndView view = new ModelAndView("hello");		
		return view;
	}
	
	private void initListCustomers() {
		//init list Customer
		customers = new ArrayList<Customer>();
		for (int i=1; i<=2; i++) {
			Customer cus = new Customer();
			cus.setId(i);
			cus.setUsername("tommy" + i);
			cus.setFullname("fullname" + i);
			cus.setEmail("email" + i + "@lvtutorial.com");
			customers.add(cus);
			maxId = i;
		}
	}
	
	private void removeCustomer(int id) {
		for (Customer cus : customers) {
			if (cus.getId() == id){
				customers.remove(cus);
				break;
			}
		}		
	}
	
	//get all customers
	@RequestMapping(value = "/customers", method = RequestMethod.GET) 
	public ResponseEntity<List<Customer>> getCustomers() {  
		
		if (customers == null)
			initListCustomers();
		
		if(customers.isEmpty()){
            return new ResponseEntity<List<Customer>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
		ResponseEntity<List<Customer>> cusEntity = new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
		return cusEntity;
	}
	
	@RequestMapping(value = "/create_customer", method = RequestMethod.POST)
    public ResponseEntity<List<Customer>> createCustomer(@RequestBody Customer customer) {
  
		try {
			maxId++;
			customer.setId(maxId);	
			customers.add(customer);
			ResponseEntity<List<Customer>> cusEntity = new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
	        return cusEntity;
		} catch (Exception ex)
		{
			return new ResponseEntity<List<Customer>>(HttpStatus.NO_CONTENT);
		}
    } 
	
	
	@RequestMapping(value = "/update_customer", method = RequestMethod.POST)
    public ResponseEntity<List<Customer>> updateCustomer(@RequestBody Customer customer){
  
		try {
			Customer cus = (Customer)customer.clone();
			removeCustomer(customer.getId());
			customers.add(cus);
			ResponseEntity<List<Customer>> cusEntity = new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
	        return cusEntity;
		} catch (Exception ex)
		{
			return new ResponseEntity<List<Customer>>(HttpStatus.NO_CONTENT);
		}
    }
	
	@RequestMapping(value = "/delete_customer", method = RequestMethod.POST)
    public ResponseEntity<List<Customer>> deleteCustomer(@RequestBody Customer customer) {
  
		try {
			removeCustomer(customer.getId());
			ResponseEntity<List<Customer>> cusEntity = new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
	        return cusEntity;
		} catch (Exception ex)
		{
			return new ResponseEntity<List<Customer>>(HttpStatus.NO_CONTENT);
		}
    }
	
	@RequestMapping(value = "/create_customer_test", method = RequestMethod.POST)
    public ResponseEntity<Customer> createCustomerTest(@RequestBody Customer customer) {
  
		try {
			maxId++;
			customer.setId(maxId);	
			customers.add(customer);
			ResponseEntity<Customer> cusEntity = new ResponseEntity<Customer>(customer, HttpStatus.OK);
	        return cusEntity;
		} catch (Exception ex)
		{
			return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
		}
    }
}
