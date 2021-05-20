package entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the detallepedido database table.
 * 
 */
//Esta clase forma la clave primaria de detallepedido que se compone de 
//codcompra y codproducto
//@EmbeddableEste atributo sirve para indicar que esta clase es integrabel en otra entidad
@Embeddable
//Esta clase hereda de la clase Entidad que es abstracta
public class DetallepedidoPK extends Entidad implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	//@column nos permitirá definir 
	//aspectos muy importantes sobre las columnas de la base de datos 
	
	//En esto dos casos insertable:  Le indica a JPA si esa columna debe ser 
	//tomada en cuenta en los inserts, en caso de ser true, el valor será insertado, en 
	//caso contrario el valor será omitido y será colocado el valor default de la columna o null.
	// updatable: Parecido al caso anterior, solo que en este caso la columna 
	//se toma en cuenta para operaciones de Update.
	@Column(insertable=false, updatable=false)
	private int codcompra;

	@Column(insertable=false, updatable=false)
	private int codproducto;

	public DetallepedidoPK() {
	}
	public int getCodcompra() {
		return this.codcompra;
	}
	public void setCodcompra(int codcompra) {
		this.codcompra = codcompra;
	}
	public int getCodproducto() {
		return this.codproducto;
	}
	public void setCodproducto(int codproducto) {
		this.codproducto = codproducto;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DetallepedidoPK)) {
			return false;
		}
		DetallepedidoPK castOther = (DetallepedidoPK)other;
		return 
			(this.codcompra == castOther.codcompra)
			&& (this.codproducto == castOther.codproducto);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codcompra;
		hash = hash * prime + this.codproducto;
		
		return hash;
	}
	@Override
	public String toString() {
		return "DetallepedidoPK [codcompra=" + codcompra + ", codproducto=" + codproducto + "]";
	}
	
	
}