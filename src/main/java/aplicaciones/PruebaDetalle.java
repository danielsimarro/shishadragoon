package aplicaciones;


import java.util.List;

import controladores.ControladorCompra;
import controladores.ControladorDetalle;
import controladores.ControladorProducto;
import controladores.ControladorUsuario;
import entidades.Compra;
import entidades.Detallepedido;
import entidades.Producto;
import entidades.Usuario;

public class PruebaDetalle {

	public static void main(String[] args) {

		// Cremos el objeto controlador para acceder a los metodos de esta clase
		ControladorDetalle controlDetalle = new ControladorDetalle();
		ControladorUsuario controlaUsur = new ControladorUsuario();
		ControladorProducto controlaPro = new ControladorProducto();
		ControladorCompra controlaCompra = new ControladorCompra();
		// Guardamos en una lista de objetos de tipo Detalle(Mapeador) los datos
		// obtenidos
		// de la tabla detalle de la base de datos
		List<Detallepedido> lista = controlDetalle.findAll();

		// Imprimimos la lista
		System.out.println("\n\nEntidades de la tabla Compra:");
		for (Detallepedido c : lista) {
			System.out.println(c);
		}
		
//		Detallepedido det = controlDetalle.findByPK(23,2);
//		
//		controlDetalle.borrarDetalle(det);

		// Vamos a guardar un objeto detalle segun la pk que le pasemos por parametro
		Detallepedido detallepk = controlDetalle.findByPK(3, 3);

		// Mostramos el objeto
		System.out
				.println("\nEl objeto con el detallePedido con condcompra 3 y codproducto 3" + " es : \n" + detallepk);

		// Vamos a guardar el objeto detalle según la cantidad de articulos que le
		// pasemos por parametros
		Detallepedido detallecantidad = controlDetalle.findByCantidad(8);

		// Mostramos el objeto
		System.out
				.println("\nEl objeto con el detallePedido la cantidad de 8 articulos" + " es : \n" + detallecantidad);

		//VAmos a crear una nueva compra y obtener el usuario con la pk4
		//parea crear un nuevo detalle pedido
		Compra nuevacompra = controlaCompra.findByPK(5);
		Usuario pk4 = controlaUsur.findByPK(1);
		nuevacompra.setUsuario(pk4);
		controlaCompra.createCompra(nuevacompra);

		Producto nuevoProducto = controlaPro.findByPK(2);

		Detallepedido nuevoDetalle = new Detallepedido();

		nuevoDetalle.setCompra(nuevacompra);
		nuevoDetalle.setProducto(nuevoProducto);
		nuevoDetalle.setCantidadarticulos(4);

		controlDetalle.createDetalle(nuevoDetalle);

		// Imprimimos la lista
		System.out.println("\n\nEntidades de la tabla Compra tras añadir nuevo detalle:");
		for (Detallepedido c : lista) {
			System.out.println(c);
		}

		

	}

}
