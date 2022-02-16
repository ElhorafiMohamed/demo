package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.beans.Client;
import com.example.demo.repos.ClientRepository;


@Service
public class ClientServiceImp implements ClientService{

	@Autowired
	ClientRepository clientRepository;
	
	@Override
	public Client saveClient(Client c) {
		return clientRepository.save(c);
	}
	
	@Override
	public Client getClient(Long id) {
		return clientRepository.findById(id).get();
	}
	
	@Override
	public void deletClientById(Long id) {
		clientRepository.deleteById(id);
	}

	

    @Override
    public Client updateClient(Long id, Client newclient) {
    	Client client = clientRepository.findById(id).get();

    	client.setNomClient(newclient.getNomClient());
    	client.setPrenomClient(newclient.getPrenomClient());
    	client.setPrenomClient(newclient.getPrenomClient());
    	client.setPrenomClient(newclient.getPrenomClient());

        return clientRepository.save(client);
    }

	@Override
	public List<Client> getAllClients() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void deleteClient(Client p) {
		// TODO Auto-generated method stub
		
	}



	
	
}
