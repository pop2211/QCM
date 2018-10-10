<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Consulter r�sultats</title>
<jsp:include page="/WEB-INF/jsp/head.jsp">
	<jsp:param name="contextPath" value="${pageContext.request.contextPath}"/>
</jsp:include>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/header.jsp">
		<jsp:param name="contextPath" value="${pageContext.request.contextPath}"/>
	</jsp:include>
	
	<table class="table">
  <thead>
    <tr>
      <th scope="col">Epreuve</th>
      <th scope="col">Date d�but</th>
      <th scope="col">Date fin</th>
      <th scope="col">Temps �coul�</th>
      <th scope="col">Etat</th>
      <th scope="col">Note</th>
      <th scope="col">Niveau</th>
    </tr>
  </thead>
  <tbody>
  <c:forEach items="${epreuves}" var="epreuve">
    <tr>
      <td>${epreuve.id}</td>
      <td>${epreuve.dateDebutValidite}</td>
      <td>${epreuve.dateFinValidite}</td>
      <td>${epreuve.tempsEcoule}</td>
      <td>${epreuve.etat}</td>
      <td>${epreuve.noteObtenue}</td>
      <td>${epreuve.niveauObtenu}</td>
    </tr>
     </c:forEach>
  </tbody>
</table>
	

</body>
</html>