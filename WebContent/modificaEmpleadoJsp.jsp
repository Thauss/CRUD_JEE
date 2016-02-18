<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import ="proyecto.scripplets.Scriptlets,proyecto.pojos.*,proyecto.pojos.Departamento,proyecto.modelo.*"    
%>
<%
	String ruta=request.getContextPath();
	Short empno= (Short)request.getAttribute("empno");
	
	
	DaoEmpleado dao= new DaoEmpleadoImplHibernateMySQL();
	
	Empleado emp =  dao.devuelveEmpleadoPorId(empno);
	String empleadoJefe ="";
	String departamento= "";
	
	if(emp.getEmpleadoJefe()!=null){
		empleadoJefe= Short.toString(emp.getEmpleadoJefe().getEmpno());	
	}
	if(emp.getDept()!=null){
		departamento= Byte.toString(emp.getDept().getDeptno());	
	}
	
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modificacion de empleado</title>


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

	<?php echo pintarErrores($errores)?>
	
	<h3>Datos del empleado</h3>

	<form id="formulario" action="<%=ruta%>crearEmp.php">
	
	<table>

	<tr>
	
		<td>Empno</td> <td><input id="eno" type="text" name="empno" value="<%= emp.getEmpno() %>" /></td>
		
	</tr>	
	
	<tr>
	
		<td>Ename</td> <td><input type="text" name="ename" value="<%= emp.getEname()%>" /></td>
		
	</tr>	
	
	<tr>
		
		<td>Job</td> <td><input type="text" name="job" value="<%= emp.getJob()%>" /></td>
	
	</tr>
	
	<tr>
		
		<td>Hiredate</td> <td><input type="text" name="hiredate" value="<%= emp.getHiredate()%>"	placeholder="aaaa-mm-dd" /></td> 
	
	</tr>
	
	<tr>
		
		<td>Sal</td> <td><input type="text" name="sal"value="<%= emp.getSal()%>" /></td>
	
	</tr>
	
	<tr>
		
		<td>Comm</td> <td><input type="text"	name="comm" value="<%= emp.getComm()%>" /> </td> 
	
	</tr>
	
	<tr>
		
		<td>Mgr</td> <td><input	type="text" name="mgr" value="<%= empleadoJefe %>" /> 
		
			<select	name="mgrs">
			
				
				
			</select></td> 
	
	</tr>
	
	<tr>
			
		<td>Deptno</td> <td><input type="text" name="deptno"	value="<%= departamento %>" /> 
		
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