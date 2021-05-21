package appfinal;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Menus {
	// Metodo que accede a los metodos de la tabla dependiendo de la tabla que haya
	// seleccionado en elegirTabla
	public void tablaElegida() {

		String eleccion;

		boolean repetir;

		do {
			eleccion = elegirTabla();
			repetir = true;
			switch (eleccion) {
			case "Cuenta":

				MetodosCuenta mc = new MetodosCuenta();
				mc.opcionesCuenta();

				break;
			case "Usuario":
				
				MetodosUsuario mu = new MetodosUsuario();
				mu.opcionesUsuario();
				
				break;
			case "Compra":
				break;
			case "Detallepedido":
				break;
			case "Producto":
				break;
			case "Salir":
				repetir = salir();
				break;
			}
		} while (repetir == true);

		CierreAplicacion app = new CierreAplicacion();
		app.attachShutDownHook();
		System.exit(0);

	}

	// Metodo que nos permite elegir una tabla a consultar o modificar
	public String elegirTabla() {

		Icon icono = new ImageIcon(getClass().getResource("../img/tabla.png"));

		String[] opciones = { "Cuenta", "Usuario", "Compra", "Detallepedido", "Producto", "Salir" };

		String opcionElegida = (String) JOptionPane.showInputDialog(null, "Selecciona la tabla a modificar/consultar",
				"Elegir", JOptionPane.QUESTION_MESSAGE, icono, opciones, opciones[0]);

		return opcionElegida;
	}

	// Metodo que finaliza el programa mostrando si desea salir o continuar
	public boolean salir() {
		boolean repetir = true;
		int op = JOptionPane.showConfirmDialog(null, "¿Deseas salir?", "Salida del programa",
				JOptionPane.YES_NO_OPTION);

		// System.out.println("Opcion: " + op); // Depuración
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
			int num = Integer.parseInt(cadena);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// Este metodo permite introducir un valor y comprobar que este valor solo pueda
	// ser un número
	// devolviendo este número
	public int ComprobarNumeroDevolver() {

		Icon icono = new ImageIcon(getClass().getResource("../img/error.png"));

		String name = "";

		boolean esNumero = true;
		do {
			name = JOptionPane.showInputDialog("Escribe la clave a buscar");
			esNumero = validanNumero(name);
			if (!esNumero) {
				JOptionPane.showMessageDialog(null, "Introduzca solo números", "Error", JOptionPane.DEFAULT_OPTION,
						icono);
			}

		} while (!esNumero);

		int numero = Integer.parseInt(name);

		return numero;
	}
}
