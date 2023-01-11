package ventanas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

import javax.swing.JOptionPane;

import BD.BD;
import clases.Cliente;
import clases.Viaje;
import log.Log;

public class Metodos {
	
	static BD bd = new BD();
	
	public static List<Viaje> resultado = new ArrayList<Viaje>();
	public static int numTransbordos = 0;

	public static boolean register(String nombre, String apellido, String usuario, String contrasenya, String dni,
			String email, String numTelefono, String cuentaBancaria) {

		if (bd.registerBD(nombre, apellido, usuario, contrasenya, dni, email, numTelefono, cuentaBancaria)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean login(String usuario, String contrasenya) {

		if (bd.loginBD(usuario, contrasenya)) {
			return true;
		} else {
			return false;
		}
	}

	public static void editarCliente(Cliente clienteActual, String nombre, String apellido, String dni, String email,
			String numTelefono, String cuentaBancaria) {

		if (bd.editarClienteBD(clienteActual, nombre, apellido, dni, email, numTelefono, cuentaBancaria)) {
			JOptionPane.showMessageDialog(null, "Perfil actualizado correctamente.");
			Log.logger.log(Level.INFO, "Perfil actualizado correctamente.");
		} else {
			JOptionPane.showMessageDialog(null, "Ha habido un error al actualizar el perfil.");
			Log.logger.log(Level.SEVERE, "Ha habido un error al actualizar el perfil.");
		}
	}

	public static boolean borrarCliente(Cliente clienteActual) {
		
		if (bd.borrarClienteBD(clienteActual)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean leeFicheroViajes() {

		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		String[] data = new String[7];

		try {
			// abrir fichero y crear BufferedReader
			
			archivo = new File("ficheros/ficheroViajes.txt");
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			// lectura fichero
			
			String linea;
			
			while ((linea = br.readLine()) != null) {
				data = linea.split(";");

				String localizador = data[0];
				String origen = data[1];
				String destino = data[2];
				String fecha = data[3];
				int aforo = Integer.valueOf(data[4]);
				double precio = Double.valueOf(data[5]);
				String imagen = data[6];

				if (bd.anyadirViajeBD(localizador, origen, destino, fecha, aforo, precio, imagen)) {
					Log.logger.log(Level.INFO, "Se ha añadido el viaje corrrectamente.");
				}
			}
		} catch (Exception e) {
			Log.logger.log(Level.SEVERE, "No se ha podido leer el fichero." + e.getStackTrace());
		} finally {
			// cerrar fichero
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				Log.logger.log(Level.SEVERE, "No se ha podido cerrar el fichero." + e2.getStackTrace());
			}
		}
		return true;
	}

	public static Map<String, Set<String>> obtenerMapaOrigenDestino() {

		int tipo = VentanaCompra.tipoBilleteInt;
		List<Viaje> listaViajes = new ArrayList<Viaje>();
		Set<String> listaOrigen = new HashSet<String>();
		Set<String> listaDestino = new HashSet<String>();
		Map<String, Set<String>> mapaOrigenDestino = new HashMap<String, Set<String>>();

		listaViajes = bd.getViajesBD();

		for (Viaje viaje : listaViajes) {
			listaOrigen.add(viaje.getOrigen());
		}

		switch (tipo) {
		case 0: // ida

			listaDestino.clear();

			for (Viaje viaje : listaViajes) {
				if (viaje.getOrigen().equals(VentanaCompra.origen) && viaje.getAforo() != 0) {
					listaDestino.add(viaje.getDestino());
				}
			}
			break;
			
		case 1: // ida y vuelta

			listaDestino.clear();

			for (Viaje viaje : listaViajes) {
				if (viaje.getOrigen().equals(VentanaCompra.origen) && viaje.getAforo() != 0) {
					String destino = viaje.getDestino();
					for (Viaje viaje2 : listaViajes) {
						if (destino.equals(viaje2.getOrigen()) && viaje.getOrigen().equals(viaje2.getDestino())) {
							listaDestino.add(viaje.getDestino());
						}
					}
				}
			}
			break;
		}

		mapaOrigenDestino.put("Origen", listaOrigen);
		mapaOrigenDestino.put("Destino", listaDestino);

		return mapaOrigenDestino;
	}
	
	public static Set<String> destinosTransbordo() {

		List<Viaje> listaViajes = new ArrayList<Viaje>();
		listaViajes = bd.getViajesBD();
		Set<String> destinos = new HashSet<String>();

		for (Viaje viaje : listaViajes) {
			destinos.add(viaje.getDestino());
		}
		return destinos;
	}

	public static boolean existeViaje(String origen, String destino, String fechaIda, String fechaVuelta,
			int cantBilletes, int tipo) throws ParseException {

		List<Viaje> listaViajes = new ArrayList<Viaje>();
		listaViajes = bd.getViajesBD();
		int comp = 0;
		boolean fechaMal = false;

		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

		Date fecha1 = formato.parse(fechaIda);
		Date fecha2 = formato.parse(fechaVuelta);

		switch (tipo) {
		case 0: // ida
			for (Viaje viaje : listaViajes) {
				if (viaje.getOrigen().equals(origen) && viaje.getDestino().equals(destino)
						&& viaje.getFecha().equals(fechaIda) && viaje.getAforo() >= cantBilletes) {
					Log.logger.log(Level.INFO, "Viaje disponible.");
					return true;
				}
			}
			JOptionPane.showMessageDialog(null, "Viaje no disponible.");
			Log.logger.log(Level.SEVERE, "Viaje no disponible.");
			return false;

		case 1: // ida y vuelta
			for (Viaje viaje : listaViajes) {
				if (viaje.getOrigen().equals(origen) && viaje.getDestino().equals(destino)
						&& viaje.getFecha().equals(fechaIda) && viaje.getAforo() >= cantBilletes) {
					Log.logger.log(Level.INFO, "Viaje disponible.");
					comp++;
				}
				if (viaje.getOrigen().equals(destino) && viaje.getDestino().equals(origen)
						&& viaje.getFecha().equals(fechaVuelta) && viaje.getAforo() >= cantBilletes) {
					Log.logger.log(Level.INFO, "Viaje disponible.");
					comp++;
				}
				if (comp == 2 && (fecha1.after(fecha2) || fecha1.equals(fecha2))) {
					return true;
				} else if (comp == 2 && fecha1.before(fecha2)) {
					fechaMal = true;
				}
			}
			if (fechaMal) {
				JOptionPane.showMessageDialog(null, "La fecha de ida es posterior a la de vuelta.");
				Log.logger.log(Level.SEVERE, "La fecha de ida es posterior a la de vuelta.");
			} else {
				JOptionPane.showMessageDialog(null, "Viaje no disponible.");
				Log.logger.log(Level.SEVERE, "Viaje no disponible.");
			}
			return false;
		default:
			Log.logger.log(Level.SEVERE, "Error.");
		}
		return true;
	}
	
	public static boolean actualizaAforoFichero(Viaje viajeIda, Viaje viajeVuelta, int cantBilletes) {

		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		String[] data = new String[7];
		
		try {
			File file = new File("ficheros/ficheroViajes2.txt");
			FileWriter writer = new FileWriter(file);
			BufferedWriter buffer = new BufferedWriter(writer);
			
			archivo = new File("ficheros/ficheroViajes.txt");
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			
			String linea;
			
			while ((linea = br.readLine()) != null) {
				data = linea.split(";");

				String localizador = data[0];
				String origen = data[1];
				String destino = data[2];
				String fecha = data[3];
				int aforo = (Integer.valueOf(data[4]));

				if (viajeIda.getLocalizador().equals(localizador)) {
					aforo -= cantBilletes;
				} else if (viajeVuelta.getLocalizador().equals(localizador)) {
					aforo -= cantBilletes;
				}
				
				double precio = Double.valueOf(data[5]);
				String imagen = data[6];

				buffer.write(localizador + ";");
				buffer.write(origen + ";");
				buffer.write(destino + ";");
				buffer.write(fecha + ";");
				buffer.write(aforo + ";");
				buffer.write(precio + ";");
				buffer.write(imagen);
				buffer.newLine();
			}
			
			buffer.flush();
			buffer.close();
			writer.close();
			
		} catch (Exception e) {
			Log.logger.log(Level.SEVERE, "No se ha podido leer ni escribir el fichero." + e.getStackTrace());
		} finally {
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				Log.logger.log(Level.SEVERE, "No se ha podido cerrar el fichero." + e2.getStackTrace());
			}
		}
		return true;
	}
	
	public static boolean actualizaAforoFichero2(Viaje viajeIda, Viaje viajeVuelta, int cantBilletes) {

		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		String[] data = new String[7];
		
		try {
			File file = new File("ficheros/ficheroViajes.txt");
			FileWriter writer = new FileWriter(file);
			BufferedWriter buffer = new BufferedWriter(writer);
			
			archivo = new File("ficheros/ficheroViajes2.txt");
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			
			String linea;
			
			while ((linea = br.readLine()) != null) {
				data = linea.split(";");

				String localizador = data[0];
				String origen = data[1];
				String destino = data[2];
				String fecha = data[3];
				int aforo = (Integer.valueOf(data[4]));
				double precio = Double.valueOf(data[5]);
				String imagen = data[6];

				buffer.write(localizador + ";");
				buffer.write(origen + ";");
				buffer.write(destino + ";");
				buffer.write(fecha + ";");
				buffer.write(aforo + ";");
				buffer.write(precio + ";");
				buffer.write(imagen);
				buffer.newLine();
			}
			
			buffer.flush();
			buffer.close();
			writer.close();
			
		} catch (Exception e) {
			Log.logger.log(Level.SEVERE, "No se ha podido leer ni escribir el fichero." + e.getStackTrace());
		} finally {
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				Log.logger.log(Level.SEVERE, "No se ha podido cerrar el fichero." + e2.getStackTrace());
			}
		}
		return true;
	}

	public static double calcularPrecioBillete(String tipo, Viaje viajeIda, Viaje viajeVuelta, int clase, int comida,
			int asientoIndividual, int seguroViaje, int mesa, int conUsuario) {

		double precioBillete = 0.00;

		if (clase == 1) {
			precioBillete += 12.00;
		}
		if (comida == 1) {
			precioBillete += 15.00;
		}
		if (asientoIndividual == 1) {
			precioBillete += 9.00;
		}
		if (seguroViaje == 1) {
			precioBillete += 3.00;
		}
		if (mesa == 1) {
			precioBillete += 2.00;
		}
		if (tipo.equals("Ida y vuelta")) {
			precioBillete *= 2;
			precioBillete += viajeVuelta.getPrecio();
		}

		precioBillete += viajeIda.getPrecio();

		if (conUsuario == 0) {
			precioBillete *= 1.1;
		}

		return precioBillete;
	}

	public static void crearTicket() {
		
		try {
			File file = new File("tickets/ticket" + leeFicheroNumTicket() + "-" + BD.clienteActual.getUsuario() + ".txt");
			escribeFicheroNumTicket();
			FileWriter writer = new FileWriter(file);
			BufferedWriter buffer = new BufferedWriter(writer);

			buffer.write("VIAJE A " + VentanaCompra.comboDestino.getSelectedItem().toString().toUpperCase());
			buffer.newLine();

			if (VentanaInicio.var == 1) {
				buffer.write("Nombre del comprador: " + VentanaConfirmacionCompra.textoNombreComprador.getText());
				buffer.newLine();
			} else if (VentanaInicio.var == 2) {
				buffer.write("Nombre del comprador: " + BD.clienteActual.getNombre() + " " + BD.clienteActual.getApellido());
				buffer.newLine();
			}

			buffer.write("Tipo de billete: " + VentanaCompra.tipoBillete);
			buffer.newLine();
			buffer.write("Origen: " + VentanaCompra.comboOrigen.getSelectedItem().toString());
			buffer.newLine();
			buffer.write("Destino: " + VentanaCompra.comboDestino.getSelectedItem().toString());
			buffer.newLine();
			buffer.write("Fecha ida: " + VentanaCompra.textFechaIda.getText());
			buffer.newLine();

			if (VentanaCompra.tipoBillete.equals("Ida y vuelta")) {
				buffer.write("Fecha vuelta: " + VentanaCompra.textFechaVuelta.getText());
				buffer.newLine();
			}

			buffer.write("Cantidad billetes: " + (int) VentanaCompra.spinnerNumBilletes.getValue());
			buffer.newLine();
			buffer.write(VentanaCompra.clase);
			buffer.newLine();

			if (!VentanaCompra.extra1.equals("") && !VentanaCompra.extra2.equals("")) {
				buffer.write("Extras: " + VentanaCompra.extra1 + ", " + VentanaCompra.extra2);
			} else if (VentanaCompra.extra1.equals("") && !VentanaCompra.extra2.equals("")) {
				buffer.write("Extras: " + VentanaCompra.extra2);
			} else if (!VentanaCompra.extra1.equals("") && VentanaCompra.extra2.equals("")) {
				buffer.write("Extras: " + VentanaCompra.extra1);
			} else if (VentanaCompra.extra1.equals("") && VentanaCompra.extra2.equals("")) {
				buffer.write("Extras: sin extras");
			}

			buffer.newLine();
			buffer.write("Precio total: "
							+ VentanaConfirmacionCompra.formato1.format((calcularPrecioBillete(VentanaCompra.tipoBillete,
									devuelveViaje(VentanaCompra.comboOrigen.getSelectedItem().toString(),
											VentanaCompra.comboDestino.getSelectedItem().toString(), VentanaCompra.textFechaIda.getText()),
									devuelveViaje(VentanaCompra.comboDestino.getSelectedItem().toString(),
											VentanaCompra.comboOrigen.getSelectedItem().toString(), VentanaCompra.textFechaVuelta.getText()),
									VentanaCompra.claseInt, VentanaCompra.extraComida,
									VentanaCompra.extraAsientoIndividual, VentanaCompra.extraSeguroViaje,
									VentanaCompra.extraMesa, VentanaConfirmacionCompra.conUsuario)) * ((int) VentanaCompra.spinnerNumBilletes.getValue()))
							+ " €");
			buffer.newLine();

			buffer.flush();
			buffer.close();
			writer.close();

		} catch (Exception e) {
			Log.logger.log(Level.SEVERE, "No se ha podido crear el ticket." + e.getStackTrace());
		}
	}
	
	public static Viaje devuelveViaje(String origen, String destino, String fecha) { // para conseguir el precio total de la compra
		
		Viaje viajeActual = new Viaje();
		List<Viaje> listaViajes = new ArrayList<Viaje>();
		listaViajes = bd.getViajesBD();
		
		for (Viaje viaje : listaViajes) {
			if(viaje.getOrigen().equals(origen) && viaje.getDestino().equals(destino) && viaje.getFecha().equals(fecha)) {
				viajeActual = viaje;
			}
		}
		
		return viajeActual;
	}
	
	public static int leeFicheroNumTicket() {
		
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		int num = 0;
		
		try {
			archivo = new File("ficheros/ficheroNumTicket.txt");
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			
			num = Integer.valueOf(br.readLine());
					
		} catch (Exception e) {
			Log.logger.log(Level.SEVERE, "No se ha podido leer el fichero." + e.getStackTrace());
		} finally {
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				Log.logger.log(Level.SEVERE, "No se ha podido cerrar el fichero." + e2.getStackTrace());
			}
		}
		
		return num;
	}
	
	public static void escribeFicheroNumTicket() {
		
		int num = leeFicheroNumTicket();
		num++;
		String numero = String.valueOf(num);
		
		try {
			File file = new File("ficheros/ficheroNumTicket.txt");
			FileWriter writer = new FileWriter(file);
			BufferedWriter buffer = new BufferedWriter(writer);

			buffer.write(numero);
			
			buffer.flush();
			buffer.close();
			writer.close();
			
		} catch (Exception e) {
			Log.logger.log(Level.SEVERE, "No se ha podido escribir en el fichero." + e.getStackTrace());
		}
	}
	
	// recursividad

	public static List<Viaje> transbordo(String origen, String destino) throws ParseException {

		String nuevoOrigen = "";
		int numMaxTransbordos = 4; // 3 transbordos; 4 viajes
		List<Viaje> listaViajes = new ArrayList<Viaje>();
		listaViajes = bd.getViajesBD();
		Viaje viaje = new Viaje();
		
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

		Date fecha1 = null;
		Date fecha2 = null;

		for (Viaje viaje1 : listaViajes) {
			if (origen.equals(viaje1.getOrigen())) {
				viaje = viaje1;
				fecha1 = formato.parse(viaje.getFecha());
//				System.out.println(viaje);
			}
		}

		if (numTransbordos > numMaxTransbordos) {
			System.out.println("No se ha podido hacer transbordo.");
			resultado.clear(); // vaciar el arrayList para que devuelva null

		} else {

			if (viaje.getDestino().equals(destino)) {
//				System.out.println(viaje);
				resultado.add(viaje);
				return resultado;

			} else if (!viaje.getDestino().equals(destino)) {

				for (Viaje viaje2 : listaViajes) {
					fecha2 = formato.parse(viaje2.getFecha());
					if (viaje.getDestino().equals(viaje2.getOrigen()) && fecha1.after(fecha2)) {
						nuevoOrigen = viaje2.getOrigen();
//						System.out.println(viaje);
						resultado.add(viaje);
//						System.out.println(viaje2.toString());
					}
				}
				numTransbordos++;
				transbordo(nuevoOrigen, destino);
//				resultado.remove(resultado.size() - 1);
			}
		}
		return resultado;
	}
}
