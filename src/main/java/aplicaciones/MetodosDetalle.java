package aplicaciones;

import java.util.List;

import javax.swing.JOptionPane;

import appfinal.Menus;
import appfinal.MetodosCompra;

import controladores.ControladorCompra;
import controladores.ControladorDetalle;
import controladores.ControladorProducto;

import entidades.Detallepedido;

public class MetodosDetalle {

	// Los atributos de la clase son objetos para acceder a los metodos de otras
	// clases

	private static Menus menu = new Menus();
	private static ControladorCompra cCompra = new ControladorCompra();
	private static ControladorDetalle cDetalle = new ControladorDetalle();
	private static MetodosCompra mCompra = new MetodosCompra();
	private static MetodosProducto mProducto = new MetodosProducto();
	private static ControladorProducto cProducto = new ControladorProducto();

	// Opciones a elegir de la tabla Compra
	public String menuDetalle() {

		String[] opciones = { "Mostrar todo", "Borrar", "Crear", "Modificar", "Buscar por cantidad", "Buscar por clave",
				"Salir" };

		String opcionElegida = (String) JOptionPane.showInputDialog(null, "¿Que deseas realizar?", "Elegir",
				JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

		return opcionElegida;

	}

	public void opcionesDetalle() {

		boolean repetir;
		boolean existePk;
		int clave1;
		int clave2;
		int cantidad;
		// Usuario que se crea nueva cada vez que se inicializa el bucle
		Detallepedido detalle;
		// Valores modificables del usuario
		do {
			String opcion = menuDetalle();
			repetir = true;
			clave1 = -1;
			clave2 = -1;
			existePk = false;
			detalle = new Detallepedido();
			cantidad = -1;
			List<Detallepedido> listaDetalle = cDetalle.findAll();

			switch (opcion) {
			case "Mostrar todo":
				listaDetalle = cDetalle.findAll();
				listaDetalle.forEach(System.out::println);
				break;
			case "Borrar":

				clave1 = menu.ComprobarNumeroDevolver();
				clave2 = menu.ComprobarNumeroDevolver();
				existePk = comprobarPk(clave1, clave2);
				if (existePk) {
					cDetalle.borrarEntidad(cDetalle.findByPK(clave1, clave2));
					JOptionPane.showMessageDialog(null, "La relación entre producto y compra se ha borrado con exito",
							"Correto", JOptionPane.DEFAULT_OPTION);
				} else {
					JOptionPane.showMessageDialog(null, "La clave primaria no existe", "Error",
							JOptionPane.DEFAULT_OPTION);
				}

				break;
			case "Crear":
				JOptionPane.showMessageDialog(null, "A continuación escribe la clave de la compra a relacionar",
						"Consejo", JOptionPane.DEFAULT_OPTION);
				clave1 = menu.ComprobarNumeroDevolver();
				JOptionPane.showMessageDialog(null, "A continuación escribe la clave del producto  a relacionar",
						"Consejo", JOptionPane.DEFAULT_OPTION);
				clave2 = menu.ComprobarNumeroDevolver();
				boolean existeCompra = mCompra.comprobarPk(clave1);
				boolean existeProducto = mProducto.comprobarPk(clave2);
				if (existeCompra && existeProducto) {
					detalle.setCompra(cCompra.findByPK(clave1));
					detalle.setProducto(cProducto.findByPK(clave2));
					JOptionPane.showMessageDialog(null, "A continuación introduce la cantidad a comprar", "Consejo",
							JOptionPane.DEFAULT_OPTION);
					cantidad = menu.ComprobarNumeroDevolver();
					detalle.setCantidadarticulos(cantidad);

					cDetalle.crearEntidad(detalle);
					JOptionPane.showMessageDialog(null, "La relación se relizo con exsito", "Correto",
							JOptionPane.DEFAULT_OPTION);
				} else {
					JOptionPane.showMessageDialog(null, "Alguna clave primaria no existe", "Error",
							JOptionPane.DEFAULT_OPTION);
				}
				break;
			case "Modificar":
				JOptionPane.showMessageDialog(null, "A continuación escribe la clave de la compra",
						"Consejo", JOptionPane.DEFAULT_OPTION);
				clave1 = menu.ComprobarNumeroDevolver();
				JOptionPane.showMessageDialog(null, "A continuación escribe la clave del producto",
						"Consejo", JOptionPane.DEFAULT_OPTION);
				clave2 = menu.ComprobarNumeroDevolver();
				existePk = comprobarPk(clave1, clave2);
				if (existePk) {
					detalle = cDetalle.findByPK(clave1, clave2);
					JOptionPane.showMessageDialog(null, "A continuación introduce la cantidad a comprar", "Consejo",
							JOptionPane.DEFAULT_OPTION);
					cantidad = menu.ComprobarNumeroDevolver();
					detalle.setCantidadarticulos(cantidad);

					cDetalle.ModificarEntidad(detalle);
				} else {
					JOptionPane.showMessageDialog(null, "La clave primaria no existe", "Error",
							JOptionPane.DEFAULT_OPTION);
				}
				break;
			case "Buscar por cantidad":
				JOptionPane.showMessageDialog(null, "A continuación introduce la cantidad a comprar", "Consejo",
						JOptionPane.DEFAULT_OPTION);
				cantidad = menu.ComprobarNumeroDevolver();
				try {
					detalle = cDetalle.findByCantidad(cantidad);
					System.out.println(detalle.toString());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,
							"La cantidad de articulos no esta registrada o hay varias cantidaes similares", "Consejo",
							JOptionPane.DEFAULT_OPTION);
				}
				break;
			case "Buscar por clave":
				clave1 = menu.ComprobarNumeroDevolver();
				clave2 = menu.ComprobarNumeroDevolver();
				existePk = comprobarPk(clave1, clave2);
				if (existePk) {
					System.out.println(cDetalle.findByPK(clave1, clave2).toString());
				} else {
					JOptionPane.showMessageDialog(null, "La clave primaria no existe", "Error",
							JOptionPane.DEFAULT_OPTION);
				}
				break;
			case "Salir":
				repetir = false;
				break;
			}

		} while (repetir == true);

		menu.tablaElegida();

	}

	// Metodo que comprueba si la pk existe
	public boolean comprobarPk(int pk1, int pk2) {

		try {
			cDetalle.findByPK(pk1, pk2);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
