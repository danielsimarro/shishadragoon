package appfinal;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import controladores.ControladorCompra;
import controladores.ControladorUsuario;
import entidades.Compra;
import entidades.Usuario;

public class MetodosCompra {

	// Los atributos de la clase son objetos para acceder a los metodos de otras
	// clases, estos estan inicializados para no tener que inicializarlos cada vez
	// que los vayamos a usar
	private static ControladorUsuario cUsuario = new ControladorUsuario();
	private static Menus menu = new Menus();
	private static MetodosUsuario mUsuario = new MetodosUsuario();
	private static ControladorCompra cCompra = new ControladorCompra();

	// Opciones a elegir de la tabla Compra
	public String menuCompra() {

		Icon icono = new ImageIcon(getClass().getResource("../img/compra.png"));

		String[] opciones = { "Mostrar todos los valores", "Borrar Compra", "Crear Compra", "Modificar Compra",
				"Buscar por precio", "Buscar por clave", "Salir" };

		String opcionElegida = (String) JOptionPane.showInputDialog(null, "¿Que deseas realizar?", "Tabla Compra",
				JOptionPane.QUESTION_MESSAGE, icono, opciones, opciones[0]);

		return opcionElegida;

	}

	// Este metodo contiene un swicth con las posibles elecciones que haya elegido
	// el usuario
	// cuendo el usuario eligue una opciion entra y realiza esa función
	public void opcionesCompra() {

		// Atributos para controlar lo que introduce el usuario
		boolean repetir;
		boolean existePk;
		int numeroIntroudcido;
		String precio;
		// Usuario que se crea nueva cada vez que se inicializa el bucle
		Compra compra;

		do {
			// Inicializamos valores cada vez que se vuelve a ejecutar el bucle
			String opcion = menuCompra();
			repetir = true;
			numeroIntroudcido = -1;
			existePk = false;
			compra = new Compra();
			precio = "";

			switch (opcion) {

			case "Mostrar todos los valores":
				List<Compra> listaCompra = cCompra.findAll();
				System.out.println("---------Los valores de la tabla Compra son:----------");
				listaCompra.forEach(System.out::println);
				break;

			case "Borrar Compra":

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

			case "Crear Compra":
				compra.setFechacompra(convertirFechaDate());

				precio = JOptionPane.showInputDialog("Escribe el precio");
				// Comprobamos que el precio introducido sea correcot
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
				existePk = mUsuario.comprobarPk(numeroIntroudcido);

				// Comprobamos si el usuario que realiza la compra existe
				if (existePk) {
					Usuario usuarioPk = cUsuario.findByPK(numeroIntroudcido);
					compra.setUsuario(usuarioPk);
					cCompra.crearEntidad(compra);
					JOptionPane.showMessageDialog(null, "El usuario se a creado correctamente", "Correcto",
							JOptionPane.DEFAULT_OPTION);
					System.out.println("---------Los valores de la nueva Compra son:----------");
					System.out.println(compra.toString());
				} else {
					JOptionPane.showMessageDialog(null, "La pk no existe", "Error", JOptionPane.DEFAULT_OPTION);
				}

				break;

			case "Modificar Compra":
				numeroIntroudcido = menu.ComprobarNumeroDevolver();
				existePk = comprobarPk(numeroIntroudcido);
				if (existePk) {
					compra = cCompra.findByPK(numeroIntroudcido);
					realizarModificacionCompra(compra);
					JOptionPane.showMessageDialog(null, "La Compra a sido modificada Correctamente", "Correcto",
							JOptionPane.DEFAULT_OPTION);
					System.out.println("---------Los valores de la Compra modificada son:----------");
					System.out.println(compra.toString());
				} else {
					JOptionPane.showMessageDialog(null, "El valor con esta pk no existe", "Error",
							JOptionPane.DEFAULT_OPTION);
				}
				break;

			case "Buscar por precio":
				precio = JOptionPane.showInputDialog("Escribe el precio");
				// Comprobamos que el precio este introducido correctamente
				if (menu.validanDecimal(precio)) {
					double buscaPrecio = Double.parseDouble(precio);
					if (existePrecio(buscaPrecio)) {
						System.out.println("---------La compra con el precio '" + precio + "' es:----------");
						System.out.print(cCompra.findByPrecio(buscaPrecio).toString());
					} else {
						JOptionPane.showMessageDialog(null,
								"El precio introducido no existe o hay varios precios iguales", "Recordar",
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
					System.out.println("---------La Compra con la Pk '" + numeroIntroudcido + "' es:----------");
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

		// Cuando salimos del bucle llamamos de nuevo al menu principal
		menu.tablaElegida();

	}

	// Metodo que comprueba si la pk existe en la tabla Compra
	public boolean comprobarPk(int pk) {

		try {
			cCompra.findByPK(pk);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// Metodo que comprueba si la fecha es adecuada
//	public static boolean fechaAdecuada(String fecha) {
//		SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
//		Date fechaDate = null;
//		try {
//			fechaDate = formato.parse(fecha);
//			return true;
//		} catch (Exception e) {
//			return false;
//
//		}
//
//	}

	// Metodo que introduce un String y devuleve la fecha en formato Date
//	public static Date ParseFecha(String fecha) {
//		SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
//		Date fechaDate = null;
//		try {
//			fechaDate = formato.parse(fecha);
//		} catch (Exception e) {
//			JOptionPane.showMessageDialog(null, "El formato de la fecha no es el adecuado", "Error",
//					JOptionPane.DEFAULT_OPTION);
//
//		}
//		return fechaDate;
//	}

	// Metodos que comprueba si el precio introducido existe en la tabla Compra o si
	// ese precio
	// existe varias veces no devuelve nada. Este es capturado por el try
	public boolean existePrecio(double precio) {

		try {
			cCompra.findByPrecio(precio);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// Metodo que realiza la modificacion de los valores de una Compra, eligiendo
	// los valores que quiere modificar
	public void realizarModificacionCompra(Compra compraModi) {
		String eleccionModificar = valoresModificar();
		String precio = "";
		switch (eleccionModificar) {
		case "Fecha":
			compraModi.setFechacompra(convertirFechaDate());
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
			compraModi.setFechacompra(convertirFechaDate());
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

	// Metodo que permite elegir que queremos modificar de la tabla cuenta
	public String valoresModificar() {

		Icon icono = new ImageIcon(getClass().getResource("../img/editar.png"));

		String[] opciones = { "Fecha", "Precio", "Todo" };

		String opcionElegida = (String) JOptionPane.showInputDialog(null, "¿Que deseas modificar?", "Modificar Compra",
				JOptionPane.QUESTION_MESSAGE, icono, opciones, opciones[0]);

		return opcionElegida;
	}

	// Metodos que devuelve un DATE, donde el usuario introduce los datos  por teclado
	public Date convertirFechaDate() {

		boolean repetirTodo;
		Date fecha = new Date();
		int year;
		int mes;
		do {

			repetirTodo = true;
			boolean repetirYear = true;
			do {
				JOptionPane.showMessageDialog(null, "A continuación introduce el año de compra", "Recordar",
						JOptionPane.DEFAULT_OPTION);
				year = menu.ComprobarNumeroDevolver();
				if (year > 1900) {
					year = year - 1900;
					repetirYear = false;
				}
			} while (repetirYear);

			JOptionPane.showMessageDialog(null, "A continuación introduce el dia de la compra", "Recordar",
					JOptionPane.DEFAULT_OPTION);
			int dia = menu.ComprobarNumeroDevolver();

			boolean repetirMes;
			mes = -1;
			do {
				JOptionPane.showMessageDialog(null, "A continuación introduce el mes de la compra", "Recordar",
						JOptionPane.DEFAULT_OPTION);
				mes = menu.ComprobarNumeroDevolver();
				repetirMes = true;

				mes -= 1;

				if (mes >= 0 && mes <= 11) {
					repetirMes = false;
				}else {
					JOptionPane.showMessageDialog(null, "Mes introducido incorre", "Error",
							JOptionPane.DEFAULT_OPTION);
				}
			} while (repetirMes);

			try {
				fecha = new Date(year, mes, dia);
				repetirTodo = false;
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "La fecha introducida no es correcta", "Recordar",
						JOptionPane.DEFAULT_OPTION);

			}

		} while (repetirTodo);

		return fecha;

	}

}
