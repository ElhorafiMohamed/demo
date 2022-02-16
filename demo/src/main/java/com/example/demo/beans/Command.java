package com.example.demo.beans;



import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Command {	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long idCommand;
	
	private String libelleCommand;
	private String descriptionCommand;
	private String adresseCommand;
	
	 @ManyToOne(fetch = FetchType.LAZY, optional = false)
	 @JoinColumn(name = "idClient", nullable = false)
	 private Client client;

	public Command() {
		super();
	}

	public Command(String libelleCommand, String descriptionCommand, String adresseCommand, Client client) {
		super();
		this.libelleCommand = libelleCommand;
		this.descriptionCommand = descriptionCommand;
		this.adresseCommand = adresseCommand;
		this.client = client;
	}

	public Long getIdCommand() {
		return idCommand;
	}

	public void setIdCommand(Long idCommand) {
		this.idCommand = idCommand;
	}

	public String getLibelleCommand() {
		return libelleCommand;
	}

	public void setLibelleCommand(String libelleCommand) {
		this.libelleCommand = libelleCommand;
	}

	public String getDescriptionCommand() {
		return descriptionCommand;
	}

	public void setDescriptionCommand(String descriptionCommand) {
		this.descriptionCommand = descriptionCommand;
	}

	public String getAdresseCommand() {
		return adresseCommand;
	}

	public void setAdresseCommand(String adresseCommand) {
		this.adresseCommand = adresseCommand;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public String toString() {
		return "Command [idCommand=" + idCommand + ", libelleCommand=" + libelleCommand + ", descriptionCommand="
				+ descriptionCommand + ", adresseCommand=" + adresseCommand + ", client=" + client + "]";
	}
	
	
	 

	
}
