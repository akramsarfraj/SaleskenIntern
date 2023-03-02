<%@page import="com.salesken.srs.model.Student"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
   <h2>Add Student Marks</h2>
   <form action="addMarks" method="post">
      
       Name : <select name="id">  
           <option > Select student </option>
      <% 
         
         List<Student> students = (List<Student>) request.getAttribute("students");
      
         for(Student st : students){
        	 
         
      %>
      
      
     
                <option value="<%= st.getId() %>"> <%= st.getName() %> </option>
                
                <% } %>
      
            </select><br> <br>
      
      
      English :<input type="text" name="english"><br> <br>
      Math :<input type="text" name="math"><br> <br>
      Science :<input type="text" name="science"><br> <br>
      
      SemesterNo : <select name="semno">
                     <option value="1">1</option>
                     <option value="2">2</option>
                </select>
                
               <br>
               <br>
      <input type="submit" value="Submit">
      
   </form>
</body>
</html>