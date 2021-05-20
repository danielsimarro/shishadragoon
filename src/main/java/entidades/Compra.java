package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the compra database table.
 * 
 */
//@Entity indica que la clase es una entidad que se va a mapear con la tabla de nuestra base de
//datos, los campos de esta clase seran los mismos que las columnas de la base de datos
@Entity
//@Table, para que pueda encontrar JPA la tabla correctamente. Si no se pone, puede dar error
@Table(name = "compra")
//@NamedQuery,Sirve para identificar el nombre de consultas especificas, en esta caso esta consulta sirve para 
//consultar todos los datos de la tabla
@NamedQueries({
	@NamedQuery(name="Compra.findAll", query="SELECT c FROM Compra c"),
	@NamedQuery(name = "Compra.findPre", query = "SELECT c FROM Compra c WHERE c.preciocompra = :preciocompra"),
})

//Esta clase hereda de la clase Entidad que es abstracta
public class Compra extends Entidad implements Serializable {
	private static final long serialVersionUID = 1L;

	//@Id Indica el atributo que va a mapear con clave primaria
	@Id
	// @GeneratedValue, indica que este valor se genera automáticamente cuando la entidad
	// se guarde en la tabla. En este caso IDENTITY se basa en una columna con incremento automático 
	// y permite que la base de datos genere un nuevo valor con cada operación de inserción
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codcompra;

	// La anotación @Temporal sirve para indicar a JPA el tipo de dato
	// JDBC java.sql (DATE, TIME, TIMESTAMP) al que pasar el atributo
	// "fechacompra",
	// que es de tipo java.util.Date
	@Temporal(TemporalType.DATE)
	private Date fechacompra;

	private BigDecimal preciocompra;

	// Relación bidireccional muchos a un a Usuario
	// Muchas Compras pueden ser del mismo Usuario
	// Este atributo representa el Usuario involucrado en esta compra
	// La tabla compra es la propietaria de la relación al tener la clave ajena
	// Esto se indica con @JoinColumn y el atributo de la tabla con el que obtener
	// los datos de la tabla usuario
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codusuario")
	private Usuario usuario;

	// Asociación bidireccional entre detallepedido y compra
	// Una compra puede pertenecer a  varias detallepedido
	// Este atributo representa la lista de detallepedido en los que
	// se encuentra esta compra
	// mappedBy indica el atributo asociado en la detallepedido  
	@OneToMany(mappedBy="compra")
	private List<Detallepedido> detallepedidosCompras;

	public Compra() {
	}

	public int getCodcompra() {
		return this.codcompra;
	}

	public void setCodcompra(int codcompra) {
		this.codcompra = codcompra;
	}

	public Date getFechacompra() {
		return this.fechacompra;
	}

	public void setFechacompra(Date fechacompra) {
		this.fechacompra = fechacompra;
	}

	public BigDecimal getPreciocompra() {
		return this.preciocompra;
	}

	public void setPreciocompra(BigDecimal preciocompra) {
		this.preciocompra = preciocompra;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Detallepedido> getDetallepedidosCompras() {
		return this.detallepedidosCompras;
	}

	public void setDetallepedidosCompras(List<Detallepedido> detallepedidosCompras) {
		this.detallepedidosCompras = detallepedidosCompras;
	}

	public Detallepedido addDetallepedidosCompra(Detallepedido detallepedidosCompra) {
		getDetallepedidosCompras().add(detallepedidosCompra);
		detallepedidosCompra.setCompra(this);

		return detallepedidosCompra;
	}

	public Detallepedido removeDetallepedidosCompra(Detallepedido detallepedidosCompra) {
		getDetallepedidosCompras().remove(detallepedidosCompra);
		detallepedidosCompra.setCompra(null);

		return detallepedidosCompra;
	}

	@Override
	public String toString() {
		return "Compra [codcompra=" + codcompra + ", fechacompra=" + fechacompra + ", preciocompra=" + preciocompra
				+ ", usuario=" + usuario.getCodusuario() + toStringDetalle() ;
	}
	
	
	private String toStringDetalle() {
		StringBuilder tmp = new StringBuilder();
		for (Detallepedido detalle: detallepedidosCompras) {
			tmp.append(" Id: ").append(detalle.getId());
		}
		return tmp.toString();
	}

}