package com.omdev.cp.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="enquiries_tbl")
@Getter
@Setter
public class Enquiry {

	@Column(name = "enq-id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer enqId;
	@Column(name="student_name")
	private String studentName;
	@Column(name="student_phone")
	private String studentPhone;
	@Column(name="course_name")
	private String courseName;
	@Column(name="class_mode")
	private String classMode;
	@Column(name="enq-status")
	private String enqStatus;
	@Column(name="created_date")
	@CreationTimestamp
	private LocalDateTime createdDate;
	@Column(name="updated_date")
	@UpdateTimestamp
	private LocalDateTime updatedDate;
	@ManyToOne
	@JoinColumn(name = "counsellor_id",nullable = false)
//	@JsonBackReference
	private Counsellor counsellor;
}
