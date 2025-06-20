package com.omdev.cp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.omdev.cp.entity.Counsellor;

@Repository
public interface CounsellorRepo extends JpaRepository<Counsellor, Integer> {
	
	
	public Counsellor findByEmail(String email);
	
	//Query will generate automatically 
	public Counsellor findByEmailAndPassword(String email,String password);
	
}