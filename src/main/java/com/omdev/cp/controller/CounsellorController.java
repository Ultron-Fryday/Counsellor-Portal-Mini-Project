package com.omdev.cp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.omdev.cp.entity.Counsellor;
import com.omdev.cp.pojo.DashboardResponse;
import com.omdev.cp.service.CounsellorService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor()
public class CounsellorController {
	
	private final CounsellorService counsellorService;
	
	@GetMapping("/")
	public String index(Model model) {
		
		Counsellor cobj = new Counsellor();
		
		//sending data from controller to UI
		model.addAttribute("counsellor", cobj);
		
		//return view name
		return "index";
	}
	
	@PostMapping("/login")
	public String login(Counsellor counsellor,HttpServletRequest request,Model model) {
		Counsellor couns = counsellorService.login(counsellor.getEmail(), counsellor.getPassword());
		
		if(couns == null) {
			model.addAttribute("emsg", "Invalid Credentials");
			return "index";
		}else {
			
			//valid login store counsellorId Session
			HttpSession session = request.getSession(true);
			session.setAttribute("counsellorId", couns.getCounsellorId());
			
			return "redirect:/dashboard";
		}
		
	}
	
	@GetMapping("/register")
	public String registerPage(Model model) {
		
		Counsellor cobj = new Counsellor();
		
		//sending data from controller to UI
		model.addAttribute("counsellor", cobj);
		
		//return view name
		return "register";
	}
	
	@PostMapping("/register")
	public String handleRegistration(Model model, Counsellor counsellor) {
		
		Counsellor byEmail = counsellorService.findByEmail(counsellor.getEmail());
		if(byEmail != null) {
			model.addAttribute("emsg", "Duplicate Email");
			return "register";
		}
		
		boolean isRegistered = counsellorService.registerCounsellor(counsellor);
		if(isRegistered) {
			//success
			model.addAttribute("smsg", "Registration Successfull");
			return "index";
		}else {
			//Failure
			model.addAttribute("emsg", "Registration Failed");
			return "register";
		}
		
	}
	
	@GetMapping("/dashboard")
	public String displayDashboard(HttpServletRequest request, Model model) {
		// get existing session object
		HttpSession session = request.getSession(false);
		Integer counsellorId = (Integer) session.getAttribute("counsellorId");
		
		DashboardResponse dbresponse = counsellorService.getDashboardInfo(counsellorId);
		model.addAttribute("dashboardInfo",dbresponse);
		
		return "dashboard";
		
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		//getting existing session and invalidate
		HttpSession session = request.getSession(false);
		session.invalidate();
		//redirect to login page
		return "redirect:/";
	}
	
	
}
