package com.example.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.beans.Command;

public interface  CommandRepository extends  JpaRepository<Command, Long > {

}
