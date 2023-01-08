package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import BD.BD;
import clases.Viaje;
import log.Log;
import properties.PropertiesClass;
import ventanas.Metodos;
import ventanas.VentanaTransbordo;
import ventanas.VentanaInicio;

public class Main {

	public static void main(String[] args) throws IOException {

		BD bd = new BD();

		try {
			Log.iniciarLog();
			PropertiesClass.getProperties();

			if (bd.connect() != null) {
				bd.borrarViajesBD();
				Metodos.leeFicheroViajes();
				new VentanaInicio();
				
				////////
				
//				List<Viaje> listaTransbordos = new ArrayList<Viaje>();
//				listaTransbordos = Metodos.transbordo("Madrid", "Berlin");
//				for (Viaje viaje : listaTransbordos) {
//					System.out.println(viaje.toString());
//				}
//				System.out.println(listaTransbordos.size());
				
				///////
				
			} else {
				Log.logger.log(Level.SEVERE, "Ha habido un problema para conectarse.");
				// Log.cerrarLog();
			}
		} catch (Exception e) {
			Log.logger.log(Level.SEVERE, "Error." + e.getStackTrace());
		}
	}
}
