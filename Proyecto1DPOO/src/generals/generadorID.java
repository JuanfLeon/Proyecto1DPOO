package generals;

public class generadorID {
	static int contadorJuego=0;
	static  int contadorUsuario=0;
	
	public static int getContadorJuego() {
		int resp= contadorJuego;
		contadorJuego+=1;
		return resp;
	}
	public static int getContadorUsuario() {
		int resp= contadorUsuario;
		contadorUsuario+=1;
		return resp;
	}
	
	public static String generarIDJuego(String nombreJuego) {
		return nombreJuego+getContadorJuego();
	}
	public static String generarIDUsuario(String nombreUsuario) {
		return nombreUsuario+getContadorUsuario();
	}
	
}
