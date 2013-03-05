<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!doctype html>
<html lang="en">
	<head>
		<link rel="stylesheet" href="<c:url value="/resources/bootstrap.css"/>">
	
		<script src="<c:url value="/resources/jquery.js"/>"></script>
		<script src="<c:url value="/resources/angular.js"/>"></script>
		<script src="<c:url value="/resources/bootstrap.js"/>"></script>
	
		<title>PlacePool</title>
	</head>
	<body>
	
		<div class="container">
		
			<div class="row">
				<div class="span4 well">
					${serverTime}
				</div>
			</div>
		
		
		</div>

	
	</body>
</html>
