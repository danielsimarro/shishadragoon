package controladores;


import java.util.List;

import entidades.Compra;

public class ControladorCompra extends Controlador{

	// Este método obtiene todos los registros de la tabla. Si no hay registros
	// devuelve una lista vacía
	public List<Compra> findAll() {
		this.em = entityManagerFactory.createEntityManager();
		this.consulta = em.createNamedQuery("Compra.findAll");
		@SuppressWarnings("unchecked")
		List<Compra> listaCompra = (List<Compra>) consulta.getResultList();
		this.em.close();
		return listaCompra;
	}

	// Obtiene una entidad por clave primaria, pk. Si no existe lanza
	// una excepción NoResultException
	public Compra findByPK(int pk) {
		this.em = entityManagerFactory.createEntityManager();
		Compra aux = null;
		// Se crea el objeto Query a partir de una SQL nativa
		this.consulta = em.createNativeQuery("Select * from compra where codcompra = ?", Compra.class);
		this.consulta.setParameter(1, pk);
		aux = (Compra) consulta.getSingleResult();
		this.em.close();
		return aux;

	}

	// Devuelve una compra coincidente con la fecha. Si no existe lanza
	// una excepción NoResultException
	// Si existen varios registros con misma fecha lanza NonUniqueResultException
	public Compra findByPrecio(double d) {
		this.em = entityManagerFactory.createEntityManager();
		this.consulta = em.createNamedQuery("Compra.findPre");
		this.consulta.setParameter("preciocompra", d);
		Compra c = (Compra) consulta.getSingleResult();
		this.em.close();
		return c;
	}

}
