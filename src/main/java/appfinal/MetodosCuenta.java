package appfinal;

import java.util.List;

import javax.swing.JOptionPane;

import controladores.ControladorCuenta;
import entidades.Cuenta;

public class MetodosCuenta {

	// Los atributos de la clase son objetos para acceder a los metodos de otras
	// clases
	private static ControladorCuenta cc = new ControladorCuenta();
	private static Menus menu = new Menus();

	// Opciones a elegir de la tabla cuenta
	public String menuCuenta() {

		String[] opciones = { "Mostrar todo", "Borrar", "Crear", "Modificar", "Buscar por Mail", "Buscar por clave",
				"Salir" };

		String opcionElegida = (String) JOptionPane.showInputDialog(null, "¿Que deseas realizar?", "Elegir",
				JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

		return opcionElegida;
	}

	// Permite entrar a los metodos de la tabla cuenta dependiendo de la eleccion
	// hecha por el usuario
	public void opcionesCuenta() {

		boolean repetir;
		boolean existePk;
		int numeroIntroudcido;
		String contra;
		String correo;
		Cuenta cuentaCambios;

		do {
			String op = menuCuenta();
			numeroIntroudcido = -1;
			repetir = true;
			existePk = false;
			contra = "";
			correo = "";
			cuentaCambios = new Cuenta();

			switch (op) {
			case "Mostrar todo":
				List<Cuenta> listaCuenta = cc.findAll();
				listaCuenta.forEach(System.out::println);
				break;
			case "Borrar":
				numeroIntroudcido = menu.ComprobarNumeroDevolver();
				existePk = comprobarPk(numeroIntroudcido);
				if (existePk) {
					
					cuentaCambios = cc.findByPK(numeroIntroudcido);
					if (cuentaCambios.getUsuariosCuenta() == null) {
						cc.borrarEntidad(cuentaCambios);
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
			case "Crear":
				contra = JOptionPane.showInputDialog("Escribe la contraseña");
				correo = JOptionPane.showInputDialog("Escribe el correo");

				cuentaCambios.setContrasena(contra);
				cuentaCambios.setCorreoelectronico(correo);
				cc.crearEntidad(cuentaCambios);
				break;
			case "Modificar":
				numeroIntroudcido = menu.ComprobarNumeroDevolver();
				existePk = comprobarPk(numeroIntroudcido);
				if (existePk) {
					cuentaCambios = cc.findByPK(numeroIntroudcido);
					String eleccionModificar = valoresModificar();

					switch (eleccionModificar) {
					case "Email":
						correo = JOptionPane.showInputDialog("Escribe el correo");
						cuentaCambios.setCorreoelectronico(correo);
						break;
					case "Contraseña":
						contra = JOptionPane.showInputDialog("Escribe la contraseña");
						cuentaCambios.setContrasena(contra);
						break;
					case "Todo":
						contra = JOptionPane.showInputDialog("Escribe la contraseña");
						correo = JOptionPane.showInputDialog("Escribe el correo");

						cuentaCambios.setContrasena(contra);
						cuentaCambios.setCorreoelectronico(correo);

						break;
					}
					cc.ModificarEntidad(cuentaCambios);
				} else {
					JOptionPane.showMessageDialog(null, "El valor con esta pk no existe", "Error",
							JOptionPane.DEFAULT_OPTION);
				}
			case "Buscar por Mail":
				String introduceMail = JOptionPane.showInputDialog("Escribe el mail a buscar");
				try {
					cuentaCambios = cc.findByMail(introduceMail);
					System.out.println(cuentaCambios.toString());
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
			cc.findByPK(pk);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// Metodo que permite elegir que queremos modificar
	public String valoresModificar() {

		String[] opciones = { "Email", "Contraseña", "Todo" };

		String opcionElegida = (String) JOptionPane.showInputDialog(null, "¿Que deseas modificar?", "Elegir",
				JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

		return opcionElegida;
	}


}
