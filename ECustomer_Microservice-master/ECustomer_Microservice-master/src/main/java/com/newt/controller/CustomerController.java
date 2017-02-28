package com.newt.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.newt.model.Customer;
import com.newt.service.CustomerService;
import com.wordnik.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    private static final Logger logger = Logger.getLogger(CustomerController.class);
    
    @ApiOperation(value = "post a customer")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Customer registerCustomer(@RequestBody Customer customer) {
        Customer customerinfo = customerService.findCustomerBycustomerEmail(customer.getCustomerEmail());
        if (customerinfo != null) {
        	logger.info("Uesrname already exit, plz try another one");
             
        } else {
        	return customerService.save(customer);
        }

        return null;
    }

    @ApiOperation(value = "get a customer")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    
    public Customer findCustomerbyID(@PathVariable int id) {

        Customer customer = null;
        
        try {
        	customer = customerService.findCustomerBycustomerId(id);
            if (customer.getCustomerId() == id) {
            	logger.info("Customer Id is found");
                customer = customerService.findOne(id);

            } else {
                Object obj = "Customer id Notfound";
                customer = (Customer) obj;
               logger.info("Customer Id is not found");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customer;
    }

    @RequestMapping(value = "/allcustomers", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "get all customers")
    public List<Customer> listCustomers() {
    	 logger.info("Searching all customer details===============:\n");
        List<Customer> list = (List<Customer>) customerService.findAll();
        //testing.add(nameVal);
        return list;
    }

    @RequestMapping(value = "/byname/{customerName}", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "get customer names1")
    public Customer findCustomerbyFirstName(@PathVariable("customerName") String customerName) {

        Customer customer= null;
        logger.info("Controller Searching for Employee Firstname ===============:" + customerName);
       
        try {
        	customer = customerService.findCustomerBycustomerName(customerName);
            if (customer.getCustomerName().equalsIgnoreCase(customerName)) {

            	 logger.info("Customer First Name isfound:");

            } else {
                Object obj = "Customer Name not found";
                customer = (Customer) obj;
                logger.info("Customer FirstName is not found:");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customer;
    }

    @RequestMapping(value = "/login/{username:.+}", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "get customer details by username")
    public Customer findCustomerbyUsername(@PathVariable("username") String username) {

        Customer customer= null;
        logger.info("Controller Searching for username ===============:" + username);
       
        try {
        	customer = customerService.findCustomerByusername(username);
            if (customer != null) {
            	logger.info("Customer is exist");
            	return customer;
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
