package appfinal;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import aplicaciones.MetodosDetalle;
import aplicaciones.MetodosProducto;

public class Menus {
	// Metodo que accede a los metodos de cada tabla dependiendo de la tabla que
	// haya
	// seleccionado en elegirTabla
	public void tablaElegida() {

		String eleccion;
		boolean repetir;

		do {
			eleccion = elegirTabla();
			repetir = true;
			switch (eleccion) {
			// Ceunado el usuario elija la tabla a consultar/modificar, se creara el objeto
			// para acceder a los
			// metodos de la tabla elegida y se accedera al menu principal de cada tabla
			case "Cuenta":

				MetodosCuenta mCuenta = new MetodosCuenta();
				mCuenta.opcionesCuenta();

				break;
			case "Usuario":

				MetodosUsuario mUsuario = new MetodosUsuario();
				mUsuario.opcionesUsuario();

				break;
			case "Compra":

				MetodosCompra mCompra = new MetodosCompra();
				mCompra.opcionesCompra();
				;

				break;

			case "Detallepedido":

				MetodosDetalle mDetalle = new MetodosDetalle();
				mDetalle.opcionesDetalle();

				break;
			case "Producto":

				MetodosProducto mProducto = new MetodosProducto();
				mProducto.opcionesProducto();

				break;
			case "Salir":
				repetir = salir();
				break;
			}
		} while (repetir == true);

		// Esto permite asegurarnos de que cuando el usuario pulse en salir y se salga
		// del menu principal
		// se cierre nuestra aplicación
		CierreAplicacion app = new CierreAplicacion();
		app.attachShutDownHook();
		System.exit(0);

	}

	// Metodo que nos permite elegir una tabla a consultar o modificar
	public String elegirTabla() {

		Icon icono = new ImageIcon(getClass().getResource("../img/tabla.png"));

		String[] opciones = { "Cuenta", "Usuario", "Compra", "Detallepedido", "Producto", "Salir" };

		String opcionElegida = (String) JOptionPane.showInputDialog(null, "Selecciona la tabla a modificar",
				"Elegir tabla", JOptionPane.QUESTION_MESSAGE, icono, opciones, opciones[0]);

		return opcionElegida;
	}

	// Metodo que finaliza el programa mostrando si desea salir o continuar
	public boolean salir() {

		boolean repetir = true;
		int op = JOptionPane.showConfirmDialog(null, "¿Deseas salir?", "Salida del programa",
				JOptionPane.YES_NO_OPTION);

		if (op == JOptionPane.YES_OPTION) {
			// Quiere salir
			JOptionPane.showMessageDialog(null, "Saliendo...."); // Depuración
			repetir = false; // Condición de parada del programa

		}
		return repetir;
	}

	// Este metodo permite verificar si el valor introducido es un número
	public boolean validanNumero(String cadena) {

		try {
			Integer.parseInt(cadena);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// Este metodo permite verificar si el valor introducido es un decimal
	public boolean validanDecimal(String cadena) {

		try {
			 Double.parseDouble(cadena);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// Este metodo permite introducir un valor y comprobar que este valor solo pueda
	// ser un número, devolviendo este número
	public int ComprobarNumeroDevolver() {

		Icon icono = new ImageIcon(getClass().getResource("../img/error.png"));

		String name = "";

		boolean esNumero = true;
		do {
			name = JOptionPane.showInputDialog(null, "Escribe aqui el número (Solo numeros)");
			esNumero = validanNumero(name);
			if (!esNumero) {
				JOptionPane.showMessageDialog(null, "Introduzca solo números", "Error", JOptionPane.DEFAULT_OPTION,
						icono);
			}

		} while (!esNumero);

		int numero = Integer.parseInt(name);

		return numero;
	}

	// Este metodo nos permite llamar a la Introduccion del proyecto
	public void mostraIntro() {

		Icon icono = new ImageIcon(getClass().getResource("../img/logo.png"));

		JOptionPane.showMessageDialog(null, " Bienvenido a ShishaDragoon ", "Bienvenidos", JOptionPane.PLAIN_MESSAGE,
				icono);
	}
}
