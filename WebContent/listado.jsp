<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import ="java.util.List,java.util.HashMap,java.util.Map,proyecto.scripplets.Scriptlets,proyecto.pojos.Empleado,proyecto.pojos.Departamento"    
%>
<%
	String ruta=request.getContextPath();
	List<Empleado> empleados= (List<Empleado>)request.getAttribute("empleados");
	Map<String,String> departamentos= (HashMap<String,String>)request.getAttribute("departamentos");
	String [] seleccionados= (String[])request.getAttribute("seleccionados");
	Map<String,String> inputs= (HashMap<String,String>)request.getAttribute("inputs");

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
	</head>
<body>

<h3>Listado de empleados</h3>
	
	<a href="<%=ruta %>/CrearEmpleado">Añade empleado</a>
	
	<fieldset style="border: 3px ridge green;">
	
		<legend>Parámetros de búsqueda</legend>
		
		<form name="fConsulta" action="<%=ruta %>/listaEmpleados" method="get">
		
		<table id="campos">
		
		<tr>
		
			<td>Ename</td> <td><input type="text" name="patronEname"	value="<%= inputs.get("patronEname")%>" /></td> 
			
			<td>Dname</td> <td>
			
   				<%  out.print(Scriptlets.generaSelectMultiple ("departamentos", departamentos, seleccionados , departamentos.size() ));%>
   				
   				 </td> 
   				
   		</tr>
   		
   		<tr>
   				
   			<td>Sal. min</td> <td><input type="text" name="minSal" value="<%= inputs.get("minSal")%>" /></td> 
   			
   			<td>Sal. max</td> <td><input	type="text" name="maxSal" value="<%= inputs.get("maxSal")%>" />	</td>
   		
   		</tr>
   		
   		<tr>
   			
   			<td>Hiredate min.</td> <td><input type="text" name="minHiredate"	value="<%= inputs.get("minHiredate")%>" /></td> 
   			
   			<td>Hiredate max.</td> <td><input type="text" name="maxHiredate"	value="<%= inputs.get("maxHiredate")%>" /> </td> 
   		
   		</tr>
   			
   			
   		<tr>
   		
   			<td><input	type="submit" name="Lanzar consulta" /></td> 
   			
   			<td><input id="mostrarlog"	type="button" value="Mostrar Log" onclick="mostrar()" /></td>
   
		</tr>
		
		</table>

		</form>
		
		<div id="log" style="display: none;"><?php mostrarLog()?></div>
		
	</fieldset>
	
	<br/>
	
	<?php  echo $mensajeError; ?>
	
	<fieldset style="border: 3px ridge green; width: 500px">
	
			<legend>Resultado de la consulta</legend>
			
		<%
			out.println(Scriptlets.generaTablaEmpleados(empleados,ruta));
		%>
		
	</fieldset>
		
</body>
</html>