package com.salesken.srs.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.salesken.srs.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{
       
}
