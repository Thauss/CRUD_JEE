package proyecto.modelo;

import java.util.List;

import proyecto.pojos.Departamento;

public interface DaoDepartamento {
	
	public List<Departamento> devuelveTodosDepartamentos();
	public Departamento devuelveDepartamentoPorId(byte id);
	
}
