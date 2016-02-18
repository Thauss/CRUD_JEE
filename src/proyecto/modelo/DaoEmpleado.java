package proyecto.modelo;

import java.util.List;
import java.util.Map;

import proyecto.pojos.Empleado;

public interface DaoEmpleado {

	
	public List<Empleado> devuelveTodosEmpleados();
	public Empleado devuelveEmpleadoPorId(short empno);
	public void insertarEmpleado(Map datos);
	
	void borrarEmpleado(String empno);
	
}
