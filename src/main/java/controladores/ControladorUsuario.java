package controladores;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entidades.Usuario;

public class ControladorUsuario {

	// Factoria para obtener objetos EntityManager
	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("shishadragoon");
	private EntityManager em;
	private Query consulta;

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

	// Borra un registro de la tabla, aquel que tenga la misma pk que C
	// Si no existe esa pk no hace nada
	public void borrarUsuario(Usuario c) {
		this.em = entityManagerFactory.createEntityManager();
		Usuario aux = null;
		this.em.getTransaction().begin();
		// Si v no es un objeto gestionado por el contexto de persistencia
		if (!this.em.contains(c)) {
			// Carga v en el contexto de persistencia y se guarda en aux
			aux = this.em.merge(c);
		}
		// Ahora se puede borrar usando aux, porque es una entidad gestionada por la
		// caché
		this.em.remove(aux);
		// Se vuelca la información del contexto (caché intermedia) en la base de datos
		this.em.getTransaction().commit();
		// Cierra el entityManager
		this.em.close();
	}

	// Modifica un registro de la tabla, en concreto aquel que tenga la misma pk que
	// c
	// Si esa pk no existe, se inserta un nuevo registro con los valores de c
	public void modifyUsuario(Usuario c) {
		this.em = entityManagerFactory.createEntityManager();
		// En este caso es necesario iniciar una transacción en la base de datos
		// porque vamos a persistir información en la misma
		this.em.getTransaction().begin();
		// merge(Objeto) - Si una entidad con el mismo identificador que v existe en el
		// contexto de persistencia (caché), se actualizan sus atributos y se devuelve
		// como entidad gestionada
		// Si el objeto v no existe en la base de datos, se comporta como persist() y la
		// entidad gestionada es la devuelta por merge(), por lo que c es una entidad
		// desconectada
		this.em.merge(c);
		this.em.getTransaction().commit();
		this.em.close();

	}

	// Inserta un objeto en la tabla Usuario. Si ya existe lanza una excepcion
	// EntityExistsException
	public void createUsuario(Usuario c) {
		this.em = entityManagerFactory.createEntityManager();
		// En este caso es necesario iniciar una transacción en la base de datos
		// porque vamos a persistir información en la misma
		this.em.getTransaction().begin();
		// Se guarda el objeto en el contexto de persistencia (caché intermedia)
		// c es una entidad conectada
		this.em.persist(c);
		// Se vuelca la información del contexto (caché intermedia) en la base de datos
		this.em.getTransaction().commit();
		// Cierra el entityManager
		this.em.close();
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
