<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
   <h2>Add Student</h2>
   <form action="add" method="post">
      
      ID : <input type="text" name="id"><br>
      NAME :<input type="text" name="name"><br>
      CITY :<input type="text" name="city"><br>
      
      English :<input type="text" name="english"><br>
      Math :<input type="text" name="math"><br>
      Science :<input type="text" name="science"><br>
      
      SemesterNo : <select name="semno">
                     <option value="1">1</option>
                     <option value="2">2</option>
                </select>
      <input type="submit" value="Submit">
      
   </form>
</body>
</html>