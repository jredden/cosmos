<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<html>
<head>
<title>Systems Detail</title>
</head>
<body>
<c:forEach items="${cluster_list}" var="cluster">
	<c:out value="${cluster.systemId}"/>
	<br/>
	<c:out value="${cluster.distanceSysVirtCentre}"/>
	<br/>
	<c:out value="${cluster.clusterDescription}"/>
	<br/>	
	<c:out value="${cluster.clusterId}"/>
	<br/>
	<c:out value="${cluster.planetsAllowed}"></c:out>
	<br/>	
	<c:out value="${cluster.angleInRadians}"></c:out>
	<br/>	
	<c:out value="${numberStarsInCluster}"></c:out>
</c:forEach>
<br/>
<c:forEach items="${star_list}" var="stars">
	<c:out value="${stars.systemId}"/>
	<br/>	
	<c:out value="${stars.clusterId}"/>
	<br/>	
	<c:out value="${stars.distanceClustVirtCentre}"/>
	<br/>	
	<c:out value="${stars.luminosity}"/>
	<br/>	
	<c:out value="${stars.noPlanetsAllowed}"/>
	<br/>	
	<c:out value="${stars.angleInRadiansS}"/>
	<br/>	
	<c:out value="${stars.starColor}"/>
	<br/>	
	<c:out value="${stars.starType}"/>
	<br/>	
	<c:out value="${stars.starSize}"/>
	<br/>	
</c:forEach>

<c:forEach items="${planetoid_list}" var="planetoid"> 
	<br/>
	<c:out value="${planetoid.planetoid_rep.planetoidId}"/>
	<c:set var="planetoid_list" value="${planetoid.planetoid}"/>
	<c:set var="atmosphere_list" value="${planetoid.planetoid_atmosphere_rep}"/>

	<c:forEach items="${planetoid_list}" var="planetoid_detail">
		<br/>
		<c:out value="${planetoid_detail.planetoidId}"/>
		<c:out value="${planetoid_detail.radius}"/>
		<c:out value="${planetoid_detail.distanceToPimary}"/>
		<c:out value="${planetoid_detail.degree}"/>
		<c:out value="${planetoid_detail.temperature}"/>
		<c:out value="${planetoid_detail.percentWater}"/>
		<br/>
	</c:forEach>
	<c:forEach items="${atmosphere_list}" var="atmosphere_detail">
		<br/>
		<c:out value="${atmosphere_detail.chem_name}"/>
		<c:out value="${atmosphere_detail.percentage}"/>
		<br/>
	</c:forEach>
	
</c:forEach>

<br/>
<a href="<c:url value="./systems.htm"></c:url>">
return to systems</a>
<br/>

</body>
</html>