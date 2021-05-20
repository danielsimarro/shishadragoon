package aplicaciones;

import java.util.List;

import controladores.ControladorProducto;
import entidades.Producto;
//Producto
public class PruebaProducto {

	public static void main(String[] args) {

		// Cremos el objeto controlador para acceder a los metodos de esta clase
		ControladorProducto controlaPro = new ControladorProducto();

		// Guardamos en una lista de objetos de tipo cuenta(Mapeador) los datos
		// obtenidos
		// de la tabla Cuenta de la base de datos
		List<Producto> lista = controlaPro.findAll();

		// Imprimimos la lista
		System.out.println("\n\nEntidades de la tabla Cuenta:");
		for (Producto c : lista) {
			System.out.println(c);
		}
		// Vamos a guardar en un objeto el objeto que obtenemos pasando como parametro
		// el nombre
		Producto productoNombre = controlaPro.findByNombre("Manguera Camuflaje");

		// Mostramos el objeto
		System.out.println("\nEl objeto con el nombre Manguera Camuflaje es: \n" + productoNombre);

		// Vamos a guardar en un objeto el objeto que obtenemos pasando como parametro
		// su pk

		Producto productoPk = controlaPro.findByPK(2);

		// Mostramos el objeto
		System.out.println("\nEl objeto con la Pk es: \n" + productoPk);

		// Vamos a crear un nuevo producto
		Producto nuevoProducto = new Producto();

		nuevoProducto.setNombreproducto("Embery Mono");
		
		//Persistimos el nuevo producto
		controlaPro.crearEntidad(nuevoProducto);

		lista = controlaPro.findAll();
		// Imprimimos la lista
		System.out.println("\n\nEntidades de la tabla con nuevo producto:");
		for (Producto c : lista) {
			System.out.println(c);
		}
		
		//Modificamos el nuevo producto
		nuevoProducto.setNombreproducto("Caesar Dorada");

		controlaPro.ModificarEntidad(nuevoProducto);
		
		lista = controlaPro.findAll();
		// Imprimimos la lista
		System.out.println("\n\nEntidades de la tabla con  nuevo producto modificado:");
		for (Producto c : lista) {
			System.out.println(c);
		}
		
		//Borramos el nuevo producto
		controlaPro.borrarEntidad(nuevoProducto);
		
		lista = controlaPro.findAll();
		// Imprimimos la lista
		System.out.println("\n\nEntidades de la tabla con nuevo producto eliminado:");
		for (Producto c : lista) {
			System.out.println(c);
		}
		
	}
}
