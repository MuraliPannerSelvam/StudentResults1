package com.example.studentresults1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ResultsController {
	@Autowired
	ResultsService resSer;

	@Autowired
	RestTemplate restTemp;

	@PostMapping(value = "/addStudentResults")
	public String addStudentResults(@RequestBody Results1 res) {
		String url1 = "http://localhost:8080/getStudentName/";
		String url2 = "http://localhost:8080/getAttendance/";
		String url3 = "http://localhost:8081/getSem1Total/";
		String url4 = "http://localhost:8081/getSem2Total/";

		ResponseEntity<Integer> response2 = restTemp.exchange(url2 + res.getRollNumber(), HttpMethod.GET, null,
				Integer.class);
		int attendance = response2.getBody();

		ResponseEntity<Integer> response3 = restTemp.exchange(url3 + res.getRollNumber(), HttpMethod.GET, null,
				Integer.class);
		int total1 = response3.getBody();

		ResponseEntity<Integer> response4 = restTemp.exchange(url4 + res.getRollNumber(), HttpMethod.GET, null,
				Integer.class);
		int total2 = response4.getBody();
		
		if (attendance > 90) {

			res.setTotalMarks((total1 + total2 / 2) + 5);
		}
		else {
			res.setTotalMarks((total1 + total2) / 2);
		}
		if (res.getTotalMarks() > 100) {
			res.setTotalMarks(100);
		}
		
		ResponseEntity<String> response1 = restTemp.exchange(url1 + res.getRollNumber(), HttpMethod.GET, null,
				String.class);
		String name = response1.getBody();
		
		res.setName(name);

		res.setPercentage(res.getTotalMarks()*100/100);
		
		return resSer.addStudentResults(res);
	}

	@GetMapping(value = "/getResults/{id}")
	public Results1 getResults(@PathVariable int id) {
		return resSer.getResults(id);
	}

	@PutMapping(value = "/updateResults/{id}")
	public String updateResults(@RequestBody Results1 id) {
		return resSer.updateResults(id);
	}

	@DeleteMapping(value = "/deleteResults/{id}")
	public String deleteResults(@PathVariable int id) {
		return resSer.deleteResults(id);
	}

	@PostMapping(value = "/addResultList")
	public String addResultsList(@RequestBody List<Results1> res) {
		return resSer.addResultsList(res);
	}

	@GetMapping(value = "/getResults")
	public List<Results1> getResultsList() {
		return resSer.getResultsList();
	}

	@GetMapping(value = "/getResultsRange")
	public List<Results1> getResultsRange() {
		return resSer.getResultsRange();
	}

	@GetMapping(value = "/getTopperResults")
	public Results1 getTopperResults() {
		return resSer.getTopperResults();
	}

}
