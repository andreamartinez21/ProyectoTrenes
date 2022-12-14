package BD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import clases.Billete;
import clases.BilletePrimera;
import clases.BilleteSegunda;
import clases.Cliente;
import clases.Viaje;
import log.Log;
import properties.PropertiesClass;
import ventanas.Metodos;

public class BD {
	
	private static Connection conn = null;
	public static Cliente clienteActual = new Cliente();
	
	// conexi?n BD

	public Connection connect() {
		try {
			Class.forName(PropertiesClass.properties.getProperty("driver"));
			conn = DriverManager.getConnection(PropertiesClass.properties.getProperty("connection"));
			Log.logger.log(Level.INFO, "Conexi?n con la BD establecida.");
		} catch (ClassNotFoundException e) {
			Log.logger.log(Level.SEVERE, "Error cargando el driver de la BD" + e.getStackTrace());
		} catch (SQLException e) {
			Log.logger.log(Level.SEVERE, "Error al conectar con la BD" + e.getStackTrace());
		}
		return conn;
	}

	public void disconnect() {
		try {
			conn.close();
			Log.logger.log(Level.INFO, "Desconectado de la BD");
		} catch (SQLException e) {
			Log.logger.log(Level.SEVERE, "No se ha podido desconectar" + e.getStackTrace());
		}
	}

	// M?TODOS CLIENTE

	// m?todo register

	public boolean registerBD(String nombre, String apellido, String usuario, String contrasenya, String dni,
			String email, String numTelefono, String cuentaBancaria) {
		try {
			String consulta = "INSERT INTO cliente (usuario, contrasenya, nombre, apellido, dni, email, numTelefono, cuentaBancaria) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

			PreparedStatement ps = conn.prepareStatement(consulta);
			ps.setString(1, usuario);
			ps.setString(2, contrasenya);
			ps.setString(3, nombre);
			ps.setString(4, apellido);
			ps.setString(5, dni);
			ps.setString(6, email);
			ps.setString(7, numTelefono);
			ps.setString(8, cuentaBancaria);

			ps.executeUpdate();
			ps.close();

			Log.logger.log(Level.INFO, "Los datos se han introducido correctamente.");
			return true;

		} catch (Exception e) {
			Log.logger.log(Level.SEVERE, "No se ha podido insertar los datos." + e.getStackTrace());
			return false;
		}
	}

	// m?todo login

	public boolean loginBD(String usuario, String contrasenya) {

		try {
			ResultSet rs;

			String consulta = "SELECT * FROM cliente WHERE usuario = ? AND contrasenya = ?;";

			PreparedStatement ps = conn.prepareStatement(consulta);
			ps.setString(1, usuario);
			ps.setString(2, contrasenya);

			rs = ps.executeQuery(); // en rs est? lo que ha devuelto la bd

			while (rs.next()) {
				if (rs.getString("usuario").equals(usuario) && rs.getString("contrasenya").equals(contrasenya)) {

					String nombre = rs.getString("nombre");
					String apellido = rs.getString("apellido");
					String dni = rs.getString("dni");
					String email = rs.getString("email");
					String numTelefono = rs.getString("numTelefono");
					String cuentaBancaria = rs.getString("cuentaBancaria");
					List<Billete> listaBilletes = new ArrayList<Billete>();

					clienteActual = new Cliente(usuario, contrasenya, nombre, apellido, dni, email, numTelefono,
							cuentaBancaria, listaBilletes);

					ps.close();

					Log.logger.log(Level.INFO, "Usuario y contrase?a encontrados en la BD.");
					return true;
				}
			}
		} catch (Exception e) {
			Log.logger.log(Level.SEVERE, "Usuario y contrase?a no encontrados en la BD." + e.getStackTrace());
		}
		return false;
	}

	// m?todo editar cliente

	public boolean editarClienteBD(Cliente clienteActual, String nombre, String apellido, String dni,
			String email, String numTelefono, String cuentaBancaria) {
		try {
			String consulta = "UPDATE cliente SET nombre = ?, apellido = ?, dni = ?, email = ?, numTelefono = ?, cuentaBancaria = ? WHERE usuario = ? AND contrasenya = ?;";

			PreparedStatement ps = conn.prepareStatement(consulta);
			ps.setString(1, nombre);
			ps.setString(2, apellido);
			ps.setString(3, dni);
			ps.setString(4, email);
			ps.setString(5, numTelefono);
			ps.setString(6, cuentaBancaria);
			ps.setString(7, clienteActual.getUsuario());
			ps.setString(8, clienteActual.getContrasenya());

			clienteActual.setNombre(nombre);
			clienteActual.setApellido(apellido);
			clienteActual.setDni(dni);
			clienteActual.setEmail(email);
			clienteActual.setNumTelefono(numTelefono);
			clienteActual.setCuentaBancaria(cuentaBancaria);

			ps.executeUpdate();
			ps.close();

			Log.logger.log(Level.INFO, "Los datos se han actualizado correctamente.");
			return true;

		} catch (Exception e) {
			Log.logger.log(Level.SEVERE, "No se ha podido editar." + e.getStackTrace());
			return false;
		}
	}

	// m?todo borrar cliente

	public boolean borrarClienteBD(Cliente clienteActual) {
		try {
			String consulta = "DELETE FROM cliente WHERE usuario = ?;";

			PreparedStatement ps = conn.prepareStatement(consulta);
			ps.setString(1, clienteActual.getUsuario());

			ps.executeUpdate();
			ps.close();

			Log.logger.log(Level.INFO, "Se han borrado los datos correctamente.");
			return true;

		} catch (Exception e) {
			Log.logger.log(Level.SEVERE, "No se han podido borrar los datos." + e.getStackTrace());
			return false;
		}
	}

	// M?TODOS VIAJE

	// m?todo a?adir viaje

	public boolean anyadirViajeBD(String localizador, String origen, String destino, String fecha, int aforo,
			double precio, String imagen) {
		try {
			String consulta = "INSERT INTO viaje (localizador, origen, destino, fecha, aforo, precio, imagen) VALUES (?, ?, ?, ?, ?, ?, ?);";

			PreparedStatement ps = conn.prepareStatement(consulta);
			ps.setString(1, localizador);
			ps.setString(2, origen);
			ps.setString(3, destino);
			ps.setString(4, fecha);
			ps.setInt(5, aforo);
			ps.setDouble(6, precio);
			ps.setString(7, imagen);

			ps.executeUpdate();
			ps.close();

			Log.logger.log(Level.INFO, "Los datos se han introducido correctamente en la BD.");
			return true;

		} catch (Exception e) {
			Log.logger.log(Level.SEVERE, "No se ha podido insertar los datos en la BD." + e.getStackTrace());
			return false;
		}
	}
	
	// m?todo borrar viaje

	public boolean borrarViajeBD(Viaje viaje) {
		try {
			String consulta = "DELETE FROM viaje WHERE localizador = ?;";

			PreparedStatement ps = conn.prepareStatement(consulta);
			ps.setString(1, viaje.getLocalizador());

			ps.executeUpdate();
			ps.close();

			Log.logger.log(Level.INFO, "Se han eliminado los datos correctamente.");
			return true;

		} catch (Exception e) {
			Log.logger.log(Level.SEVERE, "No se han podido borrar los datos." + e.getStackTrace());
			return false;
		}
	}

	// m?todo borrar viajes

	public boolean borrarViajesBD() {
		try {
			String consulta = "DELETE FROM viaje;";
			Statement ps = conn.createStatement();

			ps.executeUpdate(consulta);
			ps.close();

			Log.logger.log(Level.INFO, "Se han eliminado los datos correctamente.");
			return true;

		} catch (Exception e) {
			Log.logger.log(Level.SEVERE, "No se han podido borrar los datos." + e.getStackTrace());
			return false;
		}
	}

	// m?todo get viajes

	public List<Viaje> getViajesBD() {
		try {
			ResultSet rs;

			String consulta = "SELECT * FROM viaje;";

			PreparedStatement ps = conn.prepareStatement(consulta);

			rs = ps.executeQuery();

			List<Viaje> listaViajes = new ArrayList<Viaje>();

			while (rs.next()) {

				Viaje viaje = new Viaje(rs.getString("localizador"), rs.getString("origen"), rs.getString("destino"),
						rs.getString("fecha"), rs.getInt("aforo"), rs.getDouble("precio"), rs.getString("imagen"));

				listaViajes.add(viaje);
			}

			Log.logger.log(Level.INFO, "Se han obtenido los viajes correctamente.");
			return listaViajes;

		} catch (Exception e) {
			Log.logger.log(Level.SEVERE, "No se han podido obtener los viajes." + e.getStackTrace());
		}
		return null;
	}

	// M?TODOS BILLETE

	// m?todo comprar billetes

	public boolean comprarBilletesBD(String tipo, String origen, String destino, String fechaIda,
			String fechaVuelta, int cantBilletes, int clase, int comida, int asientoIndividual, int seguroViaje,
			int mesa, int conUsuario) {
		try {
			List<Viaje> listaViajes = new ArrayList<Viaje>();
			listaViajes = getViajesBD();
			Viaje viajeIda = new Viaje();
			Viaje viajeVuelta = new Viaje();
			double precio = 0.0;

			switch (tipo) {
			case "Ida":
				for (Viaje viaje : listaViajes) {
					if (viaje.getOrigen().equals(origen) && viaje.getDestino().equals(destino)
							&& viaje.getFecha().equals(fechaIda)) {
						viajeIda = viaje;
					}
				}

				precio = Metodos.calcularPrecioBillete(tipo, viajeIda, viajeVuelta, clase, comida, asientoIndividual,
						seguroViaje, mesa, conUsuario);

				for (int i = 0; i < cantBilletes; i++) {
					String consulta = "INSERT INTO billete (usuarioCliente, localizadorViajeIda, localizadorViajeVuelta, precio, clase, comida, asientoIndividual, seguroViaje, mesa) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

					PreparedStatement ps = conn.prepareStatement(consulta);
					ps.setString(1, clienteActual.getUsuario());
					ps.setString(2, viajeIda.getLocalizador());
					ps.setString(3, "null");
					ps.setDouble(4, precio);
					ps.setInt(5, clase);
					ps.setInt(6, comida);
					ps.setInt(7, asientoIndividual);
					ps.setInt(8, seguroViaje);
					ps.setInt(9, mesa);

					ps.executeUpdate();
					ps.close();
				}
				
				// actualizar aforo viaje
				
				Metodos.actualizaAforoFichero(viajeIda, viajeVuelta, cantBilletes);
				Metodos.actualizaAforoFichero2(viajeIda, viajeVuelta, cantBilletes);
				
				String consulta2 = "UPDATE viaje SET aforo = ? WHERE localizador = ?;";
				
				PreparedStatement ps = conn.prepareStatement(consulta2);
				ps.setInt(1, viajeIda.getAforo() - cantBilletes);
				ps.setString(2, viajeIda.getLocalizador());

				ps.executeUpdate();
				ps.close();
				break;
			case "Ida y vuelta":
				for (Viaje viaje : listaViajes) {
					if (viaje.getOrigen().equals(origen) && viaje.getDestino().equals(destino)
							&& viaje.getFecha().equals(fechaIda)) {
						viajeIda = viaje;
					}
					if (viaje.getOrigen().equals(destino) && viaje.getDestino().equals(origen)
							&& viaje.getFecha().equals(fechaVuelta)) {
						viajeVuelta = viaje;
					}
				}

				precio = Metodos.calcularPrecioBillete(tipo, viajeIda, viajeVuelta, clase, comida, asientoIndividual,
						seguroViaje, mesa, conUsuario);

				for (int i = 0; i < cantBilletes; i++) {
					String consulta = "INSERT INTO billete (usuarioCliente, localizadorViajeIda, localizadorViajeVuelta, precio, clase, comida, asientoIndividual, seguroViaje, mesa) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

					ps = conn.prepareStatement(consulta);
					ps.setString(1, clienteActual.getUsuario());
					ps.setString(2, viajeIda.getLocalizador());
					ps.setString(3, viajeVuelta.getLocalizador());
					ps.setDouble(4, precio);
					ps.setInt(5, clase);
					ps.setInt(6, comida);
					ps.setInt(7, asientoIndividual);
					ps.setInt(8, seguroViaje);
					ps.setInt(9, mesa);

					ps.executeUpdate();
					ps.close();
				}
				
				// actualizar aforo viaje ida
				
				Metodos.actualizaAforoFichero(viajeIda, viajeVuelta, cantBilletes);
				Metodos.actualizaAforoFichero2(viajeIda, viajeVuelta, cantBilletes);
				
				consulta2 = "UPDATE viaje SET aforo = ? WHERE localizador = ?;";

				ps = conn.prepareStatement(consulta2);
				ps.setInt(1, viajeIda.getAforo() - cantBilletes);
				ps.setString(2, viajeIda.getLocalizador());

				ps.executeUpdate();
				ps.close();

				// actualizar aforo viaje vuelta

				consulta2 = "UPDATE viaje SET aforo = ? WHERE localizador = ?;";

				ps = conn.prepareStatement(consulta2);
				ps.setInt(1, viajeVuelta.getAforo() - cantBilletes);
				ps.setString(2, viajeVuelta.getLocalizador());

				ps.executeUpdate();
				ps.close();
				break;
			default:
				System.out.println("Ha habido un error.");
			}
			return true;

		} catch (Exception e) {
			Log.logger.log(Level.SEVERE, "No se ha podido insertar los datos." + e.getStackTrace());
			return false;
		}
	}

	// m?todo get billetes cliente

	public List<Billete> getBilletesClienteBD(Cliente clienteActual) {

		List<Billete> listaBilletesCliente = new ArrayList<Billete>(); // billetePrimera clase = 1, billeteSegunda clase = 2
		List<String> listaLocalizadoresIda = new ArrayList<String>();
		List<String> listaLocalizadoresVuelta = new ArrayList<String>();

		try {
			ResultSet rs, rs2, rs3;

			String consulta = "SELECT * FROM billete WHERE usuarioCliente = ?;";

			PreparedStatement ps = conn.prepareStatement(consulta);
			ps.setString(1, clienteActual.getUsuario());

			rs = ps.executeQuery();

			while (rs.next()) {
				Viaje viajeIda = new Viaje();
				Viaje viajeVuelta = new Viaje();

				if (rs.getInt("clase") == 1) {
					BilletePrimera billetePrimera = new BilletePrimera(clienteActual, viajeIda, viajeVuelta,
							rs.getDouble("precio"), rs.getInt("comida"), rs.getInt("asientoIndividual"));
					listaBilletesCliente.add(billetePrimera);
				} else if (rs.getInt("clase") == 2) {
					BilleteSegunda billeteSegunda = new BilleteSegunda(clienteActual, viajeIda, viajeVuelta,
							rs.getDouble("precio"), rs.getInt("seguroViaje"), rs.getInt("mesa"));
					listaBilletesCliente.add(billeteSegunda);
				}

				listaLocalizadoresIda.add(rs.getString("localizadorViajeIda"));
				listaLocalizadoresVuelta.add(rs.getString("localizadorViajeVuelta"));
			}
			ps.close();

			int i = 0;
			String consulta2 = "SELECT * FROM viaje WHERE localizador = ?;";

			for (String localizador : listaLocalizadoresIda) {
				try {
					ps = conn.prepareStatement(consulta2);
					ps.setString(1, localizador);

					rs2 = ps.executeQuery();

					while (rs2.next()) {
						Viaje viaje = new Viaje(rs2.getString("localizador"), rs2.getString("origen"),
								rs2.getString("destino"), rs2.getString("fecha"), rs2.getInt("aforo"),
								rs2.getDouble("precio"), rs2.getString("imagen"));
						listaBilletesCliente.get(i).setViajeIda(viaje);
					}
					ps.close();
					i++;

				} catch (Exception e) {
					Log.logger.log(Level.SEVERE, "Error." + e.getStackTrace());
				}
			}

			i = 0;
			for (String localizador : listaLocalizadoresVuelta) {
				try {
					ps = conn.prepareStatement(consulta2);
					ps.setString(1, localizador);

					rs3 = ps.executeQuery();

					while (rs3.next()) {
						Viaje viaje = new Viaje(rs3.getString("localizador"), rs3.getString("origen"),
								rs3.getString("destino"), rs3.getString("fecha"), rs3.getInt("aforo"),
								rs3.getDouble("precio"), rs3.getString("imagen"));
						listaBilletesCliente.get(i).setViajeVuelta(viaje);
					}
					ps.close();
					i++;

				} catch (Exception e) {
					Log.logger.log(Level.SEVERE, "Error." + e.getStackTrace());
				}
			}

			Log.logger.log(Level.INFO, "Se han obtenido los billetes correctamente.");

		} catch (Exception e) {
			Log.logger.log(Level.SEVERE, "No se han podido obtener los billetes." + e.getStackTrace());
		}
		return listaBilletesCliente;
	}
}