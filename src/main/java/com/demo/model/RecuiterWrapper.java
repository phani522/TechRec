package com.demo.model;


import java.util.List;

import jakarta.persistence.ElementCollection;

public class RecuiterWrapper {
	

	
	String id;
	String fname;
	String lname;
	String phone;
	List<Candidates> candidates;
	public List<Candidates> getCandidates() {
		return candidates;
	}
	public void setCandidates(List<Candidates> candidates) {
		this.candidates = candidates;
	}
	public RecuiterWrapper(String id, String fname, String lname, String phone, List<Candidates> list) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.phone = phone;
		this.candidates=list;
	}
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
