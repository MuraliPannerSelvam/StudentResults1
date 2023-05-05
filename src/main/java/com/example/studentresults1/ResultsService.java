package com.example.studentresults1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ResultsService {
	@Autowired
	ResultsDao resDao;

	public String addStudentResults(Results1 res) {
		return resDao.addStudentResults(res);
	}
	
	public Results1 getResults(int id) {
		return resDao.getResults(id);
	}

	public String updateResults(Results1 id) {
		return resDao.updateResults(id);
	}

	public String deleteResults(int id) {
		return resDao.deleteResults(id);
	}

	public String addResultsList(List<Results1> res) {
		return resDao.addResultsList(res);
	}

	public List<Results1> getResultsList() {
		return resDao.getResultsList();
	}
	
	public List<Results1> getResultsRange() {
		return resDao.getResultsRange();
	}

	public Results1 getTopperResults() {
		List<Results1> all=resDao.getResultsList();
		float max=all.get(0).getTotalMarks();
		Results1 temp=all.get(0);
		for(Results1 x : all) {
			if(x.getTotalMarks()>max) {
				max=x.getTotalMarks();
				temp=x;
			}
		}
		return temp;
	}

}
