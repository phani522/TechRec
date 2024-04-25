package com.demo.model;

import java.util.List;

public class CandidateWrapper {
	private String id;
	public String fname;
	public String lname;
	public String tech;
	public String phone;
	public List<RecruiterInCandidate> recInCand;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public CandidateWrapper(String id, String fname, String lname, String tech, String phone,
			List<RecruiterInCandidate> recInCand) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.tech = tech;
		this.phone = phone;
		this.recInCand = recInCand;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getTech() {
		return tech;
	}
	public void setTech(String tech) {
		this.tech = tech;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public List<RecruiterInCandidate> getRecInCand() {
		return recInCand;
	}
	public void setRecInCand(List<RecruiterInCandidate> recInCand) {
		this.recInCand = recInCand;
	}

}
