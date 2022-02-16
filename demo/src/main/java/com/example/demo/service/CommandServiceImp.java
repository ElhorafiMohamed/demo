package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.beans.Command;
import com.example.demo.repos.CommandRepository;

public class CommandServiceImp implements CommandService{
	
	
	@Autowired
	CommandRepository commandRepository;
	
	@Override
	public Command saveCommand(Command c) {
		return commandRepository.save(c);
	}

	@Override
	public Command updateCommand(Command c) {
		return commandRepository.save(c);
	}

	@Override
	public void deleteCommand(Command c) {
		commandRepository.delete(c);
		
	}

	@Override
	public void deleteCommandById(Long id) {
		commandRepository.deleteById(id);
		
	}

	@Override
	public Command getCommand(Long id) {
		return commandRepository.findById(id).get();
	}

	@Override
	public List<Command> getAllCommands() {
		
		return commandRepository.findAll();
	}
	
}

