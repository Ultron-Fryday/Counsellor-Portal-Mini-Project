package com.omdev.cp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.omdev.cp.entity.Enquiry;

@Repository
public interface EnquiryRepo extends JpaRepository<Enquiry, Integer> {

	@Query(value="SELECT * FROM enquiries_tbl enq WHERE enq.counsellor_id = :counsellorId",nativeQuery = true)
	List<Enquiry> findByCounsellorId(Integer counsellorId);
	
	
}
