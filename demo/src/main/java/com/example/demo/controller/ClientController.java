package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.beans.Client;
import com.example.demo.service.ClientService;

@RestController
public class ClientController {

	// build create employee REST API
	@Autowired
	ClientService clientService;
	
	//entre des nouveau client
	@PostMapping("/clients")
    public Client saveClient( @RequestBody Client c){
        return clientService.saveClient(c);
    }
	
	//get client by ID
	 @GetMapping("/clients/{id}")
	    public Client getClient(@PathVariable Long id) {
	        return clientService.getClient(id);
	    }
	
	 //delete client
	 @DeleteMapping("/clients/{id}")
	    public String deletClientById(@PathVariable Long id){
		 	clientService.deletClientById(id);
	        return "Departement deleted Successfully";
	    }
	 
	 //update client
	 @PutMapping("/clients/{id}")
	    public Client updateDepartementById(@PathVariable Long id, @RequestBody Client client) {
	        return clientService.updateClient(id, client);
	    }
	
	
}
