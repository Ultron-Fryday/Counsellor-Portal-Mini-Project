package com.omdev.cp.service;

import java.util.List;

import com.omdev.cp.entity.Enquiry;
import com.omdev.cp.pojo.ViewEnqFilterRequest;

public interface EnquiryService {
	public boolean addEnquiry(Enquiry enquiry, Integer counsellorId) throws Exception;
	public List<Enquiry> getAllEnquiries(Integer counssellorId);
	public List<Enquiry> getAllEnquiriesWithFilter(ViewEnqFilterRequest filterReq,Integer counsellorId);
	public Enquiry getEnquiryById(Integer enqId);
	
	public boolean modifyEnquiry(Enquiry enquiry, Integer counsellorId) throws Exception;
}
