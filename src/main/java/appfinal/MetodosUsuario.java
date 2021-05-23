package appfinal;

import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import controladores.ControladorCuenta;
import controladores.ControladorUsuario;
import entidades.Compra;
import entidades.Cuenta;
import entidades.Usuario;

public class MetodosUsuario {

	// Los atributos de la clase son objetos para acceder a los metodos de otras
	// clases, estos estan inicializados para no tener que inicializarlos cada vez
	// que los vayamos a usar
	private static ControladorUsuario cUsuario = new ControladorUsuario();
	private static Menus menu = new Menus();
	private static MetodosCuenta mCuenta = new MetodosCuenta();
	private static ControladorCuenta cCuenta = new ControladorCuenta();

	// Opciones a elegir de la tabla usuario
	public String menuUusario() {

		Icon icono = new ImageIcon(getClass().getResource("../img/usuario.png"));

		String[] opciones = { "Mostrar todos los valores", "Borrar Usuario", "Crear Usuario", "Modificar Usuario",
				"Buscar por telefono", "Buscar por clave", "Salir" };

		String opcionElegida = (String) JOptionPane.showInputDialog(null, "¿Que deseas realizar?", "Tabla Usuario",
				JOptionPane.QUESTION_MESSAGE, icono, opciones, opciones[0]);

		return opcionElegida;

	}

	// Este metodo contiene un swicth con las posibles elecciones que haya elegido
	// el usuario
	// cuendo el usuario eligue una opciion entra y realiza esa función
	public void opcionesUsuario() {

		// Atributos para controlar lo que introduce el usuario
		boolean repetir;
		boolean existePk;
		int numeroIntroudcido;
		// Usuario que se crea nueva cada vez que se inicializa el bucle
		Usuario user;

		do {
			// Inicializamos valores cada vez que se vuelve a ejecutar el bucle
			String opcion = menuUusario();
			repetir = true;
			numeroIntroudcido = -1;
			existePk = false;
			user = new Usuario();

			switch (opcion) {

			case "Mostrar todos los valores":
				List<Usuario> listaUsuario = cUsuario.findAll();
				System.out.println("---------Los valores de la tabla Usuario son:----------");
				listaUsuario.forEach(System.out::println);
				break;

			case "Borrar Usuario":
				numeroIntroudcido = menu.ComprobarNumeroDevolver();
				existePk = comprobarPk(numeroIntroudcido);
				if (existePk) {

					user = cUsuario.findByPK(numeroIntroudcido);

					// Cramos una lista de compras para ver si esta esta llena o vacia
					// en caso de estar vacia se podra eliminar el usuario
					List<Compra> listaCompras = user.getComprasUsuario();

					if (listaCompras.size() == 0) {
						cUsuario.borrarEntidad(user);
						JOptionPane.showMessageDialog(null, "El usuario a sido borrada correctamente", "Perfecto",
								JOptionPane.DEFAULT_OPTION);
					} else {
						JOptionPane.showMessageDialog(null,
								"No se puede borrar el usuario, ya que este a ralizado compras", "Error",
								JOptionPane.DEFAULT_OPTION);
						System.out.println(user.getComprasUsuario());
					}
				} else {
					JOptionPane.showMessageDialog(null, "El valor con esta pk no existe", "Error",
							JOptionPane.DEFAULT_OPTION);

				}
				break;

			case "Crear Usuario":

				user.setNombreuser(JOptionPane.showInputDialog("Escribe el nombre"));
				user.setApellidouser(JOptionPane.showInputDialog("Escribe el apellido"));
				user.setDireccionuser(JOptionPane.showInputDialog("Escribe la dirección"));
				user.setTelefonouser(JOptionPane.showInputDialog("Escribe el telefono"));
				user.setCuentabancaria(JOptionPane.showInputDialog("Escribe la cuenta bancaria"));

				JOptionPane.showMessageDialog(null, "Introduce la cuenta con la que quieres relacionar el Usuario",
						"Recordar", JOptionPane.DEFAULT_OPTION);
				// Comprobamos que la cuenta existe, utilizando los metodos de la clase
				// metodoscuenta
				numeroIntroudcido = menu.ComprobarNumeroDevolver();
				existePk = mCuenta.comprobarPk(numeroIntroudcido);

				if (existePk) {
					Cuenta cuentaPk = cCuenta.findByPK(numeroIntroudcido);

					if (cuentaPk.getUsuariosCuenta() == null) {
						user.setCuenta(cuentaPk);
						cUsuario.crearEntidad(user);
						JOptionPane.showMessageDialog(null, "El usuario fue creado correctamente", "Correcto",
								JOptionPane.DEFAULT_OPTION);
						System.out.println("---------Los valores del nuevo Usuario son:----------");
						System.out.println(user.toString());

					} else {
						JOptionPane.showMessageDialog(null, "La cuenta ya esta relacionada", "error",
								JOptionPane.DEFAULT_OPTION);
					}
				} else {
					JOptionPane.showMessageDialog(null, "La pk no existe", "error", JOptionPane.DEFAULT_OPTION);
				}

				break;

			case "Modificar Usuario":
				numeroIntroudcido = menu.ComprobarNumeroDevolver();
				existePk = comprobarPk(numeroIntroudcido);
				if (existePk) {
					user = cUsuario.findByPK(numeroIntroudcido);
					realizarModificacion(user);
					JOptionPane.showMessageDialog(null, "El Usuario a sido modificada Correctamente", "Correcto",
							JOptionPane.DEFAULT_OPTION);
					System.out.println("---------Los valores del Usuario modificado son:----------");
					System.out.println(user.toString());
				} else {
					JOptionPane.showMessageDialog(null, "El valor con esta pk no existe", "Error",
							JOptionPane.DEFAULT_OPTION);
				}
				break;

			case "Buscar por telefono":
				String introduceTel = JOptionPane.showInputDialog("Escribe el telefono a buscar");
				try {
					user = cUsuario.findByTel(introduceTel);
					System.out.println("---------El usuario con el telefono '" + introduceTel + "' es:----------");
					System.out.println(cUsuario.findByTel(introduceTel).toString());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "El telefono introducido no existe", "Error",
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
					System.out.println("---------El Usuario con la Pk '" + numeroIntroudcido + "' es:----------");
					System.out.println(cUsuario.findByPK(numeroIntroudcido));
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

	// Metodo que comprueba si la pk existe en la tabla Usuario
	public boolean comprobarPk(int pk) {

		try {
			cUsuario.findByPK(pk);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// Metodo que realiza la modificacion de los valores de usuario
	public void realizarModificacion(Usuario userModi) {
		String eleccionModificar = valoresModificar();

		switch (eleccionModificar) {
		case "Nombre":
			userModi.setNombreuser(JOptionPane.showInputDialog("Escribe el nombre"));
			break;
		case "Apellido":
			userModi.setApellidouser(JOptionPane.showInputDialog("Escribe el apellido"));
			break;
		case "Telefono":
			userModi.setTelefonouser(JOptionPane.showInputDialog("Escribe el telefono"));
			break;
		case "Direccion":
			userModi.setDireccionuser(JOptionPane.showInputDialog("Escribe la dirección"));
			break;
		case "CuentaBancaria":
			userModi.setCuentabancaria(JOptionPane.showInputDialog("Escribe la  cuenta bancaria"));
			break;
		case "Todo":
			userModi.setNombreuser(JOptionPane.showInputDialog("Escribe el nombre"));
			userModi.setApellidouser(JOptionPane.showInputDialog("Escribe el apellido"));
			userModi.setDireccionuser(JOptionPane.showInputDialog("Escribe la dirección"));
			userModi.setTelefonouser(JOptionPane.showInputDialog("Escribe el telefono"));
			userModi.setCuentabancaria(JOptionPane.showInputDialog("Escribe la  cuenta bancaria"));
			break;
		}
		cUsuario.ModificarEntidad(userModi);
	}

	// Metodo que realiza la modificacion de los valores de una Usuario, eligiendo
		// los valores que quiere modificar
	public String valoresModificar() {

		Icon icono = new ImageIcon(getClass().getResource("../img/editar.png"));

		String[] opciones = { "Nombre", "Apellido", "Telefono", "Direccion", "CuentaBancaria", "Todo" };

		String opcionElegida = (String) JOptionPane.showInputDialog(null, "¿Que deseas modificar?", "Modificar Usuario",
				JOptionPane.QUESTION_MESSAGE, icono, opciones, opciones[0]);

		return opcionElegida;
	}

}
