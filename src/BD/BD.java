package BD;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;

import clases.Billete;
import clases.BilletePrimera;
import clases.BilleteSegunda;
import clases.Cliente;
import clases.Viaje;
import log.Log;

public class BD {
	private static Connection conn = null;
	public static Cliente clienteActual = new Cliente();
					
	public static Connection connect() {
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:database.db");
			Log.logger.log(Level.INFO, "Conexión con BD establecida.");
		} catch (ClassNotFoundException e) {
			Log.logger.log(Level.SEVERE, "Error cargando el driver de la BD");
		} catch (SQLException e) {
			System.out.println("Error al conectar con BD");
		}
		return conn;
	}

	public static void disconnect() {
		try {
			conn.close();
			System.out.println("Desconectado de la BD");
		} catch (SQLException e) {
			System.out.println("No se ha podido desconectar");
		}
	}
	
	// método register
	
	public static boolean registerBD(String nombre, String apellido, String usuario, String contrasenya, String dni, String email, String numTelefono, String cuentaBancaria) {
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
			
			System.out.println("Los datos se han introducido correctamente.");
			
			return true;
			
		} catch (Exception e) {
			System.out.println("No se ha podido insertar los datos.");
			return false;
		}
	}
	
	// método loginBD
	
	public static boolean loginBD(String usuario, String contrasenya) {
		
		try {
			ResultSet rs;
//			ResultSet rs2;
			
			String consulta = "SELECT * FROM cliente WHERE usuario = ? AND contrasenya = ?;";
//			String consulta2 = "SELECT * FROM billete WHERE usuarioCliente = ?;";
			
			PreparedStatement ps = conn.prepareStatement(consulta);
			ps.setString(1, usuario);
			ps.setString(2, contrasenya);
			
			rs = ps.executeQuery(); // en rs está lo que ha devuelto la bd
			
			while(rs.next()) {
				if(rs.getString("usuario").equals(usuario) && rs.getString("contrasenya").equals(contrasenya)) {
					
					String nombre = rs.getString("nombre");
					String apellido = rs.getString("apellido");
					String dni = rs.getString("dni");
					String email = rs.getString("email");
					String numTelefono = rs.getString("numTelefono");
					String cuentaBancaria = rs.getString("cuentaBancaria");
					List<Billete> listaBilletes = new ArrayList<>();
					
					clienteActual = new Cliente(usuario, contrasenya, nombre, apellido, dni, email, numTelefono, cuentaBancaria, listaBilletes);
					
					ps.close();
					
//					ps = conn.prepareStatement(consulta2);
//					ps.setString(1, usuario);
//					rs2 = ps.executeQuery(); // aquí estarian todos los billetes de ese usuario
					
//					while(rs2.next()) {
//						if(rs2.getString("usuarioCliente").equals(usuario)) {
//							
//							// coger los atributos de billetes y meterselos al constructor
//							String id = String.valueOf(rs2.getInt("id"));
//							
//							
//							int clase = rs2.getInt("clase");
//							
//							if(clase == 0) {
//								BilletePrimera billetePrimera = new BilletePrimera();	
//								billetePrimera.setId(id);
//								clienteActual.anyadirBillete(billetePrimera);
//							} else {
//								BilleteSegunda billeteSegunda = new BilleteSegunda();
//								billeteSegunda.setId(id);
//								clienteActual.anyadirBillete(billeteSegunda);
//							}
//						}
//						
//					}
					return true;
				}
			}
		} catch (Exception e) {
			System.out.println("No se ha podido comprobar.");
		}
		return false;
	}
	
	// método editar
	
	public static boolean editarBD(Cliente clienteActual, String nombre, String apellido, String dni, String email, String numTelefono, String cuentaBancaria) {
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
			
			ps.executeUpdate();
			ps.close();
			
			System.out.println("Los datos se han actualizado correctamente.");
			
			return true;
			
		} catch (Exception e) {
			System.out.println("No se ha podido editar.");
			
			return false;
		}
	}
	
	
	// método añadir viaje
	
	public static boolean anyadirViajeBD(String localizador, String origen, String destino, String fecha, int aforo, double precio) {
		try {
			String consulta = "INSERT INTO viaje (localizador, origen, destino, fecha, aforo, precio) VALUES (?, ?, ?, ?, ?, ?);";
		
			PreparedStatement ps = conn.prepareStatement(consulta);
			ps.setString(1, localizador);
			ps.setString(2, origen);
			ps.setString(3, destino);
			ps.setString(4, fecha);
			ps.setInt(5, aforo);
			ps.setDouble(6, precio);
			
			ps.executeUpdate();
			ps.close();
			
			System.out.println("Los datos se han introducido correctamente en la BD.");
			
			return true;
			
		} catch (Exception e) {
			System.out.println("No se ha podido insertar los datos en la BD.");
			return false;
		}
	}
	
	// método borrar viajes
	
	public static boolean borrarViajesBD() {
		try {
			String consulta = "DELETE * FROM viaje;";
			Statement ps = conn.createStatement();
			
			ps.executeUpdate(consulta);
			ps.close();
			
			System.out.println("Se han eliminado los datos correctamente.");
			
			return true;
			
		} catch (Exception e) {
			System.out.println("No se han podido borrar los datos.");
			return false;
		}
	}
	
	// método get viajes
	
	public static HashSet<Viaje> getViajesBD() {
		try {
			ResultSet rs;
			
			String consulta = "SELECT * FROM viaje;";
			
			PreparedStatement ps = conn.prepareStatement(consulta);
			
			rs = ps.executeQuery();
			
			// List<Viaje> listaViajes = new ArrayList<Viaje>();
			HashSet<Viaje> listaViajes = new HashSet<Viaje>(); 
			
			while(rs.next()) {
				
				Viaje viaje = new Viaje(rs.getString("localizador"), rs.getString("origen"), rs.getString("destino"), rs.getString("fecha"), rs.getInt("aforo"), rs.getDouble("precio"));
				
				listaViajes.add(viaje);				
			}
			
			return listaViajes;
			
		} catch (Exception e) {
			System.out.println("No se han podido obtener los viajes.");
		}
		return null;
	}
}