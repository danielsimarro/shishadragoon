package appfinal;

public class CierreAplicacion {

	//Esta clase permite asegurarnos de que se cierra la aplicación por completo cuando se llame
	public void attachShutDownHook() {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {

			}
		});

	}
}
