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
			ModelMap map 
			) {
		
		log.info("Inside Add Student method");
		
		Student st = null;
		List<SemesterMark> marks = null;
	   // SemesterMark sm = new SemesterMark(semesterNo , english,math,science); 
		
	    
	    Optional<Student> studentop = studentService.findStudentById(id);
	    if(studentop.isPresent()) {
	        //st = studentop.get();
	        //List<SemesterMark> semesterMarks = st.getSem();
	        //if(semesterMarks.size() < 2 && semesterMarks.get(0).getSemesterNo() != semesterNo) {
	        	//.add(sm);
	        //}//else {
	        	log.error("Already Student avaiable ");
	        	return "redirect:/";
	        
	
	    }else{
	    	//marks=new ArrayList<SemesterMark>(); 
	    	//marks.add(sm);
	        st = new Student(id , name,city);
	    	//st.setSem(marks);
	    }
	    
		
	  //  sm = semesterService.addSemestermark(sm);
		st = studentService.addStudent(st);
		
		
		log.info(st.toString());
		//log.info(sm.toString());
		
		log.info("Student Successfully  added ");
		return "redirect:/";
	}
	
	@GetMapping("/addstudent")
	public String addstudent() {
		return "addstudent";
	}
	
	
	@PostMapping("/addMarks")
	public String addMarks(@RequestParam("id") int id ,
			@RequestParam("english") int english,@RequestParam("math") int math ,@RequestParam("science") int science, @RequestParam("semno") int semesterNo 
			,ModelMap map 
			) {
		
		log.info("Inside add marks method");
		
		Student st = null;
		//List<SemesterMark> marks = null;
	    SemesterMark sm = new SemesterMark(semesterNo , english,math,science); 
		
	    log.info("ID "+ id);
	    
	    Optional<Student> studentop = studentService.findStudentById(id);
	    if(studentop.isPresent()) {
	    	
	    	log.info("Inside 1st if");
	        st = studentop.get();
	        List<SemesterMark> semesterMarks = st.getSem();
	        
	        log.info(semesterMarks.toString());
	        if(!semesterMarks.isEmpty()) {
	        	
	        	if(semesterMarks.size() < 2 && semesterMarks.get(0).getSemesterNo() != semesterNo) {
	        		semesterMarks.add(sm);
	        	}else {
	        		log.error("Already semester mark available  for student "+st.getName());
	        		return "redirect:/";
	        	}
	        	
	        }else {
	        	log.info("line 120");
	        	semesterMarks = new ArrayList<SemesterMark>();
	        	
	        	semesterMarks.add(sm);
	        	
	        	st.setSem(semesterMarks);
	        	
	        }
	        
	
	    }
	    
		
	    sm = semesterService.addSemestermark(sm);
		st = studentService.updateStudent(st);
		
		
		log.info(st.toString());
		log.info(sm.toString());
		
		log.info("Successfully semester mark added for " +st.getName());
		return "redirect:/";
	}
	
	@GetMapping("/addMarks")
	public String addMark(ModelMap map) {
		List<Student> students = studentService.getAllStudent();
		map.put("students", students);
		return "addMark";
	}
}
