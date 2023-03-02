<%@page import="org.slf4j.Logger"%>
<%@page import="org.slf4j.LoggerFactory"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.TreeMap"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="com.salesken.srs.common.MarksCalculater"%>
<%@page import="com.salesken.srs.services.SemesterService"%>
<%@page import="com.salesken.srs.model.SemesterMark"%>
<%@page import="com.salesken.srs.model.Student"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
table {
	width: 100%;
}

table, th, td {
	text-align: center;
	border: 1px solid;
	border-collapse: collapse;
	border: 1px solid;
}

#head {
	text-align: center;
}
</style>
</head>
<body>
     <%! 
        org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());
     
     %>
  
	<div id="head">
		<h2>Student Reporting System</h2>
	</div>

	<div>${error}${success}</div>

	<div>
		<a style="font-size: medium; color: green;"
			href="<%=request.getContextPath()%>/addstudent">Add Student</a>
	</div>
	
	<br>
	<br>
	
	<div>
		<a style="font-size: medium; color: green;"
			href="<%=request.getContextPath()%>/addMarks">Add Marks</a>
	</div>

	<br>
	<br>
	<br>
	<br>
	<%
	double lastSemAvgPercentage = 0;
	double totalPercetage = 0;
	double englishAverage = 0, mathAverage = 0, scienceAverage = 0;
	int totalSem = 0;

	Map<Double, String> firstSemStudentAverageMap = new TreeMap<Double, String>(Collections.reverseOrder());
	Map<Double, String> secSemStudentAverageMap = new TreeMap<Double, String>(Collections.reverseOrder());
	
	Map<String, Integer> map = new HashMap<String, Integer>();

	Map<Integer, Integer> semesterStudentMarkmap = new HashMap<Integer, Integer>();

	Map<Integer, Double> semMap = new HashMap<Integer, Double>();

	List<Student> students = (List<Student>) request.getAttribute("students");

	
	
	
	for (Student student : students) {
		MarksCalculater.calculatePercentagePerSemester(student, semMap);
        
		MarksCalculater.semAverageMarkMap(student, firstSemStudentAverageMap, secSemStudentAverageMap);
		
		totalSem = totalSem + student.getSem().size();
		MarksCalculater.calculateTotalPerSubjectMarks(student, map);

		List<SemesterMark> semesterMarks = student.getSem();
			for (SemesterMark sm : semesterMarks) {
				int noOfstudentPerSem = 0;
				
				if (semesterStudentMarkmap.containsKey(sm.getSemesterNo())) {
					noOfstudentPerSem = semesterStudentMarkmap.get(sm.getSemesterNo()) + 1;
				} else {
					noOfstudentPerSem = 1;
				}
				semesterStudentMarkmap.put(sm.getSemesterNo(), noOfstudentPerSem);
			}
	

 
	}

	
	log.info("fisrt average :" + firstSemStudentAverageMap);
	log.info("second average :" + secSemStudentAverageMap);
	
	
	

	if (!map.isEmpty()) {

		englishAverage = MarksCalculater.averageStudentMarksPerSubject(map.get("English"), totalSem);

		mathAverage = MarksCalculater.averageStudentMarksPerSubject(map.get("Math"), totalSem);

		scienceAverage = MarksCalculater.averageStudentMarksPerSubject(map.get("Science"), totalSem);

	}
	
     
      
      List<Double> firstSemKeyList = new ArrayList<Double>(firstSemStudentAverageMap.keySet());
      List<Double> secondSemKeyList = new ArrayList<Double>(secSemStudentAverageMap.keySet());
      
	if (!semMap.isEmpty()) {
		int semKey;
		log.info("semesterStudentMarkmap :" + semesterStudentMarkmap);

		log.info("semestermap :" + semMap);
		
		if (semMap.containsKey(2)) {
			semKey = 2;
			totalPercetage = semMap.get(2);
		} else {
			semKey = 1;
			totalPercetage = semMap.get(1);
		}
		
		
		lastSemAvgPercentage = MarksCalculater.calculateAveragePercentage(semesterStudentMarkmap.get(semKey),
		totalPercetage);
		
		
	}
      
	%>

    <div>
      
      <h3>Average Percentage in recent Semester : <%= lastSemAvgPercentage %> </h3>
      
    </div>



	<div>
	    <h3>Subject Mark Average</h3>
		<table>
			<thead>
				<tr>
					<th>SubjectName</th>
					<th>Average</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>English</td>
					<td><%=englishAverage%></td>
				</tr>
				<tr>
					<td>math</td>
					<td><%=mathAverage%></td>
				</tr>
				<tr>
					<td>Science</td>
					<td><%=scienceAverage%></td>
				</tr>
			</tbody>
		</table>
	</div>

	<br>
	<br>
	<br>

       <div>
           <h3>First Semester Student Average</h3>
           <table>
			<thead>
				<tr>
					<th>Student Name</th>
					<th>Average Mark</th>
				</tr>
			</thead>
			<tbody>
			    <% 
			    if(!firstSemKeyList.isEmpty()){
			    
			    %>
			    <tr>
                
			       <td> <%= firstSemStudentAverageMap.get(firstSemKeyList.get(0)) %> </td>
			       <td> <%= firstSemKeyList.get(0) %> </td> 
			   </tr>
			   <% 
			    }
			    
			    if(firstSemKeyList.size() > 1)
			    {
			   %>
			    <tr>
                
			       <td> <%= firstSemStudentAverageMap.get(firstSemKeyList.get(1)) %> </td>
			       <td> <%= firstSemKeyList.get(1) %> </td> 
			   </tr>
			   <% 
			    }
			   %>
			</tbody>
        </table>
         
       </div>
       
        <br>
        <br>
        <br>
        <br>
        
       <div>
           <h3>Second Semester Student Average</h3>
           <table>
			<thead>
				<tr>
					<th>Student Name</th>
					<th>Average Mark</th>
				</tr>
			</thead>
			<tbody>
			   <% 
			    if(!secondSemKeyList.isEmpty()){
			    
			    %>
			    <tr>
                
			       <td> <%= secSemStudentAverageMap.get(secondSemKeyList.get(0)) %> </td>
			       <td> <%= secondSemKeyList.get(0) %> </td> 
			   </tr>
			   
			   <% 
			    }
			     
			     if(secondSemKeyList.size() > 1){
			   %>
			    <tr>
                
			       <td> <%= secSemStudentAverageMap.get(secondSemKeyList.get(1)) %> </td>
			       <td> <%= secondSemKeyList.get(1) %> </td> 
			   </tr>
			   <% } %>
			</tbody>
        </table>
         
       </div>
    



	<br>
	<br>
	<br>
	<br>
	<br>

	<div>



		<table>
			<thead>
				<tr>
					<th>id</th>
					<th>Name</th>
					<th>City</th>
					<th>SemesterNO</th>
					<th>English</th>
					<th>Math</th>
					<th>Science</th>
				</tr>
			</thead>
			<tbody>
				<%
				for (Student student : students) {
				%>
				<tr>
					<td><%=student.getId()%></td>
					<td><%=student.getName()%></td>
					<td><%=student.getCity()%></td>
					<%
					List<SemesterMark> semesterMarks = student.getSem();
					for (SemesterMark mark : semesterMarks) {
					%>
					<td><%=mark.getSemesterNo()%></td>
					<td><%=mark.getEnglish()%></td>
					<td><%=mark.getMath()%></td>
					<td><%=mark.getScience()%></td>
					<%
					}
					%>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
	</div>
</body>
</html>