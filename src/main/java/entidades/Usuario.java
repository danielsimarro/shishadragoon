package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the usuario database table.
 * 
 */

//@Entity indica que la clase es una entidad que se va a mapear con la tabla de nuestra base de
//datos, los campos de esta clase seran los mismos que las columnas de la base de datos
@Entity
//@Table, para que pueda encontrar JPA la tabla correctamente. Si no se pone, puede dar error
@Table(name = "usuario")
//@NamedQuery,Sirve para identificar el nombre de consultas especificas, en esta caso esta consulta sirve para 
//consultar todos los datos de la tabla
@NamedQueries({
	@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u"),
	@NamedQuery(name = "Usuario.findTel", query = "SELECT u FROM Usuario u WHERE u.telefonouser = :telefonouser"),
})
//Esta clase hereda de la clase Entidad que es abstracta
public class Usuario extends Entidad implements Serializable {
	private static final long serialVersionUID = 1L;

	//@Id Indica el atributo que va a mapear con clave primaria
	@Id
	// @GeneratedValue, indica que este valor se genera automáticamente cuando la entidad
	// se guarde en la tabla. En este caso IDENTITY se basa en una columna con incremento automático 
	// y permite que la base de datos genere un nuevo valor con cada operación de inserción
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codusuario;

	private String apellidouser;

	private String cuentabancaria;

	private String direccionuser;

	private String nombreuser;

	private String telefonouser;

	// Asociación bidireccional entre Usuario y compra
	// Un usuario puede realizar muchas compras
	// Con esta anotación y este atributo se pueden recuperar las compras
	// de este usuario
	// mappedBy indica el atributo asociado en la clase compra
	@OneToMany(mappedBy="usuario")
	private List<Compra> comprasUsuario;

	// Asociación bidireccional entre Usuario y cuenta
	// Un usuario tiene una cuenta (@OneToOne) y una cuenta sólo es de un usuario
	// CascadeType.PERSIST indica que al persistir un usuario persista también la
	// cuenta asociada
	@OneToOne(fetch=FetchType.LAZY)
	// La anotación JoinColumn indica a JPA el atributo de la tabla
	// usuario que debe usar para realizar el JOIN con la tabla de Cuenta
	// Usuario es la entidad propietaria de la relación al tener la clave ajena de
	// Cuenta
	@JoinColumn(name="codcuenta")
	private Cuenta cuenta;

	public Usuario() {
	}

	public int getCodusuario() {
		return this.codusuario;
	}

	public void setCodusuario(int codusuario) {
		this.codusuario = codusuario;
	}

	public String getApellidouser() {
		return this.apellidouser;
	}

	public void setApellidouser(String apellidouser) {
		this.apellidouser = apellidouser;
	}

	public String getCuentabancaria() {
		return this.cuentabancaria;
	}

	public void setCuentabancaria(String cuentabancaria) {
		this.cuentabancaria = cuentabancaria;
	}

	public String getDireccionuser() {
		return this.direccionuser;
	}

	public void setDireccionuser(String direccionuser) {
		this.direccionuser = direccionuser;
	}

	public String getNombreuser() {
		return this.nombreuser;
	}

	public void setNombreuser(String nombreuser) {
		this.nombreuser = nombreuser;
	}

	public String getTelefonouser() {
		return this.telefonouser;
	}

	public void setTelefonouser(String telefonouser) {
		this.telefonouser = telefonouser;
	}

	public List<Compra> getComprasUsuario() {
		return this.comprasUsuario;
	}

	public void setComprasUsuario(List<Compra> comprasUsuario) {
		this.comprasUsuario = comprasUsuario;
	}

	public Compra addComprasUsuario(Compra comprasUsuario) {
		getComprasUsuario().add(comprasUsuario);
		comprasUsuario.setUsuario(this);

		return comprasUsuario;
	}

	public Compra removeComprasUsuario(Compra comprasUsuario) {
		getComprasUsuario().remove(comprasUsuario);
		comprasUsuario.setUsuario(null);

		return comprasUsuario;
	}

	public Cuenta getCuenta() {
		return this.cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	
	@Override
	
	public String toString() {
		return "Usuario [codusuario=" + codusuario + ", apellidouser=" + apellidouser + ", cuentabancaria="
				+ cuentabancaria + ", direccionuser=" + direccionuser + ", nombreuser=" + nombreuser + ", telefonouser="
				+ telefonouser  + ", cuenta=" + cuenta + "]" + "Compras realizadas:" + toStringCompra() ;
	}
	
	
	private String toStringCompra() {
		StringBuilder tmp = new StringBuilder();
		for (Compra comp: comprasUsuario) {
			tmp.append(" codcompra: ").append(comp.getCodcompra());
		}
		return tmp.toString();
	}

}