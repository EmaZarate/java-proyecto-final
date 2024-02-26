<%@ page import="java.util.LinkedList" %>
<%@ page import="entities.Product" %>
<%@ page import="logic.*" %>

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
	
	<title>Carrito</title>
	
</head>
<body>
<%@ include file="Header.jsp" %>
<div class="container mt-2">
		<div class="row mb-4">
			<div class="col-11  mt-1">
				<h4>Productos</h4>
			</div>
		</div>
		
		<div class="row">
            	<div class="col-12 col-sm-12 col-lg-12">
                	<div class="table-responsive">
                	 	<% if(sellProdList == null || sellProdList.size() == 0) { %>
                	 		<p>No hay productos en su carro</p>
                	 	<%} %>
                	 	
	                	 	<table class="table">
	                    		<thead>
	                    			<tr>
	                    		    	<th>nombre</th>
	                    		    	<th>Precio</th>
	                        			<th></th>
	                      			</tr>
	                      		</thead>
	                    		<tbody>
	                    		<% for (Product prod : sellProdList) { %>
	                    			<tr>
	                    				<td><%=prod.getName()%></td>
										<td><%=prod.getNumberSale() * prod.getSalePrice()%></td>
	                    				<td>
	                    					<a href="deleteProductToCar?productId=<%=prod.getProductId()%>"><button type="button" class="btn btn-success mx-1">Eliminar</button></a>
	                    				</td>
	                    			</tr>
	                    		<% } %>
	                    			<tr>
	                    				<td><strong>Total</strong></td>
										<td><strong><%=CarLogic.showTotal(sellProdList)%></strong></td>
	                    				<td>
	                    					<a href="sellProductToCar"><button type="button" class="btn btn-primary mx-1">Comprar</button></a>
	                    				</td>
	                    			</tr>
	                    		</tbody>
	        				</table>
                	 	
                    	
       				</div> <!-- /table-responsive -->
     			</div> <!-- <div class="col-12 col-sm-12 col-lg-12"> -->
     	</div> <!-- row -->
	</div> <!-- /container -->

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>
</html>