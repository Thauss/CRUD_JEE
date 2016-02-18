package proyecto.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import proyecto.modelo.DaoDepartamento;
import proyecto.modelo.DaoDepartamentoImplHibernateMySQL;
import proyecto.modelo.DaoEmpleado;
import proyecto.modelo.DaoEmpleadoImplHibernateMySQL;
import proyecto.pojos.Departamento;
import proyecto.pojos.Empleado;
import proyecto.scripplets.Scriptlets;

/**
 * Servlet implementation class CrearEmpleado
 */
@WebServlet("/CrearEmpleado")
public class CrearEmpleado extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
		DaoDepartamento daod= new DaoDepartamentoImplHibernateMySQL();
		
		Departamento dep =  daod.devuelveDepartamentoPorId((byte) 10);
		
		request.setAttribute("departamento", dep.getDname());

		request.getRequestDispatcher("crearEmpleadoJsp.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		List<String> erroresValidacion=new ArrayList();
		Map<String,String> inputs = new HashMap<String, String>();
		String dato="";
		
		dato=request.getParameter("ename");
		if(dato!=null){
		inputs.put("ename", dato);
		}
		else{
			inputs.put("ename", "");
		}
		dato=request.getParameter("job");
		if(dato!=null){
		inputs.put("job", dato);
		}
		else{
			inputs.put("job", "");
		}
		dato=request.getParameter("hiredate");
		if(dato!=null){
		inputs.put("hiredate", dato);
		}
		else{
			inputs.put("hiredate", "");
		}
		dato=request.getParameter("sal");
		if(dato!=null){
		inputs.put("sal", dato);
		}
		else{
			inputs.put("sal", "");
		}
		dato=request.getParameter("comm");
		if(dato!=null){
		inputs.put("comm", dato);
		}
		else{
			inputs.put("comm", "");
		}
		
		
		erroresValidacion=Scriptlets.validacionUsuario(inputs);
		
		dato=request.getParameter("empno");
		if(dato!=null && !dato.equals("")){
			try{
				Short resultadoParse=Short.parseShort(dato);
				inputs.put("empno", dato);
			}
			catch(NumberFormatException e){
				erroresValidacion.add("Error en el campo Numero de empleado, ha de ser numérico");
			}
		}
		else{
			erroresValidacion.add("Error en el campo Numero de empleado, no puede estar vacío");
		}
		
		dato=request.getParameter("deptno");
		if(dato!=null && !dato.equals("")){
			try{
				Byte resultadoParse=Byte.parseByte(dato);
				inputs.put("deptno", dato);
			}
			catch(NumberFormatException e){
				erroresValidacion.add("Error en el campo Número de departamento, ha de ser numérico");
			}
		}
		
		dato=request.getParameter("mgr");
		if(dato!=null && !dato.equals("")){
			try{
				Short resultadoParse=Short.parseShort(dato);
				inputs.put("mgr", dato);
			}
			catch(NumberFormatException e){
				erroresValidacion.add("Error en el campo Numero de jefe, ha de ser numérico");
			}
		}
		
		if(erroresValidacion.isEmpty()){
			DaoEmpleado dao= new DaoEmpleadoImplHibernateMySQL();
			
			dao.insertarEmpleado(inputs);
			request.setAttribute("operacion", "La creación del empleado se realizó con éxito.");

			request.getRequestDispatcher("resultado.jsp").forward(request, response);
			doGet(request, response);
		}else{
			request.setAttribute("errores", erroresValidacion);
			request.getRequestDispatcher("crearEmpleadoJsp.jsp").forward(request, response);
			doGet(request, response);
		}
		
		
		
	}

}
