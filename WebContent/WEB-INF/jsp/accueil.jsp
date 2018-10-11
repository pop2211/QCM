<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="error.jsp" isErrorPage="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Connexion</title>

<jsp:include page="/WEB-INF/jsp/head.jsp">
	<jsp:param name="contextPath" value="${pageContext.request.contextPath}"/>
</jsp:include>
<link rel="stylesheet" type="text/css" href="/QCM/css/connexion.css">
</head>
<body id="LoginForm">

	<!--
	<jsp:include page="/WEB-INF/jsp/header.jsp">
		<jsp:param name="contextPath" value="${pageContext.request.contextPath}"/>
	</jsp:include>
	 -->
	
<div class="container">
	<h1 class="form-heading">Plateforme ECF</h1>
	<div class="login-form">
		<div class="main-div">
		    <div class="panel">
				<h2>Plateforme ECF</h2>
			   	<p>Entre ton email et ton mot de passe poto !</p>
		 	</div>
		 	<h1>c'est l'accueil</h1>
		    </div>
		</div>
	</div>
</div>
</body>
</html>