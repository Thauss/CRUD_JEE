package proyecto.scripplets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import proyecto.pojos.Empleado;

@WebServlet("/Scriptlets")
public class Scriptlets extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String NL = "\n";
	private LinkedHashMap<String,String> arrayValoresYEtiquetas = new LinkedHashMap<String,String>(); 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		arrayValoresYEtiquetas.put("ES", "España");
		arrayValoresYEtiquetas.put("FR", "Francia");
		arrayValoresYEtiquetas.put("IT", "Italia");
		arrayValoresYEtiquetas.put("PT", "Portugal");		

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println(generaBotonesRadio("pais", arrayValoresYEtiquetas, "IT"));
		out.println("<br />");
		out.println(generaCajasChequeo(arrayValoresYEtiquetas, new String[]{"FR", "PT"}));
		out.println("<br />");
		out.println(generaArrayCajasChequeo("pais[]", arrayValoresYEtiquetas, new String[]{"ES", "IT"}));
		out.println("<br />");
		out.println(generaSelectSimple("pais", arrayValoresYEtiquetas, "FR"));
		out.println("<br />");
		out.println(generaSelectMultiple("pais[]", arrayValoresYEtiquetas, new String[]{"FR", "IT"}, 3));
	}
	
	
	
	/*
	  nombreControl: valor del atributo name de los radios
	  arrayValoresYEtiquetas: array asociativo de pares (valor, etiqueta) para el atributo value de cada radio
	    y para el label de cada radio
	  valorSeleccionado: valor del atributo value del elemento seleccionado, si es vacío
	    no se selecciona ninguno	
	*/
	public static String generaBotonesRadio(String nombreControl, Map<String,String> arrayValoresYEtiquetas, String valorSeleccionado) {
	  String salida = "";
	  Iterator<String> iteradorConjuntoClaves = arrayValoresYEtiquetas.keySet().iterator();
	  while (iteradorConjuntoClaves.hasNext()){
		  String clave = iteradorConjuntoClaves.next();
		  String valor = arrayValoresYEtiquetas.get(clave);
		  if (valorSeleccionado.equals(clave)) {
			  salida += "<label>" + valor + "</label><input type=\"radio\" name=\"" + nombreControl + "\" value=\"" + clave + "\" checked=\"checked\" />" + "\n";
		  } else {
			  salida += "<label>" + valor + "</label><input type=\"radio\" name=\"" + nombreControl + "\" value=\"" + clave + "\" />" + "\n";
		  }
	  }
	  return salida;
	}
	
	public static List<String> validacionUsuario(Map datos) {
		List<String> errores=new ArrayList();  
		
		String valor;
		  
		  	valor=(String) datos.get("ename");
			if(valor !=null && !valor.equals("")){
				if(!valor.matches("[a-zA-Z]{1,10}")){
					errores.add("El campo nombre no es correcto");
				}
			}
			
			valor=(String) datos.get("job");
			if(valor !=null && !valor.equals("")){
				if(!valor.matches("[a-zA-Z]{1,20}")){
					errores.add("El campo trabajo no es correcto");
				}
			}
			
			valor=(String) datos.get("sal");
			if(valor !=null && !valor.equals("")){
				try{
					float dato=Float.parseFloat(valor);
				}
				catch(NumberFormatException e){
					errores.add("El campo Salario  no es numérico");
				}
				
			}
			
			
			valor=(String) datos.get("comm");
			if(valor !=null && !valor.equals("")){
				try{
					float dato=Float.parseFloat(valor);
				}
				catch(NumberFormatException e){
					errores.add("El campo comision no es numérico");
				}
			}
			
			
			valor=(String) datos.get("hiredate");
			if(valor !=null && !valor.equals("")){
				
				Date fechaOriginal;
				SimpleDateFormat formateo = new SimpleDateFormat("yyyy-MM-dd");
				
				try {
					
					fechaOriginal=formateo.parse(valor);
					String fechaFormateada= formateo.format(fechaOriginal);
					if(valor.equals(fechaFormateada)){
						
					}
					else{
						errores.add("El campo fecha no es correcto");
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					errores.add("El campo fecha no es correcto");
				}
			}
			
			

			return errores;
	}
	
	public static String generaTablaEmpleados(List<Empleado> listaEmpleados,String ruta) {
		  
		  String salida = "";
		  salida += "<table style='border:7px ridge orange;'>";
			
		  salida += "<tr>";
			
		  salida += "<th style='border-bottom:2px solid orange; color:green;'>EMPNO</th>";
			
		  salida += "<th style='border-bottom:2px solid orange; color:green;'>ENAME</th>";
			
		  salida += "<th style='border-bottom:2px solid orange; color:green;'>JOB</th>";
			
		  salida += "<th style='border-bottom:2px solid orange; color:green;'>MGR</th>";
			
		  salida += "<th style='border-bottom:2px solid orange; color:green;'>HIREDATE</th>";
			
		  salida += "<th style='border-bottom:2px solid orange; color:green;'>SAL</th>";
			
		  salida += "<th style='border-bottom:2px solid orange; color:green;'>COMM</th>";
			
		  salida += "<th style='border-bottom:2px solid orange; color:green;'>DEPTNO</th>";
			
		  salida += "<th style='border-bottom:2px solid orange; color:green;'>MODF</th>";
			
		  salida += "<th style='border-bottom:2px solid orange; color:green;'>DEL</th>";
			
		  salida += "</tr>";
			
		  for (Empleado empleado: listaEmpleados){

			 
			  salida += "<tr>";
				
			  salida += "<td>" + empleado.getEmpno() + "</td>";
			  
			  salida += "<td>" + empleado.getEname() + "</td>";
				
			  salida += "<td>" + empleado.getJob() + "</td>";
			
			  if(empleado.getEmpleadoJefe()!=null){
			  salida += "<td>" +  empleado.getEmpleadoJefe().getEname() + "</td>";
			  }else{
				  salida += "<td></td>";
			  }
			  salida += "<td>" +  empleado.getHiredate() + "</td>";
				
			  salida += "<td>" +  empleado.getSal() + "</td>";
				
			  salida += "<td>" +  empleado.getComm() + "</td>";
			  
			  if(empleado.getDept()!=null){
			  salida += "<td>" +  empleado.getDept().getDname() + "</td>";
			  }else{
				  salida += "<td></td>";
			  }	
			  salida += "<td><a href='"+ruta+"/ModificarEmpleado?empno=" + empleado.getEmpno() + "'><img src='edit-icon.png' width='20' height='20'</img></a></td>";
				
			  salida += "<td><a href='"+ruta+"/listaEmpleados?borrar=" + empleado.getEmpno() + "'><img src='delete-icon.png' width='20' height='20'</img></a></td>";
				
			  salida += "</tr>";
				
		  }
		  salida +="</table>";
		  return salida;
		}	

	/*
	  arrayValoresYEtiquetas: array asociativo de pares (valor, etiqueta) para el atributo value
	    de cada checkbox y para el nombre y label de cada checkbox
	  valoresSeleccionados: un array de valores que son los value de los ckeckbox seleccionados
	*/
	public static String generaCajasChequeo(Map<String,String> arrayValoresYEtiquetas, String[] valoresSeleccionados) {
		String salida = "";
		int numerovaloresSeleccionados = valoresSeleccionados.length;  // cuantos valores seleccionados se han recibido
		if (numerovaloresSeleccionados > 0) {  // hay algún valor seleccionado
			int contadorValoresSeleccionados = 0;  // cuántos valores seleccionados ya se han recorrido
			Iterator<String> iteradorConjuntoClaves = arrayValoresYEtiquetas.keySet().iterator();
			while (iteradorConjuntoClaves.hasNext()) {
				String clave = iteradorConjuntoClaves.next();
				String valor = arrayValoresYEtiquetas.get(clave);
				if ( (contadorValoresSeleccionados < numerovaloresSeleccionados) &&
		             (valoresSeleccionados[contadorValoresSeleccionados].equals(clave)) ) {
					salida += "<label>" + valor + "</label><input type=\"checkbox\" name=\"" + valor + "\" value=\"" + clave + "\" checked=\"checked\" />" + "\n";
					contadorValoresSeleccionados++;
				} else {
					salida += "<label>" + valor + "</label><input type=\"checkbox\" name=\"" + valor + "\" value=\"" + clave + "\" />" + "\n";
				}  
			}
		} else {
			Iterator<String> iteradorConjuntoClaves = arrayValoresYEtiquetas.keySet().iterator();
			while (iteradorConjuntoClaves.hasNext()) {
				String clave = iteradorConjuntoClaves.next();
				String valor = arrayValoresYEtiquetas.get(clave);
				salida += "<label>" + valor + "</label><input type=\"checkbox\" name=\"" + valor + "\" value=\"" + clave + "\" />" + "\n";
			}    
		}  
		return salida;
	}

	/*
	  arrayValoresYEtiquetas: array asociativo de pares (valor, etiqueta) para el atributo value
	    de cada checkbox y para el nombre y label de cada checkbox
	  valoresSeleccionados: un array de valores que son los value de los ckeckbox seleccionados
	*/
	public static String generaArrayCajasChequeo(String nombreControl, Map<String,String> arrayValoresYEtiquetas, String[] valoresSeleccionados) {
		String salida = "";
		int numerovaloresSeleccionados = valoresSeleccionados.length;  // cuántos valores seleccionados se han recibido
		if (numerovaloresSeleccionados > 0) {  // hay algún valor seleccionado
			int contadorValoresSeleccionados = 0;  // cuántos valores seleccionados ya se han recorrido
			Iterator<String> iteradorConjuntoClaves = arrayValoresYEtiquetas.keySet().iterator();
			while (iteradorConjuntoClaves.hasNext()) {
				String clave = iteradorConjuntoClaves.next();
				String valor = arrayValoresYEtiquetas.get(clave);
				if ( (contadorValoresSeleccionados < numerovaloresSeleccionados) &&
		             (valoresSeleccionados[contadorValoresSeleccionados].equals(clave)) ) {
					salida += "<label>" + valor + "</label><input type=\"checkbox\" name=\"" + nombreControl + "\" value=\"" + clave + "\" checked=\"checked\" />" + "\n";
					contadorValoresSeleccionados++;
				} else {
					salida += "<label>" + valor + "</label><input type=\"checkbox\" name=\"" + nombreControl + "\" value=\"" + clave + "\" />" + "\n";
				}  
			}
		} else {
			Iterator<String> iteradorConjuntoClaves = arrayValoresYEtiquetas.keySet().iterator();
			while (iteradorConjuntoClaves.hasNext()) {
				String clave = iteradorConjuntoClaves.next();
				String valor = arrayValoresYEtiquetas.get(clave);
				salida += "<label>" + valor + "</label><input type=\"checkbox\" name=\"" + nombreControl + "\" value=\"" + clave + "\" />" + "\n";
			}    
		}  
		return salida;
	}


	/*
	  nombreControl: valor del atributo name del select
	  arrayValoresYTextos: array asociativo de pares (valor, texto) para el atributo value de cada option
	    y para el texto de cada otion
	  valorSeleccionado: value del option seleccionado
	*/
	public  String generaSelectSimple(String nombreControl, Map<String,String> arrayValoresYTextos, String valorSeleccionado) {
		String salida = "";
	    salida += "<select name=\"" + nombreControl + "\">" + "\n";
		Iterator<String> iteradorConjuntoClaves = arrayValoresYEtiquetas.keySet().iterator();
		while (iteradorConjuntoClaves.hasNext()) {
			String clave = iteradorConjuntoClaves.next();
			String valor = arrayValoresYEtiquetas.get(clave);
			if (valorSeleccionado.equals(clave)) {
				salida += "  <option value=\"" + clave + "\" selected=\"selected\">" + valor + "</option>" + "\n";  
			  } else {
				salida += "  <option value=\"" + clave + "\">" + valor + "</option>" + "\n";
			  }
		  }
		  salida += "</select>" + "\n";	
		  return salida;	
	}

	
	/*
	  nombreControl: valor del atributo name del select
	  arrayValoresYTextos: array asociativo de pares (valor, texto) para el atributo value de cada option
	    y para el texto de cada otion
	  valoresSeleccionados: un array de valores, los value de los option seleccionados
	  opcionesVisibles: número de opciones visibles del select múltiple
	*/
	public static String generaSelectMultiple(String nombreControl, Map<String,String> arrayValoresYTextos, String[] valoresSeleccionados, int opcionesVisibles) {
		String salida = "";	
	    salida += "<select name=\"" + nombreControl + "\" multiple=\"multiple\" size=\"" + opcionesVisibles + "\">" + "\n";
	    int contadorValoresSeleccionados = 0;  // cuántos valores seleccionados ya se han recorrido
		int numerovaloresSeleccionados = valoresSeleccionados.length;  // cuántos valores seleccionados se han recibido
		Iterator<String> iteradorConjuntoClaves = arrayValoresYTextos.keySet().iterator();
		while (iteradorConjuntoClaves.hasNext()) {
			String clave = iteradorConjuntoClaves.next();
			String valor = arrayValoresYTextos.get(clave);
			if ( (contadorValoresSeleccionados < numerovaloresSeleccionados) &&
	             (valoresSeleccionados[contadorValoresSeleccionados].equals(clave)) ) {
				salida += "  <option value=\"" + clave + "\" selected=\"selected\">" + valor + "</option>" + "\n";
				contadorValoresSeleccionados++;
			} else {
				salida += "  <option value=\"" + clave + "\">" + valor + "</option>" + "\n";
			}
		}
		salida += "</select>" + "\n";	
		return salida;
	}
	
	
/*	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
*/
}
