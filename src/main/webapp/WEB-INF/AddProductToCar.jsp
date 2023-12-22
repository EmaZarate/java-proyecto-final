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
    
      <!-- Bootstrap core CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

	<title>Producto</title>
	
	<%
    	Product prod = (Product)request.getAttribute("prod");
	%>
	
</head>
<body>
<%@ include file="Header.jsp" %>

<label for="name" class="form-label">Name</label>
<p><%=prod.getName()%></p>
<label class="form-label">Precio</label>
<p><%= prod.getSalePrice()%></p>
<label class="form-label">Cantidad disponible</label>
<p><%=prod.getNumber()%></p>

<form action="addProductoToCar" method="post">
<input type=hidden class="form-control" name="productId" id="productId" aria-describedby="emailHelp"  value='<%=prod.getProductId()%>'>
  <div class="mb-3">
    <label for="number" class="form-label">Cantidad</label>
    <input type="number" max=<%=prod.getNumber()%> class="form-control" name="number" id="number" required>
  </div>
  
  <button type="submit" class="btn btn-primary">Submit</button>
</form>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>
</html>