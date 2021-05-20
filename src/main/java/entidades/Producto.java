package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the producto database table.
 * 
 */

//@Entity indica que la clase es una entidad que se va a mapear con la tabla de nuestra base de
//datos, los campos de esta clase seran los mismos que las columnas de la base de datos
@Entity
//@Table, para que pueda encontrar JPA la tabla correctamente. Si no se pone, puede dar error
@Table(name = "producto")
//@NamedQuery,Sirve para identificar el nombre de consultas especificas, en esta caso esta consulta sirve para 
//consultar todos los datos de la tabla
@NamedQueries({
	@NamedQuery(name="Producto.findAll", query="SELECT p FROM Producto p"),
	@NamedQuery(name = "Producto.findNom", query = "SELECT p FROM Producto p WHERE p.nombreproducto = :nombreproducto"),
})
//Esta clase hereda de la clase Entidad que es abstracta
public class Producto extends Entidad implements Serializable {
	private static final long serialVersionUID = 1L;

	//Indica el atributo que va a mapear con clave primaria
	@Id
	// @GeneratedValue, indica que este valor se genera automáticamente cuando la entidad
	// se guarde en la tabla. En este caso IDENTITY se basa en una columna con incremento automático 
	// y permite que la base de datos genere un nuevo valor con cada operación de inserción
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codproducto;

	private String nombreproducto;

	// Relación bidireccional uno a muchos a detallepedido
	// Un producto puede estar en muchos detallespediod muchas veces
	// Este atributo representa la lista de detallespedidos en los que
	// se encuentra este producto
	// mappedBy indica el atributo asociado en la clase detallepedido 
	@OneToMany(mappedBy="producto")
	private List<Detallepedido> detallepedidosProducto;

	public Producto() {
	}

	public int getCodproducto() {
		return this.codproducto;
	}

	public void setCodproducto(int codproducto) {
		this.codproducto = codproducto;
	}

	public String getNombreproducto() {
		return this.nombreproducto;
	}

	public void setNombreproducto(String nombreproducto) {
		this.nombreproducto = nombreproducto;
	}


	public List<Detallepedido> getDetallepedidosProducto() {
		return this.detallepedidosProducto;
	}

	public void setDetallepedidosProducto(List<Detallepedido> detallepedidosProducto) {
		this.detallepedidosProducto = detallepedidosProducto;
	}

	public Detallepedido addDetallepedidosProducto(Detallepedido detallepedidosProducto) {
		getDetallepedidosProducto().add(detallepedidosProducto);
		detallepedidosProducto.setProducto(this);

		return detallepedidosProducto;
	}

	public Detallepedido removeDetallepedidosProducto(Detallepedido detallepedidosProducto) {
		getDetallepedidosProducto().remove(detallepedidosProducto);
		detallepedidosProducto.setProducto(null);

		return detallepedidosProducto;
	}

	@Override
	public String toString() {
		return "Producto [codproducto=" + codproducto + ", nombreproducto=" + nombreproducto +toStringDetalle() ;
	}
	
	private String toStringDetalle() {
		StringBuilder tmp = new StringBuilder();
		for (Detallepedido detalle: detallepedidosProducto) {
			tmp.append(" Id: ").append(detalle.getId());
		}
		return tmp.toString();
	}
	
	

}