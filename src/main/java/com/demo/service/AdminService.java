package com.demo.service;

import java.security.SecureRandom;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.dao.CandidatesDao;
import com.demo.dao.RecruitersDao;
import com.demo.dao.SubmissionDao;
//import com.demo.dao.SubmissionDao;
import com.demo.model.CandidateWrapper;
import com.demo.model.Candidates;
import com.demo.model.RecruiterInCandidate;
import com.demo.model.RecruiterLogin;
import com.demo.model.Recruiters;
import com.demo.model.RecuiterWrapper;
//import com.demo.model.Submission;
import com.demo.model.Submission;
import com.demo.model.SubmissionWrapper;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AdminService {
	@Autowired
	RecruitersDao dao;
	@Autowired
	CandidatesDao candidatesDao;
	@Autowired
	SubmissionDao submissionDao;
	
	public ResponseEntity create(String fname, String lname, String phone,String id, List<Candidates> candidates) {
		String password= lname+"@"+phone.substring(phone.length()-4);
		Recruiters r=new Recruiters(id, fname, lname, phone, password,candidates);
		dao.save(r);
		return new ResponseEntity<>("recruiter added successfully", HttpStatus.CREATED);
		
	}
	
	public ResponseEntity createCandidate(Candidates candidate) {
		System.out.println("candidate is "+candidate.getFname()+" "+candidate.getTech()+ "  "+candidate.getRecruiterId());
		candidatesDao.save(candidate);
		return new ResponseEntity("candidate added successfully",HttpStatus.CREATED);
	}
	
//	public ResponseEntity assignCandidateToRecruiter(String recruiterId, Candidates candidate) {
//	    Recruiters recruiter = dao.findById(recruiterId).orElse(null);
//
//	    if (recruiter != null) {
//	        List<Candidates> assignedCandidates = recruiter.getCandidates();
//	        
//	        // Check if the candidate already exists in the list
//	        if (!assignedCandidates.contains(candidate)) {
//	            assignedCandidates.add(candidate);
//	            recruiter.setCandidates(assignedCandidates);
//
//	            dao.save(recruiter);
//	            return new ResponseEntity<>("Candidate assigned to recruiter successfully", HttpStatus.OK);
//	        } else {
//	            return new ResponseEntity<>("Candidate is already assigned to the recruiter", HttpStatus.BAD_REQUEST);
//	        }
//	    } else {
//	        return new ResponseEntity<>("Recruiter not found", HttpStatus.NOT_FOUND);
//	    }
//	}
	
	public ResponseEntity assignCandidateToRecruiter(String recruiterId, Candidates candidate) {
	    Recruiters recruiter = dao.findById(recruiterId).orElse(null);

	    if (recruiter != null) {
	        List<Candidates> assignedCandidates = recruiter.getCandidates();

	        // Check if the candidate already exists in the list
	        boolean candidateAlreadyAssigned = false;
	        for (Candidates assignedCandidate : assignedCandidates) {
	            if (assignedCandidate.getId().equals(candidate.getId())) {
	                candidateAlreadyAssigned = true;
	                break;
	            }
	        }

	        if (!candidateAlreadyAssigned) {
	            assignedCandidates.add(candidate);
	            recruiter.setCandidates(assignedCandidates);

	            dao.save(recruiter);
	            return new ResponseEntity<>("Candidate assigned to recruiter successfully", HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>("Candidate is already assigned to the recruiter", HttpStatus.BAD_REQUEST);
	        }
	    } else {
	        return new ResponseEntity<>("Recruiter not found", HttpStatus.NOT_FOUND);
	    }
	}




	
	
	
	public String passwordGen() {
		String Alpha="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		String digits="0123456789";
		String symbols="#$@%&*";
		StringBuilder password=new StringBuilder("");
		SecureRandom random=new SecureRandom();
		for(int i=0;i<5;i++) {
			password.append(Alpha.charAt(random.nextInt(Alpha.length())));
		}
		password.append(symbols.charAt(random.nextInt(symbols.length())));
		password.append(digits.charAt(random.nextInt(digits.length())));
		password.append(digits.charAt(random.nextInt(digits.length())));
		 
		return password.toString();
	}
	
	public ResponseEntity getAllRecruiters() {
		List<Recruiters> recruiters= dao.findAll();
		List<RecuiterWrapper> rwList=new ArrayList<>();
		for(Recruiters r: recruiters) {
			rwList.add(new RecuiterWrapper(r.getId(), r.getFirst_name(), r.getLast_name(), r.getPhone(),r.getCandidates()));
		}
		
		return new ResponseEntity(rwList,HttpStatus.OK);
	}
	
	public ResponseEntity getRecruiterLogin() {
		List<Recruiters> recruiters= dao.findAll();
		List<RecruiterLogin> rlogins=new ArrayList<>();
		for(Recruiters recruiter:recruiters) {
			rlogins.add(new RecruiterLogin(recruiter.getId(), recruiter.getPassword()));
		}
		return new ResponseEntity(rlogins, HttpStatus.OK);
	}
	
	public ResponseEntity getAllCandidates() {
		List<Candidates> candList=candidatesDao.findAll();
		 
		 List<CandidateWrapper> cw=new ArrayList();
		 for (Candidates candidate : candList) {
			 
		         // This forces eager loading of recruiterId
			 List<Recruiters> recruiters=candidate.getRecruiterId();
			 List<RecruiterInCandidate> rwList=new ArrayList<>();
				for(Recruiters r: recruiters) {
					rwList.add(new RecruiterInCandidate(r.getId(), r.getFirst_name(), r.getLast_name(), r.getPhone()));
				}
			 cw.add(new CandidateWrapper(candidate.getId(), candidate.getFname(), candidate.getLname(), candidate.getTech(), candidate.getPhone(), rwList));
		    }
		
		//System.out.println(candList);
		//System.out.println(candList.get(0).toString());
		//candList.get(0).g
		return new ResponseEntity(cw, HttpStatus.OK);
	}
	
	public ResponseEntity deleteCandidate(String recId,String candId) {
		List<Recruiters> recruiters= dao.findAll();
		for(Recruiters recruiter: recruiters) {
			if(recruiter.getId().equals(recId)) {
				List<Candidates> candidates= recruiter.getCandidates();
				Candidates candidate=null;
				for(Candidates candidate1:candidates) {
					if(candidate1.getId().equals(candId)) {
						candidate=candidate1;
						break;
					}
				}
				candidates.remove(candidate);
				recruiter.setCandidates(candidates);
				dao.save(recruiter);
				return new ResponseEntity("candidate deleted from recruiter successfully", HttpStatus.OK);
				
			}
		}
		return new ResponseEntity("Deletion not successful", HttpStatus.BAD_REQUEST);
	}
	
//	public ResponseEntity deleteRecruiter(String recId) {
//		dao.deleteById(recId);
//		Recruiters r=dao.findById(recId).get();
//		List<Submission> submissions=submissionDao.findAll();
//		//List<Submission> submissionByRec = submissionDao.findByRecruiter(r);
//		submissionDao.deleteByRecruiter(r);
//		return new ResponseEntity("Recruiter Deleted Successfully", HttpStatus.OK);
//	}
//	
//	public ResponseEntity deleteRecruiter(String recId) {
//	    Optional<Recruiters> recruiterOptional = dao.findById(recId);
//
//	    if (recruiterOptional.isPresent()) {
//	        Recruiters r = recruiterOptional.get();
//	        
//	        // Delete submissions associated with the recruiter
//	        System.out.println("Before deleting recruiter submission");
//	        List<Submission> submissionsList=submissionDao.findAll();
//	        for(Submission sub: submissionsList) {
//	        	if(sub.getRecruiter().getId().equals(recId)) {
//	        		System.out.println("deleted Id is "+sub.getRecruiter().getId()+" and candidate is "+sub.getCandidate().getId());
//	        		submissionDao.delete(sub);
//	        	}
//	        }
//	        System.out.println("after deleting recruiter submission");
//	        //submissionDao.deleteAllByRecruiter(r);
//	        List<Candidates> candList= candidatesDao.findAll();
//	        for(Candidates cand: candList) {
//	        	System.out.println("candidate is "+cand.getId());
//	        	if(cand.getRecruiterId().size()!=0) {
//	        		List<Recruiters> recList=cand.getRecruiterId();
//	        		System.out.println("recruiter size is "+cand.getRecruiterId().size());
//	        		for(Recruiters rec: recList) {
//	        			if(rec.getId().equals(recId)) {
//	        				System.out.println("candidate id is for removing recruiter is "+cand.getId());
//	        				cand.getRecruiterId().remove(rec);
//	        			}
//	        		}
//	        	}
//	        }
//	        System.out.println("outside loop");
//	        dao.deleteById(recId);
//
//	        return new ResponseEntity<>("Recruiter Deleted Successfully", HttpStatus.OK);
//	    } else {
//	        return new ResponseEntity<>("Recruiter not found", HttpStatus.NOT_FOUND);
//	    }
//	}

	public ResponseEntity deleteRecruiter(String recId) {
	    Optional<Recruiters> recruiterOptional = dao.findById(recId);

	    if (recruiterOptional.isPresent()) {
	        Recruiters r = recruiterOptional.get();

	        // Find all submissions associated with the recruiter
	        List<Submission> submissions = submissionDao.findByRecruiter(r);

	        // Delete submissions associated with the recruiter
	        submissionDao.deleteAll(submissions);

	        // Update candidates to remove references to the recruiter
	        List<Candidates> candList = candidatesDao.findAll();
	        List<Candidates> candToAdd = new ArrayList<>();

	        for (Candidates cand : candList) {
	            List<Recruiters> recList = cand.getRecruiterId();
	            recList.removeIf(rec -> rec.getId().equals(recId));
	            cand.setRecruiterId(recList);
	            candToAdd.add(cand);
	        }

	        candidatesDao.saveAll(candToAdd);

	        // Delete the recruiter
	        dao.deleteById(recId);

	        return new ResponseEntity<>("Recruiter Deleted Successfully", HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>("Recruiter not found", HttpStatus.NOT_FOUND);
	    }
	}


	
//	public ResponseEntity deleteCandidateFull(String candId) {
//		List<Recruiters> recruiters=dao.findAll();
//		for(Recruiters recruiter: recruiters) {
//			List<Candidates> candidates= recruiter.getCandidates();
//			Candidates candidate=null;
//			for(Candidates candidate1:candidates) {
//				if(candidate1.getId().equals(candId)) {
//					candidate=candidate1;
//					break;
//				}
//			}
//			candidates.remove(candidate);
//			recruiter.setCandidates(candidates);
//			dao.save(recruiter);
//		}
//		candidatesDao.deleteById(candId);
//		Candidates candidate= candidatesDao.findById(candId).get();
//		submissionDao.deleteAllByCandidate(candidate);
//		
//		return new ResponseEntity("Candidate removed ", HttpStatus.OK);
//	}
	
	public ResponseEntity deleteCandidateFull(String candId) {
	   
	    Candidates candidate = candidatesDao.findById(candId).orElse(null);
	    if (candidate != null) {
	    	System.out.println("Before deleting candidate submission");
	        submissionDao.deleteAllByCandidate(candidate);
	        System.out.println("After deleting candidate submission");
	    }
	    List<Recruiters> recruiters = dao.findAll();
	    for (Recruiters recruiter : recruiters) {
	        List<Candidates> candidates = recruiter.getCandidates();
	        candidates.removeIf(c -> c.getId().equals(candId));
	        recruiter.setCandidates(candidates);
	        dao.save(recruiter);
	    }

	    candidatesDao.deleteById(candId);

	    return new ResponseEntity<>("Candidate removed", HttpStatus.OK);
	}

		
	
	public ResponseEntity createSubmission(String company, String recruiterId, String candId, String timestamp,String role) {
		Recruiters r=dao.findById(recruiterId).get();
		
		submissionDao.save(new Submission(company, r, candidatesDao.findById(candId).get(),timestamp,role));
		return new ResponseEntity("Submission successful ", HttpStatus.CREATED);
	}
	
	public ResponseEntity getAllSubmissions() {
		List<Submission> subList= submissionDao.findAll();
		List<SubmissionWrapper> swList=new ArrayList<>();
		for(Submission sub:subList) {
			swList.add(new SubmissionWrapper(sub.getCompany(), sub.getRecruiter().getFirst_name(), sub.getCandidate().getFname(), sub.getTimestamp(), sub.getRole()));
			
		}
		return new ResponseEntity(swList, HttpStatus.OK);
	}

	public ResponseEntity getSubmissionsByRecruiter(String recId) {
		Recruiters r=dao.findById(recId).get();
		return new ResponseEntity(submissionDao.findByRecruiter(r),HttpStatus.OK);
	}
	
	public ResponseEntity getSubmissionsByCandidate(String candId) {
		Candidates c = candidatesDao.findById(candId).get();
		return new ResponseEntity(submissionDao.findByCandidate(c),HttpStatus.OK);
		
	}
	
	public ResponseEntity getRecruiterById(String recId) {
		Recruiters r=dao.findById(recId).get();
		RecuiterWrapper rw=new RecuiterWrapper(r.getId(), r.getFirst_name(), r.getLast_name(), r.getPhone(), r.getCandidates());
		return new ResponseEntity(rw, HttpStatus.OK);
	}
	public ResponseEntity getCandidateById(String candId) {
		return new ResponseEntity(candidatesDao.findById(candId).get(), HttpStatus.OK);
	}

}
