package proyecto.modelo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

	@Override
	public List<Empleado> devuelveEmpleadosParametrizada(Map<String, String> inputs,String[] numDepartamentos) {
		
		SessionFactory factoriaSesiones = new Configuration().configure().buildSessionFactory();
		
		Session sesion = factoriaSesiones.openSession();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		boolean isFirst = true; 
				
		StringBuilder query = new StringBuilder("from Empleado ");
				
		
		if(inputs.get("patronEname")!=null && !inputs.get("patronEname").equals("")){
			if(isFirst){
				query.append(" where ename like '%" + inputs.get("patronEname") + "%'");
			}else{
				query.append(" and ename like '%" + inputs.get("patronEname") + "%'");
			}
			isFirst = false;
		}
	
		if(numDepartamentos!=null && (numDepartamentos.length>0)){
			String resultado="";
			if(isFirst){
				for(int i = 0; i < numDepartamentos.length; i ++) {
					
					if (i == 0) {
						
						resultado=" Where deptno in (" + numDepartamentos [i];
					} 

					else {
						
						 resultado+= "," + numDepartamentos [i];
					}
					resultado+=")";
				}
				query.append(resultado);
			}
				
			else{
				
				for(int i = 0; i < numDepartamentos.length; i ++) {
					
					if (i == 0) {
						
						resultado=" Where deptno in (" + numDepartamentos [i];
					} 

					else {
						
						 resultado+= "," + numDepartamentos [i];
					}
					resultado+=")";
				}
				query.append(resultado);
			}
		
			isFirst = false;	
		}
		
		if(inputs.get("minSal")!=null && !inputs.get("minSal").equals("")){
			if(isFirst){
				query.append(" where sal >= '" + inputs.get("minSal") + "'");
			}else{
				query.append(" and sal >= '" + inputs.get("minSal") + "'");
			}
			isFirst = false;
		}
		
		if(inputs.get("maxSal")!=null && !inputs.get("maxSal").equals("")){
			if(isFirst){
				query.append(" where sal <= '" + inputs.get("maxSal") + "'");
			}else{
				query.append(" and sal <= '" + inputs.get("maxSal") + "'");
			}
			isFirst = false;
		}
		
		if(inputs.get("minHiredate")!=null && !inputs.get("minHiredate").equals("")){
			if(isFirst){
				query.append(" where hiredate >= '" + inputs.get("minHiredate") + "'");
			}else{
				query.append(" and hiredate >= '" + inputs.get("minHiredate") + "'");
			}
			isFirst = false;
		}
		
		if(inputs.get("maxHiredate")!=null && !inputs.get("maxHiredate").equals("")){
			if(isFirst){
				query.append(" where hiredate <= '" + inputs.get("maxHiredate") + "'");
			}else{
				query.append(" and hiredate <= '" + inputs.get("maxHiredate") + "'");
			}
			isFirst = false;
		}
		
		if(inputs.get("maxHiredate")!=null && !inputs.get("maxHiredate").equals("")){
			if(isFirst){
				query.append(" where hiredate <= '" + inputs.get("maxHiredate") + "'");
			}else{
				query.append(" and hiredate <= '" + inputs.get("maxHiredate") + "'");
			}
			isFirst = false;
		}
		
		
		query.append(" order by ename");
		Query result = sesion.createQuery(query.toString());
		List<Empleado> empleados=result.list();
		return empleados;
	}

}
