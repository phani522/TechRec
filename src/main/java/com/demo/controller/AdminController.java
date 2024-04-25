package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dao.CandidatesDao;
import com.demo.model.Candidates;
import com.demo.model.RecuiterWrapper;
import com.demo.model.Submission;
import com.demo.model.SubmissionWrapper;
import com.demo.service.AdminService;
@CrossOrigin(origins={"http://localhost:4200"})
@RestController
public class AdminController {
	@Autowired
	AdminService service;
	
	@PostMapping("/addRecruiter")
	public ResponseEntity addRecruiter(@RequestBody RecuiterWrapper rw ) {
		return service.create(rw.getFname(), rw.getLname(), rw.getPhone(), rw.getId(),rw.getCandidates());
	}

	@PostMapping("/addCandidate")
	public ResponseEntity addCandidate(@RequestBody Candidates candidate) {
		return service.createCandidate(candidate);
	}
	
	@GetMapping("/getRecruiters")
	public ResponseEntity getRecruiters() {
		return service.getAllRecruiters();
		
	}
	
	@GetMapping("/getRecruitersLogin")
	public ResponseEntity getRecruitersLogin() {
		return service.getRecruiterLogin();
	}
	
	@GetMapping("/getCandidates")
	public ResponseEntity getCandidates() {
		return service.getAllCandidates();
	}
	
	@PutMapping("/assignCandidate/{recruiterId}")
	public ResponseEntity assignCandidate(@PathVariable String recruiterId,@RequestBody Candidates candidate) {
		return service.assignCandidateToRecruiter(recruiterId, candidate);
		
	}
	
	@DeleteMapping("/deleteCandidate/{recuiterId}/{candidateId}")
	public ResponseEntity deleteCand(@PathVariable String recuiterId, @PathVariable String candidateId) {
		return service.deleteCandidate(recuiterId, candidateId);
	}
	
	@DeleteMapping("deleteRecruiter/{recuiterId}")
	public ResponseEntity deleteRecruiter(@PathVariable String recuiterId) {
		return service.deleteRecruiter(recuiterId);
	}
	@DeleteMapping("deleteCandidateFull/{candidateId}")
	public ResponseEntity deleteCanidate(@PathVariable String candidateId) {
		return service.deleteCandidateFull(candidateId);

	}

	
	@PostMapping("/createSubmission")
	public ResponseEntity doSubmission(@RequestBody SubmissionWrapper sw) {
		return service.createSubmission(sw.getCompany(), sw.getRecId(), sw.getCandId(),sw.getTimestamp(),sw.getRole());
		
	}
	
	@GetMapping("/getAllSubmissions")
	public ResponseEntity getSubmissions() {
		return service.getAllSubmissions();
	}
	
	@GetMapping("/getSubmissionByRecruiter/{recId}")
	public ResponseEntity getSubmissionByRecruiter(@PathVariable String recId) {
		return service.getSubmissionsByRecruiter(recId);
	}
	
	@GetMapping("/getSubmissionByCandidate/{candId}")
	public ResponseEntity getSubmissionByCandidate(@PathVariable String candId) {
		return service.getSubmissionsByCandidate(candId);
	}
	
	@GetMapping("/getRecruiterById/{recId}")
	public ResponseEntity getRecuiterById(@PathVariable String recId) {
		return service.getRecruiterById(recId);
	}
	@GetMapping("/getCandidateById/{candId}")
	public ResponseEntity getCandidateById(@PathVariable String candId) {
		return service.getCandidateById(candId);
	}
}
