package entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the detallepedido database table.
 * 
 */
//@Entity indica que la clase es una entidad que se va a mapear con la tabla de nuestra base de
//datos, los campos de esta clase seran los mismos que las columnas de la base de datos
@Entity
//@Table, para que pueda encontrar JPA la tabla correctamente. Si no se pone, puede dar error
@Table(name = "detallepedido")
//@NamedQuery,Sirve para identificar el nombre de consultas especificas, en esta caso esta consulta sirve para 
//consultar todos los datos de la tabla
@NamedQueries({ @NamedQuery(name = "Detallepedido.findAll", query = "SELECT d FROM Detallepedido d"),
		@NamedQuery(name = "Detallepedido.findCan", query = "SELECT d FROM Detallepedido d WHERE d.cantidadarticulos = :cantidadarticulos"), })
//Esta clase hereda de la clase Entidad que es abstracta
public class Detallepedido extends Entidad implements Serializable {
	private static final long serialVersionUID = 1L;

	// @EmbeddedId Este atributo hace referencia a que el id de esta clase esta
	// formado por los atributos de la clase DetallepedidoPK
	@EmbeddedId
	private DetallepedidoPK id;

	int cantidadarticulos;

	// Relación bidireccional muchos a uno a compra
	// Muchos detallepedidos pueden tener la misma compra
	// Este atributo representa la compra involucrado en este detallepedido
	// La tabla compra es la propietaria de la relación al tener la clave ajena
	// Esto se indica con @JoinColumn y el atributo de la tabla con el que obtener
	// los datos de la tabla detallepedido
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codcompra")
	private Compra compra;

	// Relación bidireccional muchos a uno a producto
	// Muchos detallepedidos pueden tener muchos productos
	// Este atributo representa el producto involucrado en este detallepedido
	// La tabla producto es la propietaria de la relación al tener la clave ajena
	// Esto se indica con @JoinColumn y el atributo de la tabla con el que obtener
	// los datos de la tabla detallepedido
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codproducto")
	private Producto producto;

	public Detallepedido() {
	}

	public DetallepedidoPK getId() {
		return this.id;
	}

	public void setId(DetallepedidoPK id) {
		this.id = id;
	}

	public int getCantidadarticulos() {
		return this.cantidadarticulos;
	}

	public void setCantidadarticulos(int cantidadarticulos) {
		this.cantidadarticulos = cantidadarticulos;
	}

	public Compra getCompra() {
		return this.compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	@Override
	public String toString() {
		return "Detallepedido [ids=" + "(codcompra):" + id.getCodcompra() + ", (codproducto):" + id.getCodproducto()
				+ ", cantidadarticulos=" + cantidadarticulos + ", nombreProducto= " + producto.getNombreproducto();
	}

}