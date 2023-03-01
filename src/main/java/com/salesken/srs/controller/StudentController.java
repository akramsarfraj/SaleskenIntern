package com.salesken.srs.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesken.srs.model.SemesterMark;
import com.salesken.srs.model.Student;
import com.salesken.srs.services.SemesterService;
import com.salesken.srs.services.StudentService;

@Controller
@RequestMapping("/")
public class StudentController {
 
	private org.slf4j.Logger log = LoggerFactory.getLogger(StudentController.class);
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private SemesterService semesterService;
	
	@GetMapping
	public String home(ModelMap map){
		List<Student> students = studentService.getAllStudent();
		map.put("students", students);
		return "Home";
	}
	
	@PostMapping("/add")
	public String add(@RequestParam("id") int id ,@RequestParam("name") String name,@RequestParam("city") String city,
			@RequestParam("english") int english,@RequestParam("math") int math ,@RequestParam("science") int science, @RequestParam("semno") int semesterNo  ) {
		
		log.info("Inside Add method");
		
		Student st = null;
		List<SemesterMark> marks = null;
	    SemesterMark sm = new SemesterMark(semesterNo , english,math,science); 
		
	    sm = semesterService.addSemestermark(sm);
	    
	    Optional<Student> studentop = studentService.findStudentById(id);
	    if(studentop.isPresent()) {
	        st = studentop.get();
	        st.getSem().add(sm);
	
	    }else{
	    	marks=new ArrayList<SemesterMark>(); 
	    	marks.add(sm);
	        st = new Student(id , name,city);
	    	st.setSem(marks);
	    }
	    
		
		st = studentService.addStudent(st);
		
		
		log.info(st.toString());
		log.info(sm.toString());
		
		return "redirect:/";
	}
	
	@GetMapping("/addstudent")
	public String addstudent() {
		return "addstudent";
	}
	
}
