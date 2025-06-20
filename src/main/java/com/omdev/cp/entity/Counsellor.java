package com.omdev.cp.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name ="counsellors_tbl")
@Getter
@Setter
public class Counsellor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "counsellor_id")
	private Integer counsellorId;
	private String name;
	@Column(unique = true)
	private String email;
	private String password;
	private String phno;
	@Column(name="created_date")
	@CreationTimestamp
	private LocalDateTime createdDate;
	@Column(name="updated_date")
	@UpdateTimestamp
	private LocalDateTime updatedDate;
}
