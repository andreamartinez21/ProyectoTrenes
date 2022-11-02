package ventanas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.logging.Level;

import BD.BD;
import clases.Cliente;
import clases.Viaje;
import log.Log;

public class Metodos {
	
	public static boolean register(String nombre, String apellido, String usuario, String contrasenya, String dni, String email, String numTelefono, String cuentaBancaria) {
		   	
    	if(BD.registerBD(nombre, apellido, usuario, contrasenya, dni, email, numTelefono, cuentaBancaria)) {
    		return true;
    	} else {
    		return false;
    	}
    }
	
	public static boolean login(String usuario, String contrasenya) {
		
		if(BD.loginBD(usuario, contrasenya)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean editar(Cliente clienteActual, String nombre, String apellido, String dni, String email, String numTelefono, String cuentaBancaria) {
		
		if(BD.editarClienteBD(clienteActual, nombre, apellido, dni, email, numTelefono, cuentaBancaria)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean borrarCliente(Cliente clienteActual) {
		if(BD.borrarClienteBD(clienteActual)) {
			return true;
		} else {
			return false;
		}
	}
	
   public static boolean leeFichero() { // tabular bien
	   
      File archivo = null;
      FileReader fr = null;
      BufferedReader br = null;
      String[] data = new String[6];

      try {
         // abrir fichero y crear de BufferedReader
         archivo = new File ("ficheroViajes.txt");
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);

         // lectura fichero
         String linea;
         while((linea = br.readLine()) != null) {
            data = linea.split(";");
         
         	String localizador = data[0];
         	String origen = data[1];
         	String destino = data[2];
         	String fecha = data[3];
         	int aforo = Integer.valueOf(data[4]);
         	double precio = Double.valueOf(data[5]);
         	
         	if(BD.anyadirViajeBD(localizador, origen, destino, fecha, aforo, precio)) {
         		Log.logger.log(Level.INFO, "Se ha añadido el viaje corrrectamente.");
         	}
         }
      }
      catch(Exception e){
         e.printStackTrace();
      }finally{
         // cerrar fichero
         try{
            if(null != fr){
               fr.close();
            }
         }catch (Exception e2){
            e2.printStackTrace();
         }
      }
      return true;
   }
   
   public static Map<String, HashSet<String>> obtenerMapaOrigenDestino() {
	   HashSet<Viaje> listaViajes = new HashSet<>();
	   HashSet<String> listaOrigen = new HashSet<>();
	   HashSet<String> listaDestino = new HashSet<>();
	   Map<String, HashSet<String>> mapaOrigenDestino = new HashMap<>();
	   	   
	   listaViajes = BD.getViajesBD();
	  
	   for (Viaje i : listaViajes) {
		   listaOrigen.add(i.getOrigen());
		   listaDestino.add(i.getDestino());
	   }
	   
	   mapaOrigenDestino.put("Origen", listaOrigen);
	   mapaOrigenDestino.put("Destino", listaDestino);
	   
	   return mapaOrigenDestino;
   }
}
