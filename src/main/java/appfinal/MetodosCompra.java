package appfinal;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import controladores.ControladorCompra;
import controladores.ControladorUsuario;
import entidades.Compra;
import entidades.Usuario;

public class MetodosCompra {

	// Los atributos de la clase son objetos para acceder a los metodos de otras
	// clases
	private static ControladorUsuario cUsuario = new ControladorUsuario();
	private static Menus menu = new Menus();
	private static MetodosUsuario mu = new MetodosUsuario();
	private static ControladorCompra cCompra = new ControladorCompra();

	// Opciones a elegir de la tabla Compra
	public String menuCompra() {

		String[] opciones = { "Mostrar todo", "Borrar", "Crear", "Modificar", "Buscar por precio", "Buscar por clave",
				"Salir" };

		String opcionElegida = (String) JOptionPane.showInputDialog(null, "¿Que deseas realizar?", "Elegir",
				JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

		return opcionElegida;

	}

	// Permite entrar a los metodos de la tabla cuenta dependiendo de la eleccion
	// hecha por el usuario
	public void opcionesCompra() {

		boolean repetir;
		boolean existePk;
		int numeroIntroudcido;
		String precio;
		// Usuario que se crea nueva cada vez que se inicializa el bucle
		Compra compra;
		// Valores modificables del usuario
		do {
			String opcion = menuCompra();
			repetir = true;
			numeroIntroudcido = -1;
			existePk = false;
			compra = new Compra();
			precio = "";

			switch (opcion) {
			case "Mostrar todo":
				List<Compra> listaCompra = cCompra.findAll();
				listaCompra.forEach(System.out::println);
				break;
			case "Borrar":

				numeroIntroudcido = menu.ComprobarNumeroDevolver();
				existePk = comprobarPk(numeroIntroudcido);
				if (existePk) {
					cCompra.borrarEntidad(cCompra.findByPK(numeroIntroudcido));
					JOptionPane.showMessageDialog(null, "La Compra se ha borrado con exito", "Correto",
							JOptionPane.DEFAULT_OPTION);
				} else {
					JOptionPane.showMessageDialog(null, "La clave primaria no existe", "Error",
							JOptionPane.DEFAULT_OPTION);
				}

				break;
			case "Crear":
				String fecha = JOptionPane.showInputDialog("Escribe la fecha con formato 'dd/MM/yyyy'","00/00/0000");
				if (fechaAdecuada(fecha)) {
					Date fechaDate = ParseFecha(fecha);
					compra.setFechacompra(fechaDate);
				} else {
					JOptionPane.showMessageDialog(null, "La fecha introducida no es correcta", "Recordar",
							JOptionPane.DEFAULT_OPTION);
					break;
				}

				precio = JOptionPane.showInputDialog("Escribe el precio");
				if (menu.validanDecimal(precio)) {
					compra.setPreciocompra(BigDecimal.valueOf(Double.parseDouble(precio)));
				} else {
					JOptionPane.showMessageDialog(null, "El decimal introducido no es correcto", "Recordar",
							JOptionPane.DEFAULT_OPTION);
					break;
				}

				JOptionPane.showMessageDialog(null, "Introduce el usuario que realiza la compra", "Recordar",
						JOptionPane.DEFAULT_OPTION);
				numeroIntroudcido = menu.ComprobarNumeroDevolver();
				existePk = mu.comprobarPk(numeroIntroudcido);

				if (existePk) {
					Usuario usuarioPk = cUsuario.findByPK(numeroIntroudcido);
					compra.setUsuario(usuarioPk);
					cCompra.crearEntidad(compra);
					JOptionPane.showMessageDialog(null, "El usuario se a creado correctamente", "Correcto",
							JOptionPane.DEFAULT_OPTION);

				} else {
					JOptionPane.showMessageDialog(null, "La pk no existe", "error", JOptionPane.DEFAULT_OPTION);
				}

				break;
			case "Modificar":
				numeroIntroudcido = menu.ComprobarNumeroDevolver();
				existePk = comprobarPk(numeroIntroudcido);
				if (existePk) {
					compra = cCompra.findByPK(numeroIntroudcido);
					realizarModificacionCompra(compra);
				} else {
					JOptionPane.showMessageDialog(null, "El valor con esta pk no existe", "Error",
							JOptionPane.DEFAULT_OPTION);
				}
				break;
			case "Buscar por precio":
				precio = JOptionPane.showInputDialog("Escribe el precio");
				if (menu.validanDecimal(precio)) {
					double buscaPrecio = Double.parseDouble(precio);
					if (existePrecio(buscaPrecio)) {
						System.out.print(cCompra.findByPrecio(buscaPrecio).toString());
					} else {
						JOptionPane.showMessageDialog(null, "El precio introducido no existe", "Recordar",
								JOptionPane.DEFAULT_OPTION);
					}

				} else {
					JOptionPane.showMessageDialog(null, "El decimal introducido no es correcto", "Recordar",
							JOptionPane.DEFAULT_OPTION);
					break;
				}

				break;
			case "Buscar por clave":
				numeroIntroudcido = menu.ComprobarNumeroDevolver();
				existePk = comprobarPk(numeroIntroudcido);
				if (existePk) {
					System.out.println(cCompra.findByPK(numeroIntroudcido).toString());
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
	public boolean comprobarPk(int pk) {

		try {
			cCompra.findByPK(pk);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// Metodo que comprueba si la fecha es adecuada
	public static boolean fechaAdecuada(String fecha) {
		SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
		Date fechaDate = null;
		try {
			fechaDate = formato.parse(fecha);
			return true;
		} catch (Exception e) {
			return false;

		}

	}

	// Metodo que se introduce un fecha y devuleve la fecha
	public static Date ParseFecha(String fecha) {
		SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
		Date fechaDate = null;
		try {
			fechaDate = formato.parse(fecha);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "El fromato de la fecha no es el adecuado", "Error",
					JOptionPane.DEFAULT_OPTION);

		}
		return fechaDate;
	}

	public boolean existePrecio(double precio) {

		try {
			cCompra.findByPrecio(precio);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// Metodo que realiza la modificacion de los valores de usuario
	public void realizarModificacionCompra(Compra compraModi) {
		String eleccionModificar = valoresModificar();
		String fecha="";
		String precio="";
		switch (eleccionModificar) {
		case "Fecha":
			fecha = JOptionPane.showInputDialog("Escribe la fecha,con formato 'dd/MM/yyyy'");
			if (fechaAdecuada(fecha)) {
				Date fechaDate = ParseFecha(fecha);
				compraModi.setFechacompra(fechaDate);
			} else {
				JOptionPane.showMessageDialog(null, "La fecha introducida no es correcta", "Recordar",
						JOptionPane.DEFAULT_OPTION);
			}
			break;
		case "Precio":
			precio = JOptionPane.showInputDialog("Escribe el precio");
			if (menu.validanDecimal(precio)) {
				compraModi.setPreciocompra(BigDecimal.valueOf(Double.parseDouble(precio)));
			} else {
				JOptionPane.showMessageDialog(null, "El decimal introducido no es correcto", "Recordar",
						JOptionPane.DEFAULT_OPTION);
			}
			break;
		case "Todo":
			fecha = JOptionPane.showInputDialog("Escribe la fecha,con formato 'dd/MM/yyyy'");
			if (fechaAdecuada(fecha)) {
				Date fechaDate = ParseFecha(fecha);
				compraModi.setFechacompra(fechaDate);
			} else {
				JOptionPane.showMessageDialog(null, "La fecha introducida no es correcta", "Recordar",
						JOptionPane.DEFAULT_OPTION);
				break;
			}
			precio = JOptionPane.showInputDialog("Escribe el precio");
			if (menu.validanDecimal(precio)) {
				compraModi.setPreciocompra(BigDecimal.valueOf(Double.parseDouble(precio)));
			} else {
				JOptionPane.showMessageDialog(null, "El decimal introducido no es correcto", "Recordar",
						JOptionPane.DEFAULT_OPTION);
				break;
			}

			break;
		}
		cCompra.ModificarEntidad(compraModi);
	}

	public String valoresModificar() {

		String[] opciones = { "Fecha", "Precio", "Todo" };

		String opcionElegida = (String) JOptionPane.showInputDialog(null, "¿Que deseas modificar?", "Elegir",
				JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

		return opcionElegida;
	}
}
