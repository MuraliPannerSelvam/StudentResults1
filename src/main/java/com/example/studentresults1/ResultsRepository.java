package com.example.studentresults1;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ResultsRepository extends JpaRepository<Results1,Integer> {
	
	@Query(value = "select*from results where total_marks>=70 and total<=90", nativeQuery = true)
	public List<Results1> getResultsRange();


}
