package com.demo.model;

public class SubmissionWrapper {

	String company;
	String recId;
	String candId;
	String timestamp;
	String role;
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public SubmissionWrapper(String company, String recId, String candId,String timestamp,String role) {
		super();
		this.company = company;
		this.recId = recId;
		this.candId = candId;
		this.timestamp = timestamp;
		this.role=role;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getRecId() {
		return recId;
	}
	public void setRecId(String recId) {
		this.recId = recId;
	}
	public String getCandId() {
		return candId;
	}
	public void setCandId(String candId) {
		this.candId = candId;
	}
	
}
