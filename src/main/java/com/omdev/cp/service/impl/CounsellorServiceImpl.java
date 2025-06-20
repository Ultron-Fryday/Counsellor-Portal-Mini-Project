package com.omdev.cp.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.omdev.cp.entity.Counsellor;
import com.omdev.cp.entity.Enquiry;
import com.omdev.cp.pojo.DashboardResponse;
import com.omdev.cp.repository.CounsellorRepo;
import com.omdev.cp.repository.EnquiryRepo;
import com.omdev.cp.service.CounsellorService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CounsellorServiceImpl implements CounsellorService {
	
	
	private final CounsellorRepo counsellorRepo;
	
	private final EnquiryRepo enquiryRepo;

	@Override
	public boolean registerCounsellor(Counsellor counsellor) {
		log.info("registerCounsellor - Counsellor : {}",counsellor);
		
		
		
		Counsellor regCounsellor = counsellorRepo.save(counsellor);
		if(regCounsellor.getCounsellorId() > 0) {
			log.info("Registered Successfull -  Registered Counselllor Id: {}",regCounsellor.getCounsellorId());
			return true;
		}
		log.info("Registered is failed");
		return false;
	}

	@Override
	public Counsellor login(String email, String pwd) {
		log.info("email : {}, pwd : {}",email,pwd);
		Counsellor counsellor = counsellorRepo.findByEmailAndPassword(email, pwd);
		log.info("login - Counsellor : {}",counsellor);
		return counsellor; 
	}

	@Override
	public DashboardResponse getDashboardInfo(Integer counsellorId) {
		log.info("getDashboardInfo -  counsellorId : {}",counsellorId);
		List<Enquiry> enquiries = enquiryRepo.findByCounsellorId(counsellorId);
		DashboardResponse response = new DashboardResponse();
		long totalcount =  enquiries.size();
		long enrolledCount = enquiries.stream()
							.filter(enq -> enq.getEnqStatus().equalsIgnoreCase("Enrolled"))
							.count();
		long lostCount = enquiries.stream()
				   				  .filter(enq -> enq.getEnqStatus().equalsIgnoreCase("Lost"))
				   				  .count();
		long openCount = enquiries.stream()
 				  .filter(enq -> enq.getEnqStatus().equalsIgnoreCase("Open"))
 				  .count();

		response.setTotalEnq((int)totalcount);
		response.setEnrolledEnq((int)enrolledCount);
		response.setLostEnq((int)lostCount);
		response.setOpenEnq((int)openCount);
		log.info("getDashboardInfo - DashboardResponse : {}",response);
		return response;
	}

	@Override
	public Counsellor findByEmail(String email) {
		return counsellorRepo.findByEmail(email);
	}

}
