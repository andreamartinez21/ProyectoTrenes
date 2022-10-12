package ventanas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import BD.BD;
import clases.Cliente;
import clases.Viaje;

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
		
		if(BD.editarBD(clienteActual, nombre, apellido, dni, email, numTelefono, cuentaBancaria)) {
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
         // Apertura del fichero y creacion de BufferedReader
         archivo = new File ("ficheroViajes.txt");
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);

         // Lectura del fichero
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
         		System.out.println("Se ha añadido el viaje corrrectamente.");
         	}
         }
      }
      catch(Exception e){
         e.printStackTrace();
      }finally{
         // En el finally cerramos el fichero, para asegurarnos
         // que se cierra tanto si todo va bien como si salta 
         // una excepcion.
         try{                    
            if( null != fr ){   
               fr.close();     
            }                  
         }catch (Exception e2){ 
            e2.printStackTrace();
         }
      }
      return true;
   }
   
   public static Map<String, List<String>> obtenerMapaOrigenDestino() {
	   List<Viaje> listaViajes = new ArrayList<>();
	   List<String> listaOrigen = new ArrayList<>();
	   List<String> listaDestino = new ArrayList<>();
	   Map<String, List<String>> mapaOrigenDestino = new HashMap<>();
	   	   
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
