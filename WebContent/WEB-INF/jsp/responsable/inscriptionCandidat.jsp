<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href='/QCM/css/style.css'>
<title>Incription d'un candidat</title>
<jsp:include page="/WEB-INF/jsp/commons/head.jsp">
	<jsp:param name="contextPath" value="${pageContext.request.contextPath}"/>
</jsp:include>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/commons/header.jsp">
		<jsp:param name="contextPath" value="${pageContext.request.contextPath}"/>
	</jsp:include>
	<form method="post" action="">
		<div class="container">		
			<h1 class="titre">Inscription d'un candidat à une épreuve</h1>
  			<div class="form-group">
    			<label for="exampleFormControlSelect1">Epreuves</label>
    			<select class="form-control" id="exampleFormControlSelect1">
    				<c:forEach items="${epreuves}" var="epreuve">
			      		<option>${epreuve.test.libelleTest}</option>
			      		</c:forEach>
			    </select>
  			</div>
  			<div class="form-group">
    			<label for="exampleFormControlSelect1">Candidats</label>
    			<select class="form-control" id="exampleFormControlSelect1">
    				<c:forEach items="${utilisateurs}" var="utilisateur">
			      		<option> ${utilisateur.prenomUtilisateur} ${utilisateur.nomUtilisateur}</option>
			      	</c:forEach>
			    </select>
  			</div>		
  			<div class="row justify-content-center">
				<div class="col-2">
					<button type="button" class="btn btn-primary style_bouton ">Inscrire</button>	
				</div>
			</div>
		</div>
	</form>
</body>
</html>