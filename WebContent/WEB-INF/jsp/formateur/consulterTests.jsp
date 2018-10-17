<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
			<h1 class="titre">Liste des tests</h1>	
			
			<ul class="list-group">
				<c:forEach items="${tests}" var="test">
			  		<li class="list-group-item"><a href="${pageContext.request.contextPath}/formateur/modifierTest?testId=${test.idTest}">${test.libelleTest}</a></li>
				</c:forEach>
			</ul>
			<form method="get" action="/QCM/formateur/ajouterTest">
				<div class="row justify-content-center">
						<div class="col-2">
							<button type="submit" class="btn btn-primary style_bouton">Ajouter un test</button>	
						</div>
				</div>
			</form>
		</div>
</body>
</html>