package appfinal;

import java.util.List;

import javax.swing.JOptionPane;

import controladores.ControladorCuenta;
import controladores.ControladorUsuario;
import entidades.Compra;
import entidades.Cuenta;
import entidades.Usuario;

public class MetodosUsuario {

	// Los atributos de la clase son objetos para acceder a los metodos de otras
	// clases
	private static ControladorUsuario cu = new ControladorUsuario();
	private static Menus menu = new Menus();
	private static MetodosCuenta mc = new MetodosCuenta();
	private static ControladorCuenta cc = new ControladorCuenta();

	// Opciones a elegir de la tabla usuario
	public String menuUusario() {

		String[] opciones = { "Mostrar todo", "Borrar", "Crear", "Modificar", "Buscar por telefono", "Buscar por clave",
				"Salir" };

		String opcionElegida = (String) JOptionPane.showInputDialog(null, "¿Que deseas realizar?", "Elegir",
				JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

		return opcionElegida;

	}

	// Permite entrar a los metodos de la tabla cuenta dependiendo de la eleccion
	// hecha por el usuario
	public void opcionesUsuario() {

		boolean repetir;
		boolean existePk;
		int numeroIntroudcido;
		// Usuario que se crea nueva cada vez que se inicializa el bucle
		Usuario user;
		// Valores modificables del usuario
		do {
			String opcion = menuUusario();
			repetir = true;
			numeroIntroudcido = -1;
			existePk = false;
			user = new Usuario();

			switch (opcion) {
			case "Mostrar todo":
				List<Usuario> listaUsuario = cu.findAll();
				listaUsuario.forEach(System.out::println);
				break;
			case "Borrar":
				numeroIntroudcido = menu.ComprobarNumeroDevolver();
				existePk = comprobarPk(numeroIntroudcido);
				if (existePk) {

					user = cu.findByPK(numeroIntroudcido);

					// Cramos una lista de compras para ver si esta esta llena o vacia
					// en caso de estar vacia se podra eliminar el usuario
					List<Compra> listaCompras = user.getComprasUsuario();
					if (listaCompras.size() == 0) {
						cu.borrarEntidad(user);
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
			case "Crear":
				user.setNombreuser(JOptionPane.showInputDialog("Escribe el nombre"));
				user.setApellidouser(JOptionPane.showInputDialog("Escribe el apellido"));
				user.setDireccionuser(JOptionPane.showInputDialog("Escribe la dirección"));
				user.setTelefonouser(JOptionPane.showInputDialog("Escribe el telefono"));
				user.setCuentabancaria(JOptionPane.showInputDialog("Escribe la  cuenta bancaria"));

				JOptionPane.showMessageDialog(null, "Introduce la cuenta con la que quieres relacionar", "Recordar",
						JOptionPane.DEFAULT_OPTION);
				numeroIntroudcido = menu.ComprobarNumeroDevolver();
				existePk = mc.comprobarPk(numeroIntroudcido);
				if (existePk) {
					Cuenta cuentaPk = cc.findByPK(numeroIntroudcido);

					if (cuentaPk.getUsuariosCuenta() == null) {
						user.setCuenta(cuentaPk);
						cu.crearEntidad(user);
						JOptionPane.showMessageDialog(null, "El usuario fue creado correctamente", "Correcto",
								JOptionPane.DEFAULT_OPTION);

					} else {
						JOptionPane.showMessageDialog(null, "La cuenta ya esta relacionada", "error",
								JOptionPane.DEFAULT_OPTION);
					}
				} else {
					JOptionPane.showMessageDialog(null, "La pk no existe", "error",
							JOptionPane.DEFAULT_OPTION);
				}

				break;
			case "Modificar":
				numeroIntroudcido = menu.ComprobarNumeroDevolver();
				existePk = comprobarPk(numeroIntroudcido);
				if (existePk) {
					user = cu.findByPK(numeroIntroudcido);
					realizarModificacion(user);
				} else {
					JOptionPane.showMessageDialog(null, "El valor con esta pk no existe", "Error",
							JOptionPane.DEFAULT_OPTION);
				}
				break;

			case "Buscar por telefono":
				String introduceTel = JOptionPane.showInputDialog("Escribe el telefono a buscar");
				try {
					user = cu.findByTel(introduceTel);
					System.out.println(user.toString());
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
					System.out.println(cu.findByPK(numeroIntroudcido));
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
			cu.findByPK(pk);
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
		cu.ModificarEntidad(userModi);
	}

	// Metodo que permite elegir que queremos modificar
	public String valoresModificar() {

		String[] opciones = { "Nombre", "Apellido", "Telefono", "Direccion", "CuentaBancaria", "Todo" };

		String opcionElegida = (String) JOptionPane.showInputDialog(null, "¿Que deseas modificar?", "Elegir",
				JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

		return opcionElegida;
	}

}
