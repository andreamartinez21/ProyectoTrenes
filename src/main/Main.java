package main;

import java.io.IOException;

import BD.BD;
import log.Log;
import ventanas.Metodos;
import ventanas.VentanaInicio;

public class Main {

	public static void main(String[] args) throws IOException {
		Log.iniciarLog();
		if(BD.connect() != null) {
//			BD.borrarViajesBD();
			Metodos.leeFichero();
			new VentanaInicio();
		} else {
			System.out.println("Ha habido un problema para conectarse.");
//			Log.cerrarLog();
		}
	}
}


// que funcione el método borrarViajesBD
// en loginBD meter todo lo de cliente