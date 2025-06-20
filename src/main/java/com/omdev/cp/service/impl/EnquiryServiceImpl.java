package com.omdev.cp.service.impl;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.omdev.cp.entity.Counsellor;
import com.omdev.cp.entity.Enquiry;
import com.omdev.cp.pojo.ViewEnqFilterRequest;
import com.omdev.cp.repository.CounsellorRepo;
import com.omdev.cp.repository.EnquiryRepo;
import com.omdev.cp.service.EnquiryService;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EnquiryServiceImpl implements EnquiryService {
	
	
	private final EnquiryRepo enquiryRepo;
	
	private final CounsellorRepo counsellorRepo;
	

	@Override
	public boolean addEnquiry(Enquiry enquiry, Integer counsellorId) throws Exception {
		log.info("addEnquiry - counsellorId : {}",counsellorId);
		
		Counsellor counsellor = counsellorRepo.findById(counsellorId).orElse(null);
		if(counsellor == null) {
			throw new Exception("Counsellor Not Found");
		}
		
		//Associating counsellor to enquiry
		enquiry.setCounsellor(counsellor);
		
		Enquiry savedEnquiry = enquiryRepo.save(enquiry);
		
		if(savedEnquiry.getEnqId() != null) {
			log.info("Enquiry Saved Successfully");
			return true;
		}
		log.info("Enquiry not saved");
		return false;
	}

	@Override
	public List<Enquiry> getAllEnquiries(Integer counsellorId) {
		log.info("getAllEnquiries - counsellorId : ",counsellorId);
		return enquiryRepo.findByCounsellorId(counsellorId);
	}

	@Override
	public List<Enquiry> getAllEnquiriesWithFilter(ViewEnqFilterRequest filterReq,Integer counsellorId) {

		log.info("getAllEnquiriesWithFilter - filterReq : {}, counsellorId :{}",filterReq, counsellorId);
		
	    // QBE (Query By Example) Implementation (Dynamic Query Preparation)
		
		Enquiry enq = new Enquiry();
		
		if(StringUtils.isNotEmpty(filterReq.getClassMode())) {
			enq.setClassMode(filterReq.getClassMode());
		}
		if(StringUtils.isNotEmpty(filterReq.getCourseName())) {
			enq.setCourseName(filterReq.getCourseName());
		}
		if(StringUtils.isNotEmpty(filterReq.getEnqStatus())) {
			enq.setEnqStatus(filterReq.getEnqStatus());
		}
		
		Example<Enquiry> of = Example.of(enq);
		
		Counsellor counsellor = counsellorRepo.findById(counsellorId).orElse(null);
		enq.setCounsellor(counsellor);
		
		List<Enquiry> enqList = enquiryRepo.findAll(of);
		
		log.info("getAllEnquiriesWithFilter - enqList Size : {}",enqList.size());
		return enqList;
	}

	@Override
	public Enquiry getEnquiryById(Integer enqId) {
		log.info("getEnquiryById - enqId :{}",enqId);
		return enquiryRepo.findById(enqId).orElse(null);
	}

	@Override
	public boolean modifyEnquiry(Enquiry enquiry,Integer counsellorId) throws Exception {
		log.info("modifyEnquiry - counsellorId :{}",counsellorId);
		Counsellor counsObj = counsellorRepo.findById(counsellorId).orElse(null);
		if(counsObj == null) {
			throw new Exception("Counsellor not Found");
		}
		enquiry.setCounsellor(counsObj);
		Enquiry updatedEnquiry = enquiryRepo.save(enquiry);
		if(updatedEnquiry.getEnqId() != null) {
			log.info("Enquiry Updated Successfully");
			return true;
		}
		log.info("Enquiry Modification Failed");
		return false;
	}

}
