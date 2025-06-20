package com.omdev.cp.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.omdev.cp.entity.Enquiry;
import com.omdev.cp.pojo.DashboardResponse;
import com.omdev.cp.pojo.ViewEnqFilterRequest;
import com.omdev.cp.service.CounsellorService;
import com.omdev.cp.service.EnquiryService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class EnquiryController {

	private final EnquiryService enquiryService;

	@GetMapping("/enquiry")
	public String addEnquiryPage(Model model) {

		Enquiry enqObj = new Enquiry();

		model.addAttribute("enquiry", enqObj);

		return "enquiryForm";
	}

	@PostMapping("/addEnquiry")
	public String handleAddEnquiry(Enquiry enquiry, HttpServletRequest request, Model model) throws Exception {

		// get existing session object
		HttpSession session = request.getSession(false);
		Integer counsellorId = (Integer) session.getAttribute("counsellorId");

		boolean isSaved = enquiryService.addEnquiry(enquiry, counsellorId);
		if (isSaved) {
			model.addAttribute("smsg", "Enquiry Added");
		} else {
			model.addAttribute("emsg", "Failed to Add Enquiry");
		}
		return "enquiryForm";
	}

	@GetMapping("/view-enquiries")
	public String getEnquiries(HttpServletRequest request, Model model) {

		// get existing session object
		HttpSession session = request.getSession(false);
		Integer counsellorId = (Integer) session.getAttribute("counsellorId");

		List<Enquiry> enqList = enquiryService.getAllEnquiries(counsellorId);
		model.addAttribute("enquiries", enqList);

		ViewEnqFilterRequest filterReq = new ViewEnqFilterRequest();
		model.addAttribute("viewEnqFilterRequest", filterReq);

		return "viewEnqsPage";
	}

	@PostMapping("/filter-enqs")
	public String filterEnquiries(ViewEnqFilterRequest viewEnqFilterRequest, HttpServletRequest request, Model model) {
		// get existing session object
		HttpSession session = request.getSession(false);
		Integer counsellorId = (Integer) session.getAttribute("counsellorId");

		List<Enquiry> enqList = enquiryService.getAllEnquiriesWithFilter(viewEnqFilterRequest, counsellorId);
		model.addAttribute("enquiries", enqList);

		return "viewEnqsPage";
	}

	@GetMapping("/editEnquiry")
	public String getEnquiryPage(@RequestParam("id") Integer id, Model model) {
		log.info("getEnquiryPage -- id: {}", id);
		Enquiry enqObj = enquiryService.getEnquiryById(id);
		model.addAttribute("enqId", id);
		model.addAttribute("enquiry", enqObj);
		return "editEnquiry";
	}

	@PostMapping("/editEnquiry")
	public String modifyEnquiry(Model model, HttpServletRequest request, Enquiry enquiry) throws Exception {
		log.info("modifyEnquiry -- id: {}", enquiry.getEnqId());
		// get existing session object
		HttpSession session = request.getSession(false);
		Integer counsellorId = (Integer) session.getAttribute("counsellorId");
		boolean isUpdated = enquiryService.modifyEnquiry(enquiry,counsellorId);
		if (isUpdated) {
			model.addAttribute("smsg", "Enquiry Updated");
		} else {
			model.addAttribute("emsg", "Failed to Update Enquiry");
		}
		return "redirect:/view-enquiries";
	}

}
