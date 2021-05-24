package appfinal;

import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import controladores.ControladorProducto;
import entidades.Producto;

public class MetodosProducto {

	// Los atributos de la clase son objetos para acceder a los metodos de otras
	// clases, estos estan inicializados para no tener que inicializarlos cada vez
	// que los vayamos a usarF
	private static ControladorProducto cProducto = new ControladorProducto();
	private static Menus menu = new Menus();

	// Opciones a elegir de la tabla Producto
	public String menuProducto() {

		Icon icono = new ImageIcon(getClass().getResource("../img/producto.png"));

		String[] opciones = { "Mostrar todos los valores", "Borrar Producto", "Crear Producto", "Modificar Producto",
				"Buscar por nombre", "Buscar por clave", "Salir" };

		String opcionElegida = (String) JOptionPane.showInputDialog(null, "¿Que deseas realizar?", "Tabla Producto",
				JOptionPane.QUESTION_MESSAGE, icono, opciones, opciones[0]);

		return opcionElegida;

	}

	// Este metodo contiene un swicth con las posibles elecciones que haya elegido
	// el usuario
	// cuendo el usuario eligue una opciion entra y realiza esa función
	public void opcionesProducto() {

		// Atributos para controlar lo que introduce el usuario
		boolean repetir;
		boolean existePk;
		int clave1;
		String nombre;
		// Producto que se crea nueva cada vez que se inicializa el bucle
		Producto product;

		do {
			// Inicializamos valores cada vez que se vuelve a ejecutar el bucle
			String opcion = menuProducto();
			repetir = true;
			clave1 = -1;
			existePk = false;
			product = new Producto();
			nombre = "";
			List<Producto> listaProducto = cProducto.findAll();

			switch (opcion) {
			
			case "Mostrar todos los valores":
				listaProducto = cProducto.findAll();
				System.out.println("---------Los valores de la tabla Producto son:----------");
				listaProducto.forEach(System.out::println);
				break;
				
			case "Borrar Producto":

				clave1 = menu.ComprobarNumeroDevolver();
				existePk = comprobarPk(clave1);

				if (existePk) {
					try {
						cProducto.borrarEntidad(cProducto.findByPK(clave1));
						JOptionPane.showMessageDialog(null, "El producto se ha borrado con exito", "Advertencia",
								JOptionPane.DEFAULT_OPTION);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "La clave esta relacionada con alguna compra", "Advertencia",
								JOptionPane.DEFAULT_OPTION);
					}

				} else {
					JOptionPane.showMessageDialog(null, "La clave primaria no existe", "Error",
							JOptionPane.DEFAULT_OPTION);
				}

				break;
				
			case "Crear Producto":
				
				product.setNombreproducto(JOptionPane.showInputDialog("Escribe el nombre del nuevo producto"));
				cProducto.crearEntidad(product);
				JOptionPane.showMessageDialog(null, "El producto a sido creada Correctamente", "Correcto",
						JOptionPane.DEFAULT_OPTION);
				System.out.println("---------Los valores del nuevo producto son:----------");
				System.out.println(product.toString());
				break;
				
			case "Modificar Producto":
				clave1 = menu.ComprobarNumeroDevolver();
				existePk = comprobarPk(clave1);

				if (existePk) {
					product = cProducto.findByPK(clave1);
					product.setNombreproducto(JOptionPane.showInputDialog("Introduce el nuevo nombre del producto"));
					cProducto.ModificarEntidad(product);
					JOptionPane.showMessageDialog(null, "El producto a sido modificada Correctamente", "Correcto",
							JOptionPane.DEFAULT_OPTION);
					System.out.println("---------Los valores del producto modificada son:----------");
					System.out.println(product.toString());
					
				} else {
					JOptionPane.showMessageDialog(null, "La clave primaria no existe", "Error",
							JOptionPane.DEFAULT_OPTION);
				}
				break;
				
			case "Buscar por nombre":
				nombre = JOptionPane.showInputDialog("Introduce el nombre del producto a buscar");
				try {

					System.out.println("---------El producto con el nombre '" + nombre + "' es:----------");
					System.out.println(cProducto.findByNombre(nombre).toString());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "El nombre del producto a buscar no existe", "Consejo",
							JOptionPane.DEFAULT_OPTION);
				}
				
				break;
			case "Buscar por clave":
				
				clave1 = menu.ComprobarNumeroDevolver();
				existePk = comprobarPk(clave1);
				if (existePk) {
					System.out.println("---------La cuenta con la Pk '" + clave1 + "' es:----------");
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

		// Cuando salimos del bucle llamamos de nuevo al menu principal
		menu.tablaElegida();

	}

	// Metodo que comprueba si la pk existe en la tabla producto
	public boolean comprobarPk(int pk1) {

		try {
			cProducto.findByPK(pk1);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
