package ventanas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import BD.BD;
import clases.Cliente;
import clases.Viaje;
import log.Log;

public class Metodos {

	public static boolean register(String nombre, String apellido, String usuario, String contrasenya, String dni,
			String email, String numTelefono, String cuentaBancaria) {

		if (BD.registerBD(nombre, apellido, usuario, contrasenya, dni, email, numTelefono, cuentaBancaria)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean login(String usuario, String contrasenya) {

		if (BD.loginBD(usuario, contrasenya)) {
			return true;
		} else {
			return false;
		}
	}

	public static void editar(Cliente clienteActual, String nombre, String apellido, String dni, String email,
			String numTelefono, String cuentaBancaria) {

		if (BD.editarClienteBD(clienteActual, nombre, apellido, dni, email, numTelefono, cuentaBancaria)) {
			JOptionPane.showMessageDialog(null, "Perfil actualizado correctamente.");
			Log.logger.log(Level.INFO, "Perfil actualizado correctamente.");
		} else {
			JOptionPane.showMessageDialog(null, "Ha habido un error al actualizar el perfil.");
			Log.logger.log(Level.SEVERE, "Ha habido un error al actualizar el perfil.");
		}
	}

	public static boolean borrarCliente(Cliente clienteActual) {
		if (BD.borrarClienteBD(clienteActual)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean leeFichero() {

		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		String[] data = new String[7];

		try {
			// abrir fichero y crear de BufferedReader
			
			archivo = new File("ficheroViajes.txt");
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

				if (BD.anyadirViajeBD(localizador, origen, destino, fecha, aforo, precio, imagen)) {
					Log.logger.log(Level.INFO, "Se ha añadido el viaje corrrectamente.");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// cerrar fichero
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return true;
	}

	public static Map<String, HashSet<String>> obtenerMapaOrigenDestino() {
		List<Viaje> listaViajes = new ArrayList<>();
		HashSet<String> listaOrigen = new HashSet<>();
		HashSet<String> listaDestino = new HashSet<>();
		Map<String, HashSet<String>> mapaOrigenDestino = new HashMap<>();

		listaViajes = BD.getViajesBD();

		for (Viaje viaje : listaViajes) {
			listaOrigen.add(viaje.getOrigen());
			listaDestino.add(viaje.getDestino());
		}

		mapaOrigenDestino.put("Origen", listaOrigen);
		mapaOrigenDestino.put("Destino", listaDestino);

		return mapaOrigenDestino;
	}

	public static boolean existeViaje(String origen, String destino,
			/* String fechaIda, String fechaVuelta, */ int cantBilletes, int tipo) {
		List<Viaje> listaViajes = new ArrayList<Viaje>();
		listaViajes = BD.getViajesBD();
		int comp = 0;

		switch (tipo) {
		case 0: // ida
			for (Viaje viaje : listaViajes) {
				if (viaje.getOrigen().equals(origen) && viaje.getDestino().equals(destino)
						&& /* viaje.getFecha() == fechaIda && */ viaje.getAforo() >= cantBilletes) {
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
						&& /* viaje.getFecha() == fechaIda && */viaje.getAforo() >= cantBilletes) {
					Log.logger.log(Level.INFO, "Viaje disponible.");
					comp++;
				}
				if (viaje.getOrigen().equals(destino) && viaje.getDestino().equals(origen)
						&& /* viaje.getFecha() == fechaVuelta && */viaje.getAforo() >= cantBilletes) {
					Log.logger.log(Level.INFO, "Viaje disponible.");
					comp++;
				}
				if (comp == 2) {
					return true;
				}
			}
			JOptionPane.showMessageDialog(null, "Viaje no disponible.");
			Log.logger.log(Level.SEVERE, "Viaje no disponible.");
			return false;
		default:
			System.out.println("Error.");
		}
		return true;
	}
	
	public static boolean actualizaAforoFichero(Viaje viajeIda, Viaje viajeVuelta, int cantBilletes) {

		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		String[] data = new String[7];
		
		try {
			File file = new File("prueba.txt");
			FileWriter writer = new FileWriter(file);
			BufferedWriter buffer = new BufferedWriter(writer);
			
			// abrir fichero y crear de BufferedReader
			
			archivo = new File("ficheroViajes.txt");
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
			e.printStackTrace();
		} finally {
			// cerrar fichero
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
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
			File file = new File("ticket" + leeFicheroNumTicket() + "-" + BD.clienteActual.getUsuario() + ".txt");
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
			buffer.write("Fecha ida: ");
			buffer.newLine();

			if (VentanaCompra.tipoBillete.equals("Ida y vuelta")) {
				buffer.write("Fecha vuelta: ");
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
											VentanaCompra.comboDestino.getSelectedItem().toString()),
									devuelveViaje(VentanaCompra.comboDestino.getSelectedItem().toString(),
											VentanaCompra.comboOrigen.getSelectedItem().toString()),
									VentanaCompra.claseInt, VentanaCompra.extraComida,
									VentanaCompra.extraAsientoIndividual, VentanaCompra.extraSeguroViaje,
									VentanaCompra.extraMesa, VentanaConfirmacionCompra.conUsuario)) * ((int) VentanaCompra.spinnerNumBilletes.getValue()))
							+ " €");
			buffer.newLine();

			buffer.flush();
			buffer.close();
			writer.close();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static Viaje devuelveViaje(String origen, String destino/*, String fecha*/) { // para conseguir el precio total de la compra
		
		Viaje viajeActual = new Viaje();
		List<Viaje> listaViajes = new ArrayList<>();
		listaViajes = BD.getViajesBD();
		
		for (Viaje viaje : listaViajes) {
			if(viaje.getOrigen().equals(origen) && viaje.getDestino().equals(destino) /*&& viaje.getFecha().equals(fecha)*/) {
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
			archivo = new File("ficheroNumTicket.txt");
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			
			num = Integer.valueOf(br.readLine());
					
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return num;
	}
	
	public static void escribeFicheroNumTicket() {
		int num = leeFicheroNumTicket();
		num++;
		String numero = String.valueOf(num);
		
		try {
			File file = new File("ficheroNumTicket.txt");
			FileWriter writer = new FileWriter(file);
			BufferedWriter buffer = new BufferedWriter(writer);

			buffer.write(numero);
			
			buffer.flush();
			buffer.close();
			writer.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
