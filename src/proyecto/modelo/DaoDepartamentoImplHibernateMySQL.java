package proyecto.modelo;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import proyecto.pojos.Departamento;


public class DaoDepartamentoImplHibernateMySQL implements DaoDepartamento{

	@Override
	public List<Departamento> devuelveTodosDepartamentos() {
		// TODO Auto-generated method stub
		List<Departamento> listaDepartamentos=null;
		
		SessionFactory factoriaSesiones = new Configuration().configure().buildSessionFactory();
		
		
		
		Session sesion = factoriaSesiones.openSession();
		
		
		
		//Solo son necesarios en caso de que se vayan a hacer modificaciones en la base de datos,
			//en caso de consultas no es necesario.
		//sesion.getTransaction().begin();
			
			String consultaHQL="FROM Departamento";
		
			Query consulta = sesion.createQuery(consultaHQL);
		
			listaDepartamentos=consulta.list();
			
		//sesion.getTransaction().commit();
		//sesion.getTransaction().rollback();
		
		sesion.close();
		factoriaSesiones.close();
		
		return listaDepartamentos;
	}

	@Override
	public Departamento devuelveDepartamentoPorId( byte id) {
		// TODO Auto-generated method stub
				Departamento departamento=null;
				
				SessionFactory factoriaSesiones = new Configuration().configure().buildSessionFactory();
				
				Session sesion = factoriaSesiones.openSession();
				
				departamento = (Departamento)sesion.get(Departamento.class, id);
					
				//sesion.getTransaction().commit();
				//sesion.getTransaction().rollback();
				
				factoriaSesiones.close();
				
				return departamento;
	}
	
	

}
