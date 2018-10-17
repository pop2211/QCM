<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href='/QCM/css/style.css'>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css" integrity="sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz" crossorigin="anonymous">
<title>Consulter résultats</title>
<jsp:include page="/WEB-INF/jsp/commons/head.jsp">
	<jsp:param name="contextPath" value="${pageContext.request.contextPath}"/>
</jsp:include>
<script type="text/javascript">
	function CheckDetailResult(idEpreuve) {
		window.location.href = '/QCM/resultat?idEpreuve=' + idEpreuve;
	}
</script>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/commons/header.jsp">
		<jsp:param name="contextPath" value="${pageContext.request.contextPath}"/>
	</jsp:include>
	
	<div class="container">
		
		<h1 class="titre">Vos résultats</h1>	
	
	<table class="table table-hover text-center">
	  <thead>
	    <tr>
	      <th scope="col">Epreuve</th>
	      <th scope="col">Date début</th>
	      <th scope="col">Date fin</th>
	      <th scope="col">Temps écoulé</th>
	      <th scope="col">Note</th>
	      <th scope="col">Niveau</th>
	      <th scope="col">Action</th>
	    </tr>
	  </thead> 
  <tbody>
  <c:forEach items="${epreuves}" var="epreuve">
    <tr class="link" onclick="CheckDetailResult(${epreuve.idEpreuve})">
      <td>${epreuve.test.libelleTest}</td>
      <td>${epreuve.dateDebutValidite}</td>
      <td>${epreuve.dateFinValidite}</td>
      <td>${epreuve.tempsEcoule}</td>
      <td>${epreuve.noteObtenue} %</td>
      <td>${epreuve.niveauObtenu}</td>
      <td><a href="/QCM/resultat?idEpreuve=${epreuve.idEpreuve}"><i class="far fa-eye"></a></i></td>
    </tr>
    </a>
     </c:forEach>
  </tbody>
</table>
	
</div>

</body>
</html>