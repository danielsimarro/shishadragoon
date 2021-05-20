package controladores;

import java.util.List;


import entidades.Usuario;

public class ControladorUsuario extends Controlador{


	// Este método obtiene todos los registros de la tabla. Si no hay registros
	// devuelve una lista vacía
	public List<Usuario> findAll() {
		this.em = entityManagerFactory.createEntityManager();
		this.consulta = em.createNamedQuery("Usuario.findAll");
		@SuppressWarnings("unchecked")
		List<Usuario> listaUsuario = (List<Usuario>) consulta.getResultList();
		this.em.close();
		return listaUsuario;
	}

	

	// Obtiene una entidad por clave primaria, pk. Si no existe lanza
	// una excepción NoResultException
	public Usuario findByPK(int pk) {
		this.em = entityManagerFactory.createEntityManager();
		Usuario aux = null;
		// Se crea el objeto Query a partir de una SQL nativa
		this.consulta = em.createNativeQuery("Select * from usuario where codusuario = ?", Usuario.class);
		this.consulta.setParameter(1, pk);
		aux = (Usuario) consulta.getSingleResult();
		this.em.close();
		return aux;

	}

	// Devuelve una cuenta coincidente con el telefono. Si no existe lanza
	// una excepción NoResultException
	// Si existen varios registros con misma telefono lanza NonUniqueResultException
	public Usuario findByTel(String telefono) {
		this.em = entityManagerFactory.createEntityManager();
		this.consulta = em.createNamedQuery("Usuario.findTel");
		this.consulta.setParameter("telefonouser", telefono);
		Usuario c = (Usuario) consulta.getSingleResult();
		this.em.close();
		return c;
	}
}
