package com.example.demo.service;

import java.util.List;

import com.example.demo.beans.Client;


public interface ClientService {
	
	Client saveClient(Client p);//
	Client updateClient(Long id,Client p);//
	void deleteClient(Client p);
	void deletClientById(Long id);//
	Client getClient(Long id);//
	List<Client> getAllClients();	
}
