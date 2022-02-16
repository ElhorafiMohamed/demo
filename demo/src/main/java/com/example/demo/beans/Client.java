package com.example.demo.beans;



import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "clients")
public class Client {	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long idClient;
	
	@OneToMany(mappedBy = "client", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Command> commands;
	
	private String nomClient;
	private String prenomClient;
	private String telClient;
	private String mailClient;

	public Client() {
		super();
	}

	public Client(String nomClient, String prenomClient, String telClient, String mailClient) {
		super();
		this.nomClient = nomClient;
		this.prenomClient = prenomClient;
		this.telClient = telClient;
		this.mailClient = mailClient;
	}

	public long getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public String getNomClient() {
		return nomClient;
	}

	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}

	public String getPrenomClient() {
		return prenomClient;
	}

	public void setPrenomClient(String prenomClient) {
		this.prenomClient = prenomClient;
	}

	public String getTelClient() {
		return telClient;
	}

	public void setTelClient(String telClient) {
		this.telClient = telClient;
	}

	public String getMailClient() {
		return mailClient;
	}

	public void setMailClient(String mailClient) {
		this.mailClient = mailClient;
	}

	@Override
	public String toString() {
		return "client [idClient=" + idClient + ", nomClient=" + nomClient + ", prenomClient=" + prenomClient
				+ ", telClient=" + telClient + ", mailClient=" + mailClient + "]";
	}
	
	
	

}
