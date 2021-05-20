package controladores;

import java.util.List;



import entidades.Cuenta;


public class ControladorCuenta extends Controlador{
			
			// Este método obtiene todos los registros de la tabla. Si no hay registros
			// devuelve una lista vacía
			public List<Cuenta> findAll() {
				this.em = entityManagerFactory.createEntityManager();
				this.consulta = em.createNamedQuery("Cuenta.findAll");
				@SuppressWarnings("unchecked")
				List<Cuenta> listaCuenta = (List<Cuenta>) consulta.getResultList();
				this.em.close();
				return listaCuenta;
			}
			
			// Obtiene una entidad por clave primaria, pk. Si no existe lanza 
			// una excepción NoResultException
			public Cuenta findByPK(int pk) {
				this.em = entityManagerFactory.createEntityManager();
				Cuenta aux = null;
				// Se crea el objeto Query a partir de una SQL nativa
				this.consulta = em.createNativeQuery("Select * from cuenta where codcuenta = ?", Cuenta.class);
				this.consulta.setParameter(1, pk);
				aux = (Cuenta) consulta.getSingleResult();
				this.em.close();
				return aux;

			}
			
			// Devuelve una cuenta coincidente con el mail. Si no existe lanza 
			// una excepción NoResultException
			// Si existen varios registros con misma mail lanza NonUniqueResultException
			public Cuenta findByMail(String mail) {
				this.em = entityManagerFactory.createEntityManager();
				this.consulta = em.createNamedQuery("Cuenta.findMail");
				this.consulta.setParameter("correoelectronico", mail);
				Cuenta c = (Cuenta) consulta.getSingleResult();
				this.em.close();
				return c;
			}
			
}
