package aplicaciones;

import java.util.List;

import javax.swing.JOptionPane;

import appfinal.Menus;
import controladores.ControladorProducto;
import entidades.Producto;

public class MetodosProducto {
	
	private static ControladorProducto cProducto = new ControladorProducto();
	private static Menus menu = new Menus();
	
	
	// Opciones a elegir de la tabla Compra
		public String menuProducto() {

			String[] opciones = { "Mostrar todo", "Borrar", "Crear", "Modificar", "Buscar por nombre", "Buscar por clave",
					"Salir" };

			String opcionElegida = (String) JOptionPane.showInputDialog(null, "¿Que deseas realizar?", "Elegir",
					JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

			return opcionElegida;

		}

		public void opcionesProducto() {

			boolean repetir;
			boolean existePk;
			int clave1;
			String nombre;
			// Usuario que se crea nueva cada vez que se inicializa el bucle
			Producto product;
			// Valores modificables del usuario
			do {
				String opcion = menuProducto();
				repetir = true;
				clave1 = -1;
				existePk = false;
				product = new Producto();
				nombre = "";
				List<Producto> listaProducto= cProducto.findAll();

				switch (opcion) {
				case "Mostrar todo":
					listaProducto = cProducto.findAll();
					listaProducto.forEach(System.out::println);
					break;
				case "Borrar":

					clave1 = menu.ComprobarNumeroDevolver();
					existePk = comprobarPk(clave1);
					
					if (existePk) {
						try {
							cProducto.borrarEntidad(cProducto.findByPK(clave1));
							JOptionPane.showMessageDialog(null, "El producto se ha borrado con exito", "Advertencia",
									JOptionPane.DEFAULT_OPTION);
						}catch(Exception e){
							JOptionPane.showMessageDialog(null, "La clave esta relcionada con alguna compra", "Advertencia",
									JOptionPane.DEFAULT_OPTION);
						}
						
					} else {
						JOptionPane.showMessageDialog(null, "La clave primaria no existe", "Error",
								JOptionPane.DEFAULT_OPTION);
					}

					break;
				case "Crear":
					product.setNombreproducto(JOptionPane.showInputDialog("Escribe el nombre del nuevo producto"));
					cProducto.crearEntidad(product);
					break;
				case "Modificar":
					clave1 = menu.ComprobarNumeroDevolver();
					existePk = comprobarPk(clave1);
					
					if (existePk) {
						product = cProducto.findByPK(clave1);
						product.setNombreproducto(JOptionPane.showInputDialog("Introduce el nuevo nombre del producto"));
						cProducto.ModificarEntidad(product);
						
					} else {
						JOptionPane.showMessageDialog(null, "La clave primaria no existe", "Error",
								JOptionPane.DEFAULT_OPTION);
					}
					break;
				case "Buscar por nombre":
					nombre = JOptionPane.showInputDialog("Introduce el nombre del producto a buscar");
					try {
						product = cProducto.findByNombre(nombre);
						System.out.println(product.toString());
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null,
								"El nombre del producto a buscar no existe", "Consejo",
								JOptionPane.DEFAULT_OPTION);
					}
					break;
				case "Buscar por clave":
					clave1 = menu.ComprobarNumeroDevolver();
					existePk = comprobarPk(clave1);
					if (existePk) {
						System.out.println(cProducto.findByPK(clave1).toString());
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
		public boolean comprobarPk(int pk1) {

			try {
				cProducto.findByPK(pk1);
				return true;
			} catch (Exception e) {
				return false;
			}
		}
}
