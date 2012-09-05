<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

<html>
<head><title>Example :: Spring Application</title></head>
<body>
<h1>Hello - Spring Application</h1>
<p>Greetings.</p>
<c:out value="${msg}"/>
<c:out value="${content}"/>
<c:forEach items="${systems_list}" var="system">
	<br/>
	<c:out value="${system.systemId}"/>
</c:forEach>
</body>
</html>