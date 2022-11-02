package main;

import java.io.IOException;
import BD.BD;
import log.Log;
import properties.PropertiesClass;
import ventanas.Metodos;
import ventanas.VentanaInicio;

public class Main {

	public static void main(String[] args) throws IOException {
		Log.iniciarLog();
		PropertiesClass.getProperties();
		if(BD.connect() != null) {
			BD.borrarViajesBD();
			Metodos.leeFichero();
			new VentanaInicio();
		} else {
			System.out.println("Ha habido un problema para conectarse.");
//			Log.cerrarLog();
		}
	}
}
