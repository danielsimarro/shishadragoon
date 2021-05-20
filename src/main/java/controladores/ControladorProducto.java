package controladores;

import java.util.List;


import entidades.Producto;

public class ControladorProducto extends Controlador {

	// Este método obtiene todos los registros de la tabla. Si no hay registros
	// devuelve una lista vacía
	public List<Producto> findAll() {
		this.em = entityManagerFactory.createEntityManager();
		this.consulta = em.createNamedQuery("Producto.findAll");
		@SuppressWarnings("unchecked")
		List<Producto> listaProducto = (List<Producto>) consulta.getResultList();
		this.em.close();
		return listaProducto;
	}

	// Obtiene una entidad por clave primaria, pk. Si no existe lanza
	// una excepción NoResultException
	public Producto findByPK(int pk) {
		this.em = entityManagerFactory.createEntityManager();
		Producto aux = null;
		// Se crea el objeto Query a partir de una SQL nativa
		this.consulta = em.createNativeQuery("Select * from producto where codproducto = ?", Producto.class);
		this.consulta.setParameter(1, pk);
		aux = (Producto) consulta.getSingleResult();
		this.em.close();
		return aux;

	}

	// Devuelve una compra coincidente con la fecha. Si no existe lanza
	// una excepción NoResultException
	// Si existen varios registros con misma fecha lanza NonUniqueResultException
	public Producto findByNombre(String nombrepro) {
		this.em = entityManagerFactory.createEntityManager();
		this.consulta = em.createNamedQuery("Producto.findNom");
		this.consulta.setParameter("nombreproducto", nombrepro);
		Producto c = (Producto) consulta.getSingleResult();
		this.em.close();
		return c;
	}

}
