package aplicaciones;

import java.util.List;

import controladores.ControladorCuenta;
import controladores.ControladorUsuario;
import entidades.Cuenta;
import entidades.Usuario;

public class PruebaUsuario {

	public static void main(String[] args) {

		// Cremos el objeto controlador para acceder a los metodos de esta clase
		ControladorUsuario controlaUsur = new ControladorUsuario();
		ControladorCuenta controlaCuenta = new ControladorCuenta();
		
		
//		controlaUsur.borrarUsuario(controlaUsur.findByPK(14));
		
		// Guardamos en una lista de objetos de tipo Usuario(Mapeador) los datos
		// obtenidos
		// de la tabla Usuario de la base de datos
		List<Usuario> lista = controlaUsur.findAll();

		// Imprimimos la lista
		System.out.println("\n\nEntidades de la tabla Usuario:");
		for (Usuario c : lista) {
			System.out.println(c);
		}

		// Vamos a guardar en un objeto el objeto que obtenemos pasando como parametro
		// el telefono
		Usuario usuarioTel = controlaUsur.findByTel("695423365");

		// Mostramos el objeto
		System.out.println("\nEl objeto con el telefono 695423365 es: \n" + usuarioTel);

		// Creamos un nuevo usuario y cuenta
		
		Cuenta CuentaPk = controlaCuenta.findByPK(25);
		
		//Creamos nuevo usuario
		Usuario usuarioNuevo = new Usuario();

		usuarioNuevo.setNombreuser("juana");
		usuarioNuevo.setApellidouser("Haro");
		usuarioNuevo.setCuentabancaria("568784584587");
		usuarioNuevo.setTelefonouser("63547888");
		
		usuarioNuevo.setCuenta(CuentaPk);
		
		controlaUsur.crearEntidad(usuarioNuevo);
		
		lista = controlaUsur.findAll();
		// Mostramos de nuevo la lista
		System.out.println("\nEntidades de la tabla con el nuevo Usuario:");
		for (Usuario c : lista) {
			System.out.println(c);
		}
		
		
		
		
		

	}

}
