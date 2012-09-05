<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

<html>
<head><title>Example :: Spring Application</title></head>
<body>
<h1>Hello - Spring Application</h1>
<p>Greetings.</p>
<c:out value="${msg}"/>
<c:out value="${content}"/>
parameter names<br/>
<%
java.util.Enumeration _col = request.getParameterNames();
while(_col.hasMoreElements()){
%>
next parm:<%=_col.nextElement()%>
<br/>
<%
}
 _col = request.getAttributeNames();
while(_col.hasMoreElements()){
%>
next parm:<%=_col.nextElement()%>
<br/>
<%
}
%>
session names<br/>
<%
javax.servlet.http.HttpSession _sess = request.getSession();
_col = _sess.getAttributeNames();
while(_col.hasMoreElements()){
%>
next attr:<%=_col.nextElement()%>
<br/>
<%
}

java.util.List<cosmos.hibernate.SystemRep> _list = (java.util.List<cosmos.hibernate.SystemRep>)request.getAttribute("systems_list");
java.util.Iterator _iter = _list.iterator();
while(_iter.hasNext()){
String _rep = ((cosmos.hibernate.SystemRep)_iter.next()).getSystemId();
%>
<a href="./systems_detail.htm?systemId=<%=_rep%>">
<%=_rep%>
</a><br/>
<%
}
%>

<br/>
</body>
</html>