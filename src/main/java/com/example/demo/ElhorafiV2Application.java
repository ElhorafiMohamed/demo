package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ElhorafiV2Application {

	public static void main(String[] args) {
		SpringApplication.run(ElhorafiV2Application.class, args);
//		String owner = "ownerName"; // Replace with the owner of the repository
//		String repo = "repositoryName"; // Replace with the name of the repository
//		String apiUrl = "https://api.github.com/repos/" + owner + "/" + repo;
//
//		RestTemplate restTemplate = new RestTemplate();
//		String response = restTemplate.getForObject(apiUrl, String.class);
//
//		System.out.println("Repository information:");
//		System.out.println(response);
	}

}
