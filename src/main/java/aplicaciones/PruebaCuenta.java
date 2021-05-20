package aplicaciones;

import java.util.List;

import controladores.ControladorCuenta;
import entidades.Cuenta;



public class PruebaCuenta {

public static void main(String[] args) {
		
		//Cremos el objeto controlador para acceder a los metodos de esta clase
		ControladorCuenta controlaCuenta = new ControladorCuenta();
		
		//Guardamos en una lista de objetos de tipo cuenta(Mapeador) los datos obtenidos
		//de la tabla Cuenta de la base de datos
		List<Cuenta> lista = controlaCuenta.findAll();
		
//		controlaCuenta.borrarCuenta(controlaCuenta.findByPK(22));
		
		//Imprimimos la lista 
		System.out.println("\n\nEntidades de la tabla Cuenta:");
		for (Cuenta c : lista) {
			System.out.println(c);
		}

		
		//Vamos a guardar en un objeto el objeto que obtenemos pasando como parametro
		//el correo electronico
		Cuenta cuentaMail = controlaCuenta.findByMail("liuis@gmail.com");
				
		//Mostramos el objeto 
		System.out.println("\nEl objeto con el correo liuis@gmail.com es: \n" + cuentaMail);
		
		//Creamos una nueva cuenta 
		Cuenta cuentaNueva = new Cuenta();
				
		cuentaNueva.setContrasena("Carmelo");
		cuentaNueva.setCorreoelectronico("carmelito@hotmail.es");
				
		//Añadimo la nueva cuenta
		controlaCuenta.createCuenta(cuentaNueva);
				
		lista = controlaCuenta.findAll();
		//Mostramos de nuevo la lista
		System.out.println("\nEntidades de la tabla con la nueva cuenta:");
		for (Cuenta c : lista) {
			System.out.println(c);
		}
				
		
		lista = controlaCuenta.findAll();
		//Mostramos de nuevo la lista
		System.out.println("\nEntidades de la tabla con la cuenta nueva borrada:");
		for (Cuenta c : lista) {
			System.out.println(c);
		}
		
		//Vamos a almacenar en un objeto cuenta un objeto que tenga la pk que le pasemos por parametros
		Cuenta cuentapk = controlaCuenta.findByPK(3);
						
		//Mostramos el objeto
		System.out.println("\nLa cuenta con la pk 3 es: \n" + cuentapk);
		
		//Modificar los datos de una cuenta
		
		cuentaNueva.setContrasena("peralte");
		cuentaNueva.setCorreoelectronico("telo@hotmail.es");
				
		controlaCuenta.modifyCuenta(cuentaNueva);
				
		System.out.println("\nImprimimos el usuario modificado: \n" + cuentaNueva);
		
		
	}
	
}
