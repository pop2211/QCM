<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Questions</title>
<jsp:include page="/WEB-INF/jsp/commons/head.jsp">
	<jsp:param name="contextPath" value="${pageContext.request.contextPath}"/>
</jsp:include>
</head>
<body>

<jsp:include page="/WEB-INF/jsp/commons/header.jsp">
	<jsp:param name="contextPath" value="${pageContext.request.contextPath}"/>
</jsp:include>
<div style="margin-top: 2%;">
	<h2 class="text-center" style="margin-bottom: 2%;">Répondez aux questions : </h2>
  	<div class="container-fluid">
	  	<div class="row">
		  	<div class="col">
				<c:forEach var="question" items="${questions}">
					<div class="card text-center" style="margin-bottom:2%; padding: 1em;">
			    		<h5 class="card-title">${question.getQuestion().getEnonce() }</h5>
					
					  	<div class="card-body">
					  	<div class="container">
						  	<div class="row">
							  	<c:forEach var="proposition" items="${question.getQuestion().getPropositions()}">
							   		<div class="col">
				   			  			<div class="form-group form-check">
								    		<input type="checkbox" class="form-check-input" id="exampleCheck1">
								    		<label class="form-check-label" for="exampleCheck1">${proposition.getEnonce()}</label>
						  				</div>	
							   		</div>
						   		</c:forEach>
						  	</div>
					  	</div>
			
				  		</div>
					</div>
				</c:forEach>
			</div>
			<div class="col">
			Liste des questions
			</div>
		</div>
	</div>
</div>
</body>
</html>