package com.salesken.srs.common;


import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.salesken.srs.model.SemesterMark;
import com.salesken.srs.model.Student;

public class MarksCalculater {
    public static double calculateAveragePercentage(int totalStudents,double totalPercentage) {
    	double average = ( totalPercentage * 1.0 )/ totalStudents;
    	
    	return average;
    }
    
    public static void calculatePercentagePerSemester(Student student, Map<Integer, Double> semMap) {
      List<SemesterMark> marks = student.getSem();

      for(SemesterMark semesterMark : marks) {
    	  int total = semesterMark.getEnglish() + semesterMark.getMath()+semesterMark.getScience();
    	  double percentage = total /3.0;
    	
    	  if(semMap.containsKey(semesterMark.getSemesterNo())) {
    		percentage = (percentage + semMap.get(semesterMark.getSemesterNo()) )  ;
    	  }
    	  semMap.put(semesterMark.getSemesterNo(), percentage);
      }
     
    }
    
    public static void calculateTotalPerSubjectMarks(Student student,Map<String, Integer> map) {
    	
    	
    	List<SemesterMark> marks = student.getSem();
    	
        int eng =0 ,math=0,scn=0;
    	for(SemesterMark semesterMark: marks) {
    		eng= eng +  semesterMark.getEnglish();
    		math = math + semesterMark.getMath();
    		scn = scn + semesterMark.getScience();
    	}
    	
    	if(map.containsKey("English")) {
    	   int sub1 = map.get("English");
    	   eng = eng + sub1;
    	}
    	
    	if(map.containsKey("Math")) {
     	   int sub2 = map.get("Math");
     	   math = math + sub2;
     	}
    	
    	if(map.containsKey("Science")) {
     	   int sub3 = map.get("Science");
     	   scn = scn + sub3;
     	}
    	
    	map.put("English", eng);
    	map.put("Math",math);
    	map.put("Science",scn);
    
    	
    		
    	
    }
    
    public static void semAverageMarkMap(Student student, Map<Double, String> firstSem, Map<Double, String> secSem) {
    	List<SemesterMark> marks= student.getSem();
    	
    	for(SemesterMark sm: marks){
    		double average;
    		int total =  sm.getEnglish() + sm.getMath() + sm.getScience();
    		average = total/3.0;
    		if(sm.getSemesterNo() == 1){
    			firstSem.put(average,student.getName());
    		}else {
    			secSem.put(average, student.getName());
    		}
    	}
    }

    
   
    
    public static double averageStudentMarksPerSubject(int totalmarks ,int totalSem) {
    	double average = totalmarks / (1.0 * totalSem);
    	
    	return average;
    }
}
