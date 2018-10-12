<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href='/QCM/css/style.css'>
<title>Choisir un test</title>
<jsp:include page="/WEB-INF/jsp/commons/head.jsp">
	<jsp:param name="contextPath" value="${pageContext.request.contextPath}"/>
</jsp:include>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/commons/header.jsp">
		<jsp:param name="contextPath" value="${pageContext.request.contextPath}"/>
	</jsp:include>
	
	<div class="container">		
		<h1 class="titre">Veuillez choisir un test</h1>	
		
		<ul class="list-group">
			<c:forEach items="${epreuves}" var="epreuve">
		  		<li class="list-group-item"><a href="${pageContext.request.contextPath}/epreuve/detailTest?testId=${epreuve.test.idTest}&epreuveId=${epreuve.idEpreuve}">${epreuve.test.libelleTest}</a></li>
			</c:forEach>
		</ul>
	</div>
</body>
</html>