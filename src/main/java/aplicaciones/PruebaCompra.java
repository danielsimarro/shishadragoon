package aplicaciones;

import java.time.LocalDate;
import java.time.Month;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import controladores.ControladorCompra;
import controladores.ControladorUsuario;
import entidades.Compra;
import entidades.Usuario;

public class PruebaCompra {

	public static void main(String[] args) {
		// Cremos el objeto controlador para acceder a los metodos de esta clase
		ControladorCompra controlaCompra = new ControladorCompra();
		ControladorUsuario controlaUsur = new ControladorUsuario();

		// Guardamos en una lista de objetos de tipo Compra(Mapeador) los datos
		// obtenidos
		// de la tabla compra de la base de datos
		
//		controlaCompra.borrarCompra(controlaCompra.findByPK(24));
		
		List<Compra> lista = controlaCompra.findAll();

		// Imprimimos la lista
		System.out.println("\n\nEntidades de la tabla Compra:");
		for (Compra c : lista) {
			System.out.println(c);
		}
		
//		controlaCompra.borrarEntidad(controlaCompra.findByPK(30));

		// Vamos a guardar en un objeto el objeto que obtenemos pasando como parametro
		// el precio
		Compra compraPrecio = controlaCompra.findByPrecio(98.36);

		// Mostramos el objeto
		System.out.println("\nEl objeto con el precio 98.36 es: \n" + compraPrecio);

		// Vamos a registrar una nueva compra, para ello vamos a buscar el usurai
		// con la pk 4 y lo vamos a guradar

		Usuario pk6 = controlaUsur.findByPK(6);

		// VAmos a crear una nueva compra
		Compra nuevaCompra = new Compra();

		nuevaCompra.setFechacompra(Date.valueOf(LocalDate.of(2020, Month.JUNE, 15)));
		nuevaCompra.setPreciocompra(BigDecimal.valueOf(152.32));
		nuevaCompra.setUsuario(pk6);

		controlaCompra.crearEntidad(nuevaCompra);

		lista = controlaCompra.findAll();
		// Imprimimos la lista
		System.out.println("\n\nEntidades de la tabla Compra tras añadir nueva compra:");
		for (Compra c : lista) {
			System.out.println(c);
		}
		

	}

}
