package com.demo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;

@Entity
public class Recruiters {
	@Id
	private String id;
	private String first_name;
	private String last_name;
	private String phone;
	private String password;

	@ManyToMany(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
	
    @JoinTable(
        name = "recruiter_candidate",
        joinColumns = @JoinColumn(name = "recruiter_id"),
        inverseJoinColumns = @JoinColumn(name = "candidate_id"))
	@JsonIgnore
	List<Candidates> candidates;
	

	public List<Candidates> getCandidates() {
		return candidates;
	}
	public void setCandidates(List<Candidates> candidates) {
		this.candidates = candidates;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Recruiters() {
		
	}
	public Recruiters(String id, String first_name, String last_name, String phone, String password, List<Candidates> cand) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.phone = phone;
		this.password = password;
		this.candidates=cand;
	}
	
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
//@ManyToMany(cascade = CascadeType.ALL)
//@JoinTable(
//    name = "recruiter_candidate",
//    joinColumns = @JoinColumn(name = "recruiter_id"),
//    inverseJoinColumns = @JoinColumn(name = "candidate_id")
//)

