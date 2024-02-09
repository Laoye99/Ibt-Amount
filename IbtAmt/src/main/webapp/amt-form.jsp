<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Ibt Amount Configuration </title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: blue">
			<div>
				<a href="https://www.xadmin.net" class="navbar-brand"> Ibt Amount Configuration </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Amount</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${user != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${user == null}">
					<form action="insert" method="post">
				</c:if>
				<caption>
					<h2>
						<c:if test="${user != null}">
            			Edit Amount
            		</c:if>
						<c:if test="${user == null}">
            			Add New Amount
            		</c:if>
					</h2>
				</caption>

				<c:if test="${user != null}">
					<input type="hidden" name="acsn" value="<c:out value='${user.acsn}' />" />
				</c:if>
				
				<fieldset class="form-group">
					<label>FSP</label> <input type="text"
						value="<c:out value='${user.fsp}' />" class="form-control"
						name="fsp" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>LOWER BOUND</label> <input type="number" 
						value="<c:out value='${user.lower_bound}' />" class="form-control"
						name="lower_bound" id="lower_bound">
				</fieldset>

				<fieldset class="form-group">
					<label>UPPER BOUND</label> <input type="number" step="any"
						value="<c:out value='${user.upper_bound}' />" class="form-control"
						name="upper_bound" id="upper_bound">
				</fieldset>

				<button type="submit" class="btn btn-success" onsubmit="validateFigure()">Save</button>
				</form>
			</div>
		</div>
	</div>
    <script src="/script.js"></script>
</body>
</html>