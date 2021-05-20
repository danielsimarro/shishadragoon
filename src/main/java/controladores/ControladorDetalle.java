package controladores;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entidades.Detallepedido;

public class ControladorDetalle {

	// Factoria para obtener objetos EntityManager
	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("shishadragoon");
	private EntityManager em;
	private Query consulta;

	// Este método obtiene todos los registros de la tabla. Si no hay registros
	// devuelve una lista vacía
	public List<Detallepedido> findAll() {
		this.em = entityManagerFactory.createEntityManager();
		this.consulta = em.createNamedQuery("Detallepedido.findAll");
		@SuppressWarnings("unchecked")
		List<Detallepedido> listaDetalle = (List<Detallepedido>) consulta.getResultList();
		this.em.close();
		return listaDetalle;
	}

	// Borra un registro de la tabla, aquel que tenga la misma pk que C
	// Si no existe esa pk no hace nada
	public void borrarDetalle(Detallepedido c) {
		this.em = entityManagerFactory.createEntityManager();
		Detallepedido aux = null;
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
	public void modifyDetalle(Detallepedido c) {
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

	// Inserta un objeto en la tabla detalle. Si ya existe lanza una excepcion
	// EntityExistsException
	public void createDetalle(Detallepedido c) {
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
	public Detallepedido findByPK(int pk1, int pk2) {
		this.em = entityManagerFactory.createEntityManager();
		Detallepedido aux = null;
		// Se crea el objeto Query a partir de una SQL nativa
		this.consulta = em.createNativeQuery("Select * from detallepedido where codcompra = ? and codproducto = ?", Detallepedido.class);
		this.consulta.setParameter(1, pk1);
		this.consulta.setParameter(2, pk2);
		aux = (Detallepedido) consulta.getSingleResult();
		this.em.close();
		return aux;

	}

	// Devuelve un detalle coincidente con la cantidad. Si no existe lanza
	// una excepción NoResultException
	// Si existen varios registros con la  misma cantidad lanza NonUniqueResultException
	public Detallepedido findByCantidad(double cantidad) {
		this.em = entityManagerFactory.createEntityManager();
		this.consulta = em.createNamedQuery("Detallepedido.findCan");
		this.consulta.setParameter("cantidadarticulos", cantidad);
		Detallepedido c = (Detallepedido) consulta.getSingleResult();
		this.em.close();
		return c;
	}
}
