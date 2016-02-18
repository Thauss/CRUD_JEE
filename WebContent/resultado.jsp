<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import ="java.util.List,java.util.HashMap,java.util.Map,proyecto.scripplets.Scriptlets,proyecto.pojos.Empleado,proyecto.pojos.Departamento"    
%>
<%
	String ruta=request.getContextPath();
	String respuesta= (String)request.getAttribute("respuesta");

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1><%=respuesta %></h1>
</body>
</html>