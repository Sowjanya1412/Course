package com.example.CourseEureka.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.CourseEureka.Model.Student;
import com.example.CourseEureka.Service.CourseService;


@RestController
public class CourseController {
	@Autowired
	CourseService courseService;
	
	@GetMapping("courses")
	public String getAllStudents() {
		 return  courseService.getAllStudents();
	}
	@GetMapping("course/{id}")
	public String getStudentByPathVar(@PathVariable String id) {
		 return  courseService.getStudentByPathVar(id);
	}
	
	@GetMapping("course1")
	public String getStudentByRequestParam(@RequestParam String name) {
		 return  courseService.getStudentsByReqParam(name);
	}
	@PostMapping("course")
    public ResponseEntity<String> SaveStudentByPostBody(@RequestBody Student student) {
    	return courseService.SaveStudentByPostBody(student);
    }

}
