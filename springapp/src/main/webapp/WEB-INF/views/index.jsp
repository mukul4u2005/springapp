<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

	<head>
		<title>Spring MVC Starter Application</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel="stylesheet" type="text/css" href="<c:url value="/static/resources/css/screen.css"/>"/>
	</head>

	<body>
		<div id="container">
			<div id="content">
				<form:form commandName="newWeather" id="reg">
					<h2>Weather Information</h2>
					<table>
						<tbody>
							<tr>
								<td><form:label path="pinCode">Pin Code:</form:label></td>
								<td><form:input path="pinCode"/></td>
								<td><form:errors class="invalid" path="pinCode"/></td>
							</tr>
						</tbody>
					</table>
					<table>
						<tr>
							<td>
								<input type="submit" value="Find Weather" class="register"/>
							</td>
						</tr>
					</table>
				</form:form>
				<h2>Weather Information</h2>
				<c:choose>
					<c:when test="${!newWeather.weatherFind}">
						<c:out value="${newWeather.msg}"></c:out>
					</c:when>
					<c:otherwise>
						<table class="simpletablestyle">
							<thead>
								<tr>
									<th>City</th>
									<th>State</th>
									<th>Current temperature  </th>
								</tr>
							</thead>
							<tbody>
								
									<tr>
										<td>${newWeather.city}</td>
										<td>${newWeather.state}</td>
										<td>${newWeather.temp}</td>
									
</tr>
							</tbody>
						</table>
						
					</c:otherwise>
				</c:choose>
			</div>
			
			
		</div>
	</body>
</html>
