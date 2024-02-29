<%@ page import="java.util.LinkedList" %>
<%@ page import="entities.*" %>
	
	<%
    	LinkedList<Product> sellProdList = (LinkedList<Product>)session.getAttribute("sellProdList");
		User currentUser = (User)request.getSession().getAttribute("user");
	%>

<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Matisa</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="productList">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="salesList">Ventas</a>
        </li>
        <%
        if(currentUser.getRole().getName().equals("admin"))
        {
        %>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Administración
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="categoryList">Categorias</a></li>
            <li><a class="dropdown-item" href="supplierList">Proveedores</a></li>
            <li><a class="dropdown-item" href="userList">Usuarios</a></li>
          </ul>
        </li>
        <%
        }
        %>
      </ul>
      <div class="d-flex" >
      	<a class="icon-link m-2" href="showCar">
  			<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-cart" viewBox="0 0 16 16">
  			<path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l1.313 7h8.17l1.313-7H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
			</svg>
		</a>
		<%=sellProdList != null ? sellProdList.size() : ""%>
	     <form class="d-flex" action="productSearch" method="post" role="search">
	       <input class="form-control me-2" name="searchTerm" type="search" placeholder="Search" aria-label="Search">
	       <button class="btn btn-outline-success" type="submit">Search</button>
	     </form>
      </div>
    </div>
  </div>
</nav>