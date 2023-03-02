package com.salesken.srs.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesken.srs.model.Student;
import com.salesken.srs.repository.StudentRepository;

@Service
public class StudentService {
	  
	  @Autowired
      StudentRepository rep;
	  
	  public Student addStudent(Student s) {
		  return rep.save(s);
	  }
	  
	  public List<Student> getAllStudent(){
		  return rep.findAll();
	  }
	  
	  public Optional<Student> findStudentById(int id) {
		 return rep.findById(id);
	  }
	  
	  public Student updateStudent(Student s) {
		  return rep.save(s);
	  }
	  
}
