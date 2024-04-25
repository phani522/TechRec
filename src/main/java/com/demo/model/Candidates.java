package com.demo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Candidates {
	@Id
	
	private String id;
	public String fname;
	public String lname;
	public String tech;
	public String phone;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	//@Column(name = "tech")
	
	@ManyToMany(mappedBy = "candidates", cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
	@JsonManagedReference
	@JsonIgnore
	List<Recruiters> recruiterId;
	public String getTech() {
		return tech;
	}
	public void setTech(String tech) {
		this.tech = tech;
	}
	public List<Recruiters> getRecruiterId() {
		return recruiterId;
	}
	public void setRecruiterId(List<Recruiters> recruiterId) {
		this.recruiterId = recruiterId;
	}
	
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

}
//
//@OneToMany(mappedBy = "candidate", cascade = CascadeType.PERSIST)
//private List<Submission> submissions;
//public List<Submission> getSubmissions() {
//	return submissions;
//}
//public void setSubmissions(List<Submission> submissions) {
//	this.submissions = submissions;
//}
//@Override
//public String toString() {
//	return "Candidates [id=" + id + ", fname=" + fname + ", lname=" + lname + ", Tech=" + Tech + ", phone=" + phone
//			+ ", recruiterId=" + recruiterId + "]";
//}
