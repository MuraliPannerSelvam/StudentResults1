package com.example.studentresults1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class ResultsDao {
	@Autowired
	ResultsRepository resRepo;

	public String addStudentResults(Results1 res) {
		resRepo.save(res);
		return "Successfully Saved";
	}
	
	public Results1 getResults(int id) {
		return resRepo.findById(id).get();
	}

	public String updateResults(Results1 id) {
		resRepo.save(id);
		return "SUccessFully updated";
	}

	public String deleteResults(int id) {
		resRepo.deleteById(id);
		return "Successfully Deleted";
	}

	public String addResultsList(List<Results1> res) {
		resRepo.saveAll(res);
		return "Successfully Saved to list";
	}

	public List<Results1> getResultsList() {
		return resRepo.findAll();
	}
	
	public List<Results1> getResultsRange() {
		return resRepo.getResultsRange();
	}

}
