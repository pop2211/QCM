<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Questions</title>
<jsp:include page="/WEB-INF/jsp/commons/head.jsp">
	<jsp:param name="contextPath" value="${pageContext.request.contextPath}"/>
</jsp:include>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css" integrity="sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz" crossorigin="anonymous">

</head>
<body>

<jsp:include page="/WEB-INF/jsp/commons/header.jsp">
	<jsp:param name="contextPath" value="${pageContext.request.contextPath}"/>
</jsp:include>
<div style="margin-top: 2%;">
  	<div class="container-fluid">
	  	<div class="row">
		  	<div class="col-10 offset-1">
		  		<h2 class="text-center" style="margin-bottom: 2%;">Répondez aux questions : </h2>
					<div style="margin: 2%;">
					<form method="GET" action="/QCM/epreuve/questions">
						<button type="submit" class="btn btn-primary card-link">
			 			<i class="fas fa-hand-point-left"></i> Revenir à la question
			 		</button>
					</form>
				</div>
				
				<c:forEach var="question" items="${questions}">
					<div class="card text-center" style="margin-bottom:5%; padding: 1em;">
			    		<h5 class="card-title">${question.getQuestion().getEnonce() }</h5>
					
					  	<div class="card-body">
					  	<div class="container">
						  	<div class="row">
							  	<c:forEach var="proposition" items="${question.getQuestion().getPropositions()}">
							   		<div class="col">
				   			  			<div class="form-group form-check">
		   			  						<c:if test="${proposition.getChecked()}">
  												<input disabled type="checkbox" class="form-check-input" checked>
								    			<label class="form-check-label" for="exampleCheck1"><c:out value="${proposition.getEnonce()}" ></c:out></label>
			    							</c:if>
			   			  					<c:if test="${!proposition.getChecked()}">
  												<input disabled type="checkbox" class="form-check-input" id="checkbox">
								    			<label class="form-check-label" for="exampleCheck1"><c:out value="${proposition.getEnonce()}" ></c:out></label>
			    							</c:if>
						  				</div>	
							   		</div>
						   		</c:forEach>
						  	</div>
					  	</div>
			
				  		</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</div>
</body>
</html>