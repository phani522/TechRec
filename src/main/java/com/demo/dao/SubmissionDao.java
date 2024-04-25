
package com.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.model.Candidates;
import com.demo.model.Recruiters;
import com.demo.model.Submission;

public interface SubmissionDao extends JpaRepository<Submission, Integer> {

	List<Submission> findByRecruiter(Recruiters recruiter);

    List<Submission> findByCandidate(Candidates candidate);
  
    
    void deleteAllByRecruiter(Recruiters recruiter);
    
    void deleteAllByCandidate(Candidates candidate);
    
    
}

//@Modifying
//@Query("DELETE FROM Submission s WHERE s.recruiter_id = :recruiter.id")
//void deleteByRecruiter(@Param("recruiter") Recruiters recruiter);
//
//@Modifying
//@Query("DELETE FROM Submission s WHERE s.candidate_id = :candidate.id")
//void deleteByCandidate(@Param("recruiter") Candidates candidate);