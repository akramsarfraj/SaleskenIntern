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
    text-align:center;
	border: 1px solid;
	border-collapse: collapse;
}

#head {
	text-align: center;
}
</style>
</head>
<body>
	<div id="head">
		<h2>Student Reporting System</h2>
	</div>

	<div>
		<a style="font-size: medium; color: green;"
			href="<%=request.getContextPath()%>/addstudent">Add Student</a>
	</div>

	<br>
	<br>
	<br>
	<br>
	<%
	List<Student> students = (List<Student>) request.getAttribute("students");
	%>

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
					MarksCalculater.calculatePercentagePerSemester(student);
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