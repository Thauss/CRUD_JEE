package proyecto.servlets;

import java.io.IOException;
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

/**
 * Servlet implementation class listaEmpleados
 */
@WebServlet("/listaEmpleados")
public class listaEmpleados extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		DaoEmpleado dao = new DaoEmpleadoImplHibernateMySQL();
		
		List<Empleado> listaEmpleados = dao.devuelveTodosEmpleados();
		
		DaoDepartamento daod= new DaoDepartamentoImplHibernateMySQL();
		
		List<Departamento> listaDepartamentos =  daod.devuelveTodosDepartamentos();
		
		Map<String,String> departamentosMapa = new HashMap();
		
		Map<String,String> inputs = new HashMap();
		String dato="";
		dato=request.getParameter("borrar");
		if(dato!=null){
			dao.borrarEmpleado(dato);
		}
		
		
		
		dato=request.getParameter("patronEname");
		if(dato==null){
			inputs.put("patronEname", "");
			
		}
		else{
			dato=request.getParameter("patronEname");

			inputs.put("patronEname", dato);
		}
		dato=request.getParameter("minSal");
		if(dato==null){
			inputs.put("minSal", "");
			
		}
		else{
			dato=request.getParameter("minSal");

			inputs.put("minSal", dato);
		}
		dato=request.getParameter("maxSal");
		if(dato==null){
			inputs.put("maxSal", "");
			
		}
		else{
			dato=request.getParameter("maxSal");

			inputs.put("maxSal", dato);
		}
		dato=request.getParameter("minHiredate");
		if(dato==null){
			inputs.put("minHiredate", "");
			
		}
		else{
			dato=request.getParameter("minHiredate");

			inputs.put("minHiredate", dato);
		}
		dato=request.getParameter("maxHiredate");
		if(dato==null){
			inputs.put("maxHiredate", "");
			
		}
		else{
			dato=request.getParameter("maxHiredate");

			inputs.put("maxHiredate", dato);
		}
		String[] departamentosSeleccionados = null;
		
		departamentosSeleccionados = request.getParameterValues("departamentos");
		if(departamentosSeleccionados!=null){
			request.setAttribute("seleccionados", departamentosSeleccionados);
		}else{
			departamentosSeleccionados = new String[0];
			request.setAttribute("seleccionados", departamentosSeleccionados);
		}
		
		
		for(Departamento dep:listaDepartamentos){
			
			departamentosMapa.put(Byte.toString(dep.getDeptno()), dep.getDname());
			
		}
		
		request.setAttribute("empleados", listaEmpleados);
		request.setAttribute("inputs", inputs);
		request.setAttribute("departamentos", departamentosMapa);
		request.getRequestDispatcher("listado.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		doGet(request, response);
		
	}

}
