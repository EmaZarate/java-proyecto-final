<%@ page import="java.util.LinkedList" %>
<%@ page import="entities.*" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="http://getbootstrap.com/favicon.ico">
    <link href="style/style.css" rel="stylesheet">
    
      <!-- Bootstrap core CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

	<title>Usuario</title>
	
	<%
    	User user = (User)request.getAttribute("user");
		LinkedList<Role> roles = (LinkedList<Role>)request.getAttribute("roles");
	%>
	
</head>
<body>
<%@ include file="Header.jsp" %>
<div class="container mt-2">
	<h4 class="mt-5 mb-3">
	<%=(user == null) ? "Agregar Usuario" : "Editar Usuario"%> 
	</h4>
	<form action="createupdateuser" method="post">
	<input type=hidden class="form-control" name="userId" id="userId"  value='<%=user != null ? user.getId() : 0%>'>
	  <div class="mb-3">
	    <label for="name" class="form-label">Nombre</label>
	    <input type="text" class="form-control" name="name" id="name" value='<%=user != null ? user.getName() : ""%>'>
	  </div>
	  <div class="mb-3">
	    <label for="name" class="form-label">Apellido</label>
	    <input type="text" class="form-control" name="surname" id="surname" value='<%=user != null ? user.getSurname() : ""%>'>
	  </div>
	  <div class="mb-3">
	    <label for="name" class="form-label">Email</label>
	    <input type="text" class="form-control" name="email" id="email" value='<%=user != null ? user.getEmail() : ""%>'>
	  </div>
	   <div class="mb-3">
	    <label for="name" class="form-label">Password</label>
	    <input type="text" class="form-control" name="password" id="password" value='<%=user != null ? user.getPassword() : ""%>'>
	  </div>
	   <div class="mb-3 form-check">
	    <input type="checkbox" class="form-check-input" id="isactive" name="isactive" <%if(user != null && user.isActive()){%> checked <%}%>>
	    <label class="form-check-label" for="exampleCheck1">Habilitado</label>
	  </div>
	  <label for="role" class="form-label">Rol</label>
	  	<select class="form-select" name="role" aria-label="Default select example">
	  		<% for (Role role : roles) { %>
	  		<option  
	  		<%if(user != null && role.getId() == user.getRole().getId()) {%>selected<%}%> value=<%=role.getId()%>
	  		><%=role.getName()%>
	  		</option>
	  		<% } %>
		</select>
	  <button type="submit" class="btn btn-primary mt-3">Submit</button>
	</form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>
</html>