package com.omdev.cp.service;

import com.omdev.cp.entity.Counsellor;
import com.omdev.cp.pojo.DashboardResponse;

public interface CounsellorService {
	public boolean registerCounsellor(Counsellor counsellor);
	public Counsellor login(String email, String pwd);
	public DashboardResponse getDashboardInfo(Integer counsellorId);
	
	public Counsellor findByEmail(String email);
	
}
