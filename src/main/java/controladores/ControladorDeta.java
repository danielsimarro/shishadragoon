package controladores;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entidades.Entidad;

//He creado este controlador ya que el metodo findByPK en esta clase se realiza pasandoles dos int por parametros

public abstract class ControladorDeta {

	//Estos son lo atributos de la clase el EntityManagerFactory lo hemos inicializado ya que todas las clases
		//usan el mismo a ser la misma base de datos
		protected static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("shishadragoon");
		protected EntityManager em;
		protected Query consulta;
		
		//Metodo que crea una nueva entidad pasada por parametro
		public void crearEntidad(Entidad e) {
			this.em = entityManagerFactory.createEntityManager();
			// En este caso es necesario iniciar una transacción en la base de datos
			// porque vamos a persistir información en la misma
			this.em.getTransaction().begin();
			// Se guarda el objeto en el contexto de persistencia (caché intermedia)
			// c es una entidad conectada
			this.em.persist(e);
			// Se vuelca la información del contexto (caché intermedia) en la base de datos
			this.em.getTransaction().commit();
			// Cierra el entityManager
			this.em.close();
		}
		
		//Metodo que modifica una entidad pasada por parametros
		public  void ModificarEntidad(Entidad e) {
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
			this.em.merge(e);
			this.em.getTransaction().commit();
			this.em.close();
		}
		
		//Metodo que borra una entidad pasandosela por parametros
		public void borrarEntidad(Entidad e) {
			this.em = entityManagerFactory.createEntityManager();
			Entidad aux = null;
			this.em.getTransaction().begin();
			// Si v no es un objeto gestionado por el contexto de persistencia
			if (!this.em.contains(e)) {
				// Carga v en el contexto de persistencia y se guarda en aux
				aux = this.em.merge(e);
			}
			// Ahora se puede borrar usando aux, porque es una entidad gestionada por la
			// caché
			this.em.remove(aux);
			// Se vuelca la información del contexto (caché intermedia) en la base de datos
			this.em.getTransaction().commit();
			// Cierra el entityManager
			this.em.close();
		}
		//Metodo abstracto para buscar la clave primaria que se implementara en cada subclase
		public abstract Entidad findByPK(int e, int a);
}
