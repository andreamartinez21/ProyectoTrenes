

import java.io.IOException;

import BD.BD;
import ventanas.Metodos;
import ventanas.VentanaInicio;

public class Main {

	public static void main(String[] args) throws IOException {
		if(BD.connect() != null) {
//			BD.borrarViajes();
			Metodos.leeFichero();
			new VentanaInicio();
		} else {
			System.out.println("Ha habido un problema para conectarse.");
		}
	}
}