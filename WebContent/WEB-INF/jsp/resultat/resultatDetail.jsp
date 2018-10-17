<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href='/QCM/css/style.css'>
<title>Consulter résultats</title>
<jsp:include page="/WEB-INF/jsp/commons/head.jsp">
	<jsp:param name="contextPath" value="${pageContext.request.contextPath}"/>
</jsp:include>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/commons/header.jsp">
		<jsp:param name="contextPath" value="${pageContext.request.contextPath}"/>
	</jsp:include>
	
	<div class="container">
		
		<h1 class="titre">Vos résultats de l'épreuve ${epreuve.test.libelleTest}</h1>
		<h3 class="titre ${epreuve.niveauObtenu}">
			${epreuve.noteObtenue} % / ${epreuve.niveauObtenu}
		</h3>
		
	</div>

</body>
</html>