package com.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.model.Candidates;

public interface CandidatesDao extends JpaRepository<Candidates,String> {
	@Query("SELECT c FROM Candidates c LEFT JOIN FETCH c.recruiterId")
    List<Candidates> findAllWithRecruiters();

}
