package appfinal;

import java.util.List;

import javax.swing.JOptionPane;

import controladores.ControladorUsuario;
import entidades.Cuenta;
import entidades.Usuario;

public class MetodosUsuario {

	// Los atributos de la clase son objetos para acceder a los metodos de otras
	// clases
	private static ControladorUsuario cu = new ControladorUsuario();
	private static Menus menu = new Menus();

	// Opciones a elegir de la tabla usuario
	public String menuUusario() {

		String[] opciones = { "Mostrar todo", "Borrar", "Crear", "Modificar", "Buscar por telefono", "Relacionar",
				"Buscar por clave", "Salir" };

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
		Usuario user;
		
		do {
			String opcion = menuUusario();
			repetir = true;
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
					if (user.getComprasUsuario() == null) {
						cu.borrarEntidad(user);
						JOptionPane.showMessageDialog(null, "El usuario a sido borrada correctamente", "Perfecto",
								JOptionPane.DEFAULT_OPTION);
					} else {
						JOptionPane.showMessageDialog(null,
								"No se puede borrar el usuario, ya que este a ralizado compras", "Error",
								JOptionPane.DEFAULT_OPTION);
					}
				} else {
					JOptionPane.showMessageDialog(null, "El valor con esta pk no existe", "Error",
							JOptionPane.DEFAULT_OPTION);
				}
				break;
			case "Crear":

				break;
			case "Modificar":

			case "Buscar por telefono":

				break;
			case "Relacionar":

				break;
			case "Buscar por clave":

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

}
