<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="error.jsp" isErrorPage="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Connexion</title>

<jsp:include page="/WEB-INF/jsp/commons/head.jsp">
	<jsp:param name="contextPath" value="${pageContext.request.contextPath}"/>
</jsp:include>
<link rel="stylesheet" type="text/css" href="/QCM/css/connexion.css">
</head>
<body id="LoginForm">
<div class="container">
	<h1 class="form-heading">Plateforme ECF</h1>	
	<div class="login-form">
		<div class="main-div">
		    <div class="panel">
				<h2>Plateforme ECF</h2>
			   	<p>Entre ton email et ton mot de passe poto !</p>
			   	<c:if test="${form.resultat != null}">
					<div class="alert alert-danger mt-5">${form.resultat}</div>
		 		</c:if>
		 	</div>
		 	<form name="login" id="Login" action="${pageContext.request.contextPath}/login" method="post">
		        <div class="form-group">
		            <input type="email" class="form-control" name="email" id="inputEmail" placeholder="Email">
		        	<span class="erreur">${form.erreurs['email']}</span>
		        </div>
		        <div class="form-group">
		            <input type="password" class="form-control" name="password" id="inputPassword" placeholder="Mot de passe">
		        	<span class="erreur">${form.erreurs['password']}</span>
		        </div>
		        <div class="forgot">
		        	<!--<a href="reset.html">Forgot password?</a>-->
				</div>
		        <button type="submit" class="btn btn-primary">Login</button>
		    </form>
		    </div>
		</div>
	</div>
</div>
</body>
</html>