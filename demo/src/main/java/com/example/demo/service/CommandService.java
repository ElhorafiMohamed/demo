package com.example.demo.service;

import java.util.List;

import com.example.demo.beans.Command;

public interface CommandService {
	Command saveCommand(Command p);
	Command updateCommand(Command p);
	void deleteCommand(Command p);
	void deleteCommandById(Long id);
	Command getCommand(Long id);
	List<Command> getAllCommands();
}
