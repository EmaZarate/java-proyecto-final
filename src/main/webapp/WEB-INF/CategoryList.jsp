<%@ page import="java.util.LinkedList" %>
<%@ page import="entities.Category" %>

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
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" />
	<title>Categorias</title>
	
	<%
    	LinkedList<Category> categoryList = (LinkedList<Category>)request.getAttribute("categoryList");
	%>
	
</head>
<body>
<%@ include file="Header.jsp" %>
<div class="container mt-2">
<img src="assets/imgs/categorias-banner.jpg" class="img-banner mb-3">
		<div class="row mb-4">
			<div class="col-11  mt-1">
				<h4>Categorias</h4>
			</div>
			<form class="col-1" action="createUpdateCategory">
				<button type="submit" class="btn btn-outline-primary">Agregar</button>
			</form>
		</div>
		
		<div class="row">
            	<div class="col-12 col-sm-12 col-lg-12">
                	<div class="table-responsive">
                    	<table class="table">
                    		<thead>
                    			<tr>
                    				<th>ID</th>
                    		    	<th>Nombre</th>
                        			<th>Habilitado</th>
                        			<th></th>
                      			</tr>
                      		</thead>
                    		<tbody>
                    		<% for (Category cat : categoryList) { %>
                    			<tr>
                    				<td><%=cat.getCategoryId()%></td>
                    				<td><%=cat.getName()%></td>
									<td>
                    				    <div class="custom-control custom-checkbox">
                                        	<input type="checkbox" class="custom-control-input" id="defaultDisabled" <%=cat.isActive()?"checked":""%> disabled>
                                        <label class="custom-control-label" for="defaultDisabled"><%=cat.isActive()?"Si":"No"%></label>
                                		</div>
                    				</td>
                    				<td>
                    				<a href="createUpdateCategory?categoryid=<%=cat.getCategoryId()%>"><button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
  										Actualizar
									</button></a> 
                    				</td>
                    			</tr>
                    		<% } %>
                    		</tbody>
        				</table>
       				</div> <!-- /table-responsive -->
     			</div> <!-- <div class="col-12 col-sm-12 col-lg-12"> -->
     	</div> <!-- row -->
	</div> <!-- /container -->

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>

</body>
</html>