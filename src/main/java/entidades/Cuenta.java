package entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cuenta database table.
 * 
 */
//@Entity indica que la clase es una entidad que se va a mapear con la tabla de nuestra base de
//datos, los campos de esta clase seran los mismos que las columnas de la base de datos
@Entity
//@Table, para que pueda encontrar JPA la tabla correctamente. Si no se pone, puede dar error
@Table(name = "cuenta")
//@NamedQuery,Sirve para identificar el nombre de consultas especificas, en esta caso esta consulta sirve para 
//consultar todos los datos de la tabla
@NamedQueries({
	@NamedQuery(name="Cuenta.findAll", query="SELECT c FROM Cuenta c"),
	@NamedQuery(name = "Cuenta.findMail", query = "SELECT c FROM Cuenta c WHERE c.correoelectronico = :correoelectronico"),
})
//Esta clase hereda de la clase Entidad que es abstracta
public class Cuenta extends Entidad implements Serializable {
	private static final long serialVersionUID = 1L;

	//@Id Indica el atributo que va a mapear con clave primaria
	@Id
	// @GeneratedValue, indica que este valor se genera automáticamente cuando la entidad
	// se guarde en la tabla. En este caso IDENTITY se basa en una columna con incremento automático 
	// y permite que la base de datos genere un nuevo valor con cada operación de inserción
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codcuenta;

	private String contrasena;

	private String correoelectronico;

	// Asociación bidireccional entre Cuenta y usuario
	// Un cuneta pertenece a un usuario (@OneToOne)
	// Con este anotación y este atributo se puede recuperar el usuario
	// al que pertenece esta cuenta.
	// mappedBy indica el atributo asociado en la clase usuario 
	@OneToOne(mappedBy="cuenta", fetch=FetchType.LAZY)
	private Usuario usuariosCuenta;

	public Cuenta() {
	}

	
	public int getCodcuenta() {
		return this.codcuenta;
	}
	

	public void setCodcuenta(int codcuenta) {
		this.codcuenta = codcuenta;
	}

	public String getContrasena() {
		return this.contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getCorreoelectronico() {
		return this.correoelectronico;
	}

	public void setCorreoelectronico(String correoelectronico) {
		this.correoelectronico = correoelectronico;
	}

	public Usuario getUsuariosCuenta() {
		return this.usuariosCuenta;
	}

	public void setUsuariosCuenta(Usuario usuariosCuenta) {
		this.usuariosCuenta = usuariosCuenta;
	}

	@Override
	public String toString() {
		
		// Si toString de tarjetaBancaria llama a toString de Cliente
		// se genera una excepción StackOverFlow al desbordarse la pila de llamadas
		// ya que las llamadas se encadenan sin fin
				
		// Para evitar llamadas concatenadas entre objetos relacionados voy
		// a usar el atributo nombre del cliente, no el toString completo.
		String nombre = (this.usuariosCuenta != null) ? this.usuariosCuenta.getNombreuser() : "";
		return "Cuenta [codcuenta=" + codcuenta + ", contrasena=" + contrasena + ", correoelectronico="
				+ correoelectronico + ", usuariosCuenta=" + nombre + "]";
	}
	
	

}