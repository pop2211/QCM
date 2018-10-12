<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href='/QCM/css/style.css'>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
<title>Détail du test</title>
<jsp:include page="/WEB-INF/jsp/commons/head.jsp">
	<jsp:param name="contextPath" value="${pageContext.request.contextPath}"/>
</jsp:include>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/commons/header.jsp">
		<jsp:param name="contextPath" value="${pageContext.request.contextPath}"/>
	</jsp:include>
	<form method="post" action="/QCM/epreuve/questions">
		<div class="container">		
			<h1 class="titre">Détail du test</h1>	
			
			<div class="card">
	  			<div class="card-header">
	    			${test.libelleTest}
	  			</div>
		  		<div class="card-body">
		    		<h5 class="card-title"><i class="far fa-clock"></i> ${test.duree}</h5>
		    		<p class="card-text">${test.description}</p>
		  		</div>
			</div>
			<input type="hidden" name="idTest" value="${test.idTest}">
			<input type="hidden" name="idEpreuve" value="${epreuveId}">
			<div class="row justify-content-center">
				<div class="col-2">
					<button type="submit" class="btn btn-primary style_bouton">Passer le test</button>	
				</div>
			</div>
		</div>
	</form>
</body>
</html>