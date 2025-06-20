package com.omdev.cp.pojo;

import lombok.Data;

@Data
public class DashboardResponse {
	private String counsellorName;
	private Integer totalEnq;
	private Integer openEnq;
	private Integer enrolledEnq;
	private Integer lostEnq;
}
