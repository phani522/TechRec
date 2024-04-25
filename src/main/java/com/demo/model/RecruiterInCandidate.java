package com.demo.model;

public class RecruiterInCandidate {

	String id;
	String fname;
	String lname;
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
	public RecruiterInCandidate(String id, String fname, String lname, String phone) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "RecruiterInCandidate [id=" + id + ", fname=" + fname + ", lname=" + lname + ", phone=" + phone + "]";
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
	String phone;
}
