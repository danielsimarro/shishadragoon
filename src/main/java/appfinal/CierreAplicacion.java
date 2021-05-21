package appfinal;

public class CierreAplicacion {

	public void attachShutDownHook() {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {

			}
		});

	}
}
