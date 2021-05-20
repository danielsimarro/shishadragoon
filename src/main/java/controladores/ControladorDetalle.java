package controladores;

import java.util.List;



import entidades.Detallepedido;

public class ControladorDetalle extends ControladorDeta{

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
