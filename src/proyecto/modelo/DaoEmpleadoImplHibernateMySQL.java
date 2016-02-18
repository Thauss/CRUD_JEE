package proyecto.modelo;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import proyecto.pojos.Departamento;
import proyecto.pojos.Empleado;


public class DaoEmpleadoImplHibernateMySQL implements DaoEmpleado {

	@Override
	public List<Empleado> devuelveTodosEmpleados() {
		List<Empleado> listaEmpleados=null;
		
		SessionFactory factoriaSesiones = new Configuration().configure().buildSessionFactory();
		
		
		
		Session sesion = factoriaSesiones.openSession();
		
		
		
		//Solo son necesarios en caso de que se vayan a hacer modificaciones en la base de datos,
			//en caso de consultas no es necesario.
		//sesion.getTransaction().begin();
			
			String consultaHQL="FROM Empleado";
		
			Query consulta = sesion.createQuery(consultaHQL);
		
			listaEmpleados=consulta.list();
			
		//sesion.getTransaction().commit();
		//sesion.getTransaction().rollback();
		
		sesion.close();
		factoriaSesiones.close();
		
		return listaEmpleados;
	}

	@Override
	public Empleado devuelveEmpleadoPorId(short empno) {
		
		Empleado empleado=null;
		
		SessionFactory factoriaSesiones = new Configuration().configure().buildSessionFactory();
		
		Session sesion = factoriaSesiones.openSession();
		
		empleado = (Empleado)sesion.get(Empleado.class, empno);
			
		//sesion.getTransaction().commit();
		//sesion.getTransaction().rollback();
		
		factoriaSesiones.close();
		
		return empleado;
		
	}

	@Override
	public void insertarEmpleado(Map datos) {
		
		Empleado empleado=null;
		
		SessionFactory factoriaSesiones = new Configuration().configure().buildSessionFactory();
		
		Session sesion = factoriaSesiones.openSession();
		
		sesion.beginTransaction();
			
		Short empno = Short.parseShort((String) datos.get("empno"));
		String ename = (String) datos.get("ename");
		Empleado emp = new Empleado();
		emp.setEmpno(empno);
		emp.setEname(ename);
		emp.setComm(null);
		emp.setDept(null);
		emp.setEmpleadoJefe(null);
		emp.setHiredate(null);
		emp.setJob(null);
		emp.setSal(null);
		emp.setSubordinados(null);
		
		
		
		sesion.save(emp);
		
		
		//salaries.add(new Salary(salaryId, emp, 10000));
		
		sesion.getTransaction().commit();
		
		
		factoriaSesiones.close();
		
	
	}

	@Override
	public void borrarEmpleado(String empno) {
		SessionFactory factoriaSesiones = new Configuration().configure().buildSessionFactory();
		
		Session sesion = factoriaSesiones.openSession();
		
		sesion.beginTransaction();
		Query q = sesion.createQuery("delete Empleado where id = "+empno);
		q.executeUpdate();
		sesion.getTransaction().commit();
		
		
		factoriaSesiones.close();
	}

}
