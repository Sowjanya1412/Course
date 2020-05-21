package com.example.CourseEureka.Service;


import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.CourseEureka.Model.Student;


@Service
public class CourseService {
	@Autowired
	RestTemplate restTemplate;

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	public String getAllStudents() {
		String result = restTemplate.getForObject("http://STUDENT-SERVICE/students", String.class);
		return result;
	}

	public String getStudentByPathVar(String id) {
		String result = restTemplate.getForObject("http://STUDENT-SERVICE/student1/" + id, String.class);
		return result;
	}
	
	public String getStudentsByReqParam(String name) {

		String url = "http://STUDENT-SERVICE/student2";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		Map<String, String> params = new HashMap<String, String>();
		params.put("name",name);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
		for (Map.Entry<String, String> entry : params.entrySet()) {
			builder.queryParam(entry.getKey(), entry.getValue());
		}

		String result = restTemplate.getForObject(builder.toUriString(), String.class);
		return result;
	}
	
	public ResponseEntity<String> SaveStudentByPostBody(Student student){
		String uri = "http://STUDENT-SERVICE/student";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		JSONObject request = new JSONObject();
		request.put("id", student.getId());
		request.put("name", student.getName());
		request.put("courses",student.getCourses());
		
		HttpEntity<String> entity = new HttpEntity<String>(request.toString(), headers);
		ResponseEntity<String> response = restTemplate.postForEntity(uri, entity, String.class);
		System.out.println(response);
		return response;
	}

}
