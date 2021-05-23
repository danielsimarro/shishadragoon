package appfinal;

import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import controladores.ControladorCuenta;
import entidades.Cuenta;

public class MetodosCuenta {

	// Los atributos de la clase son objetos para acceder a los metodos de otras
	// clases, estos estan inicializados para no tener que inicializarlos cada vez
	// que los vayamos a usar
	private static ControladorCuenta cCuenta = new ControladorCuenta();
	private static Menus menu = new Menus();

	// Opciones a elegir de la tabla cuenta
	public String menuCuenta() {
		
		Icon icono = new ImageIcon(getClass().getResource("../img/cuenta.png"));

		String[] opciones = { "Mostrar todos los valores", "Borrar Cuenta", "Crear Cuenta", "Modificar Cuenta",
				"Buscar por Mail", "Buscar por clave", "Salir" };

		String opcionElegida = (String) JOptionPane.showInputDialog(null, "¿Que deseas realizar?", "Tabla Cuenta",
				JOptionPane.QUESTION_MESSAGE, icono, opciones, opciones[0]);

		return opcionElegida;
	}

	// Este metodo contiene un swicth con las posibles elecciones que haya elegido
	// el usuario
	// cuendo el usuario eligue una opciion entra y realiza esa función
	public void opcionesCuenta() {

		// Atributos para controlar lo que introduce el usuario
		boolean repetir;
		boolean existePk;
		int numeroIntroudcido;
		// Cuenta que se crea nueva cada vez que se inicializa el bucle
		Cuenta cuenta;

		do {
			// Inicializamos valores cada vez que se vuelve a ejecutar el bucle
			String opcion = menuCuenta();
			numeroIntroudcido = -1;
			repetir = true;
			existePk = false;
			cuenta = new Cuenta();

			switch (opcion) {

			case "Mostrar todos los valores":
				List<Cuenta> listaCuenta = cCuenta.findAll();
				System.out.println("---------Los valores de la tabla Cuenta son:----------");
				listaCuenta.forEach(System.out::println);
				break;

			case "Borrar Cuenta":
				numeroIntroudcido = menu.ComprobarNumeroDevolver();
				existePk = comprobarPk(numeroIntroudcido);
				if (existePk) {

					cuenta = cCuenta.findByPK(numeroIntroudcido);
					
					if (cuenta.getUsuariosCuenta() == null) {
						cCuenta.borrarEntidad(cuenta);
						JOptionPane.showMessageDialog(null, "La cuenta a sido borrada correctamente", "Perfecto",
								JOptionPane.DEFAULT_OPTION);
					} else {
						JOptionPane.showMessageDialog(null,
								"No se puede borrar la cuenta, ya que esta relacionada con un Usuario", "Error",
								JOptionPane.DEFAULT_OPTION);
					}
				} else {
					JOptionPane.showMessageDialog(null, "El valor con esta pk no existe", "Error",
							JOptionPane.DEFAULT_OPTION);
				}
				break;

			case "Crear Cuenta":

				cuenta.setContrasena(JOptionPane.showInputDialog("Escribe la contraseña"));
				cuenta.setCorreoelectronico(JOptionPane.showInputDialog("Escribe el correo"));
				cCuenta.crearEntidad(cuenta);
				JOptionPane.showMessageDialog(null, "La cuenta a sido creada Correctamente", "Correcto",
						JOptionPane.DEFAULT_OPTION);
				System.out.println("---------Los valores de la nueva cuenta son:----------");
				System.out.println(cuenta.toString());
				break;

			case "Modificar Cuenta":
				numeroIntroudcido = menu.ComprobarNumeroDevolver();
				existePk = comprobarPk(numeroIntroudcido);
				if (existePk) {
					cuenta = cCuenta.findByPK(numeroIntroudcido);
					realizarModificacionCuenta(cuenta);
					JOptionPane.showMessageDialog(null, "La cuenta a sido modificada Correctamente", "Correcto",
							JOptionPane.DEFAULT_OPTION);
					System.out.println("---------Los valores de la cuenta modificada son:----------");
					System.out.println(cuenta.toString());
				} else {
					JOptionPane.showMessageDialog(null, "El valor con esta pk no existe", "Error",
							JOptionPane.DEFAULT_OPTION);
				}

				break;

			case "Buscar por Mail":
				String introduceMail = JOptionPane.showInputDialog("Escribe el mail a buscar");
				try {
					System.out.println("---------La cuenta con el mail '" + introduceMail + "' es:----------");
					System.out.println(cCuenta.findByMail(introduceMail).toString());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "El mail introducido no existe", "Error",
							JOptionPane.DEFAULT_OPTION);

				}

				break;

			case "Buscar por clave":
				numeroIntroudcido = menu.ComprobarNumeroDevolver();
				existePk = comprobarPk(numeroIntroudcido);
				if (!existePk) {
					JOptionPane.showMessageDialog(null, "El valor con esta pk no existe", "Error",
							JOptionPane.DEFAULT_OPTION);
				} else {
					System.out.println("---------La cuenta con la Pk '" + numeroIntroudcido + "' es:----------");
					System.out.println(cCuenta.findByPK(numeroIntroudcido));
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

	// Metodo que comprueba si la pk existe en la tabla Cuenta
	public boolean comprobarPk(int pk) {

		try {
			cCuenta.findByPK(pk);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// Metodo que permite elegir que queremos modificar de la tabla cuenta
	public String valoresModificar() {
		
		Icon icono = new ImageIcon(getClass().getResource("../img/editar.png"));

		String[] opciones = { "Email", "Contraseña", "Todo" };

		String opcionElegida = (String) JOptionPane.showInputDialog(null, "¿Que deseas modificar?", "Modificar Cuenta",
				JOptionPane.QUESTION_MESSAGE, icono, opciones, opciones[0]);

		return opcionElegida;
	}

	// Metodo que realiza la modificacion de los valores de una Cuenta, eligiendo
	// los valores que quiere modificar
	public void realizarModificacionCuenta(Cuenta cuenta) {
		String eleccionModificar = valoresModificar();

		switch (eleccionModificar) {
		case "Email":
			cuenta.setCorreoelectronico(JOptionPane.showInputDialog("Escribe el correo"));
			break;
		case "Contraseña":
			cuenta.setContrasena(JOptionPane.showInputDialog("Escribe la contraseña"));
			break;
		case "Todo":
			cuenta.setContrasena(JOptionPane.showInputDialog("Escribe la contraseña"));
			cuenta.setCorreoelectronico(JOptionPane.showInputDialog("Escribe el correo"));

			break;
		}
		cCuenta.ModificarEntidad(cuenta);
	}
}
