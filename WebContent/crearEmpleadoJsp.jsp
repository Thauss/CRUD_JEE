<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import ="java.util.List,java.util.HashMap,java.util.Map,proyecto.scripplets.Scriptlets,proyecto.pojos.Empleado,proyecto.pojos.Departamento"    
%>
<%
	String ruta=request.getContextPath();
	List<String>  errores;
	String  dep= (String)request.getAttribute("departamento");
	if(request.getAttribute("errores")!=null){
		errores= (List<String>)request.getAttribute("errores");	
	}
	else{
		errores=null;
	}
	
	
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Creacion de empleado</title>


<script>

	//Función que pregunta al usuario si desea realmente enviar los datos
	function conf(){
		
		var result=confirm("¿Enviar los datos?");
		
		if(result==true)
			document.getElementById("Enviar").click();
		
	}
	
</script>

</head>
<body>

	<h1 style="color: orange"><?php echo $titulo ?></h1>
	
	<div style="color: red;">
	<%
		if(errores != null){
			for(String error:errores){
				out.print("<p>"+error+"</p>");
			}
		}
	%>
	</div>
	<h3>Datos del empleado</h3>

	<form id="formulario" action="<%=ruta%>/CrearEmpleado" method="POST">
	
	<table>

	<tr>
	
		<td>Empno</td> <td><input id="eno" type="text" name="empno" value="" /></td>
		
	</tr>	
	
	<tr>
	
		<td>Ename</td> <td><input type="text" name="ename" value="" /></td>
		
	</tr>	
	
	<tr>
		
		<td>Job</td> <td><input type="text" name="job" value="" /></td>
	
	</tr>
	
	<tr>
		
		<td>Hiredate</td> <td><input type="text" name="hiredate" value=""	placeholder="aaaa-mm-dd" /></td> 
	
	</tr>
	
	<tr>
		
		<td>Sal</td> <td><input type="text" name="sal"value="" /></td>
	
	</tr>
	
	<tr>
		
		<td>Comm</td> <td><input type="text"	name="comm" value="" /> </td> 
	
	</tr>
	
	<tr>
		
		<td>Mgr</td> <td><input	type="text" name="mgr" value="" /> 
		
			<select	name="mgrs">
			
				
				
			</select></td> 
	
	</tr>
	
	<tr>
			
		<td>Deptno</td> <td><input type="text" name="deptno"	value="" /> 
		
			<select name="deptnos">
			
				
				
			</select> <span style="color: red;"></td>
	
	</tr>
	
	<tr>
			
		<td><input type="submit" name="env" id="Enviar" style="display: none;" /></td>
		
		<td><input id="confirmar" type="button" value="Enviar" onclick="conf()" /> </td>
		
		<td><a href="<%=ruta%>/listaEmpleados">Volver</a> </td>
	
	</tr>
	
	</table>
	
	</form>
</body>
</html>