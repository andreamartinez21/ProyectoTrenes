package main;

import java.io.IOException;
import BD.BD;
import log.Log;
import properties.PropertiesClass;
import ventanas.Metodos;
import ventanas.VentanaInicio;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BD bd = new BD();
		
		Log.iniciarLog();
		PropertiesClass.getProperties();
		if (bd.connect() != null) {
			bd.borrarViajesBD();
			Metodos.leeFicheroViajes();			
			new VentanaInicio();
		} else {
			System.out.println("Ha habido un problema para conectarse.");
//			Log.cerrarLog();
		}
	}
}
