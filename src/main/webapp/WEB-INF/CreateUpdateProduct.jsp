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
		LinkedList<Category> cats = (LinkedList<Category>)request.getAttribute("cats");
		LinkedList<Supplier> sups = (LinkedList<Supplier>)request.getAttribute("sups");
	%>
	
</head>
<body>
<%@ include file="Header.jsp" %>

<form action="createupdateproduct" method="post">
<input type=hidden class="form-control" name="productId" id="productId" aria-describedby="emailHelp"  value='<%=prod != null ? prod.getProductId() : 0%>'>
  <div class="mb-3">
    <label for="name" class="form-label">Name</label>
    <input type="text" class="form-control" name="name" id="name" value='<%=prod != null ? prod.getName() : ""%>'>
  </div>
  <div class="mb-3">
    <label for="salePrice" class="form-label">Precio de venta</label>
    <input type="number" step="0.01" class="form-control" name="salePrice" id="salePrice" value='<%= prod != null ? prod.getSalePrice() : 0%>'>
  </div>
  <div class="mb-3">
    <label for="purchasePrice" class="form-label">Precio de compra</label>
    <input type="number" step="0.01" class="form-control" name="purchasePrice" id="purchasePrice" value='<%=prod != null ? prod.getPurchasePrice() : 0%>'>
  </div>
  <div class="mb-3">
    <label for="orderPoint" class="form-label">Punto de pedido</label>
    <input type="number" class="form-control" name="orderPoint" id="orderPoint" value='<%=prod != null ? prod.getOrderPoint() : 0%>'>
  </div>
  <div class="mb-3">
    <label for="number" class="form-label">Cantidad</label>
    <input type="number" class="form-control" name="number" id="number" value='<%=prod != null ? prod.getNumber() : 0%>'>
  </div>
    <div class="mb-3 form-check">
    <input type="checkbox" class="form-check-input" id="isactive" name="isactive" <%if(prod != null && prod.isActive()){%> checked <%}%>>
    <label class="form-check-label" for="exampleCheck1">Habilitado</label>
  </div>
  <select class="form-select" name="category" aria-label="Default select example">
  <% for (Category cat : cats) { %>
  <option  
  <%if(prod != null && cat.getCategoryId() == prod.getCategory().getCategoryId()) {%>selected<%}%> value=<%=cat.getCategoryId()%>
  ><%=cat.getName()%></option>
  <% } %>
</select>

<select multiple class="form-select" name="suppliersIds" aria-label="Default select example">
  <% for (Supplier sup : sups) { %>
  <option  
  <%if(prod != null && sup.isSelected()) {%>selected<%}%> value=<%=sup.getSupplierId()%>
  ><%=sup.getName()%></option>
  <% } %>
</select>
  <button type="submit" class="btn btn-primary">Submit</button>
</form>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>
</html>