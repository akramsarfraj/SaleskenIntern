package com.salesken.srs.common;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.salesken.srs.model.SemesterMark;
import com.salesken.srs.model.Student;

public class MarksCalculater {
    public static double calculateAveragePercentage(int totalStudents,int totalPercentage) {
    	double average = ( totalPercentage * 1.0 )/ totalStudents;
    	
    	return average;
    }
    
    public static double calculatePercentagePerSemester(Student student) {
      List<SemesterMark> marks = student.getSem();
      Collections.sort(marks);
      System.out.println(marks);
      return 0;
    }
}
