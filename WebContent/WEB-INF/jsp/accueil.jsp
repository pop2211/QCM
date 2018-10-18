<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="error.jsp" isErrorPage="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href='/QCM/css/style.css'>
<title>Connexion</title>

<jsp:include page="/WEB-INF/jsp/commons/head.jsp">
	<jsp:param name="contextPath" value="${pageContext.request.contextPath}"/>
</jsp:include>
<link rel="stylesheet" type="text/css" href="/QCM/css/connexion.css">
</head>
<body>
<jsp:include page="/WEB-INF/jsp/commons/header.jsp">
		<jsp:param name="contextPath" value="${pageContext.request.contextPath}"/>
	</jsp:include>
<div class="container">
	<c:set var="inscriptionReussite" value="${inscription}" />
	<c:if test="${not empty inscriptionReussite}">
		<div class="alert alert-success" role="alert">
  			${inscription}
		</div>
	</c:if>
	
	<c:set var="inscriptionPromotionReussite" value="${inscriptionPromotion}" />
	<c:if test="${not empty inscriptionPromotionReussite}">
		<div class="alert alert-success" role="alert">
  			${inscriptionPromotion}
		</div>
	</c:if>
	
	<c:set var="desinscriptionCandidatReussite" value="${desinscription}" />
	<c:if test="${not empty desinscriptionCandidatReussite}">
		<div class="alert alert-success" role="alert">
  			${desinscription}
		</div>
	</c:if>
	
	<c:set var="ajoutReussi" value="${ajout}" />
	<c:if test="${not empty ajoutReussi}">
		<div class="alert alert-success" role="alert">
  			${ajout}
		</div>
	</c:if>
	
	<c:set var="modificationReussite" value="${modification}" />
	<c:if test="${not empty modificationReussite}">
		<div class="alert alert-success" role="alert">
  			${modification}
		</div>
	</c:if>
	
	<c:set var="ajoutTestReussi" value="${ajoutTest}" />
	<c:if test="${not empty ajoutTestReussi}">
		<div class="alert alert-success" role="alert">
  			${ajoutTest}
		</div>
	</c:if>
	
	<h2 class="titre">Bonjour ${sessionScope.sessionUtilisateur.prenomUtilisateur} ${sessionScope.sessionUtilisateur.nomUtilisateur}, bienvenue sur la plateforme ECF.</h2>
	<img class="rounded mx-auto d-block" src="/QCM/image/kiki.jpg" alt="">
	<img class="rounded mx-auto d-block" src="/QCM/image/cage.jpg" alt="">
</div>
</body>
</html>