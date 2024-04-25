package com.demo.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Submission {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Submission() {
		super();
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Submission( String company,Recruiters recruiter, Candidates candidate,String timestamp) {
		super();
		
		this.company = company;
		this.timestamp=timestamp;
		this.recruiter = recruiter;
		this.candidate = candidate;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public Recruiters getRecruiter() {
		return recruiter;
	}

	public void setRecruiter(Recruiters recruiter) {
		this.recruiter = recruiter;
	}

	public Candidates getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidates candidate) {
		this.candidate = candidate;
	}

	@Column
    private String company;

	@Column(name="timestamp")
    private String timestamp;

    @ManyToOne
    @JoinColumn(name = "recruiter_id", nullable=false)
    private Recruiters recruiter;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "candidate_id", nullable = false)
    private Candidates candidate;
    
    @Column
    String role;
    

	public Submission( String company,  Recruiters recruiter, Candidates candidate,String timestamp,
			String role) {
		super();
	
		this.company = company;
		this.timestamp = timestamp;
		this.recruiter = recruiter;
		this.candidate = candidate;
		this.role = role;
		//this.deleted=deleted;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}