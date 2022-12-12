package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import BD.BD;
import clases.Billete;
import clases.BilleteSegunda;
import clases.Cliente;
import clases.Viaje;
import properties.PropertiesClass;
import ventanas.Metodos;

class BDTest {

	BD bd = new BD();

	static Cliente c1;
	static Cliente c2;
	static List<Billete> listaBilletes;
	static List<Billete> listaBilletes2;
	static Viaje v1;
	static Viaje v2;
	static Viaje v3;
	static List<Viaje> listaViajes;
	static BilleteSegunda b1;
	static boolean respuesta;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		PropertiesClass.getProperties();
				
		c1 = new Cliente("iker4", "4321", "Iker", "Gonzalez", "847598374H", "iker@gmail.com", "64534545",
				"3464565675867867K", listaBilletes);
		c2 = new Cliente("laura3", "laura", "Laura", "Sanchez", "24234234K", "laura5@gmail.com", "436564574",
				"54736573673636856", listaBilletes2);

		v1 = new Viaje("09A", "Barcelona", "Dublin", "23-11-2022", 9, 89, "src/img/img9.png");
		v2 = new Viaje("234J", "Bilbao", "Londres", "20-12-22", 168, 45, "src/img/img1.png");
		v3 = new Viaje("748H", "Praga", "Viena", "11-11-2022", 250, 40, "src/img/img10.png");
		
		b1 = new BilleteSegunda(c2, v2, null, 45, 0, 0);
	}
	
	// test register

	@Test
	void testRegisterBD() {
		bd.connect();

		respuesta = bd.registerBD(c1.getNombre(), c1.getApellido(), c1.getUsuario(), c1.getContrasenya(), c1.getDni(),
				c1.getEmail(), c1.getNumTelefono(), c1.getCuentaBancaria());
		assertEquals(true, respuesta);

		bd.borrarClienteBD(c1);
		bd.disconnect();
	}

	// test login
	
	@Test
	void testLoginBD() {
		bd.connect();
		
		respuesta = bd.loginBD(c2.getUsuario(), c2.getContrasenya());
		assertEquals(true, respuesta);
		
		bd.disconnect();
	}
	
	// test editar cliente
	
	@Test
	void testEditarCliente() {
		bd.connect();
		
		respuesta = bd.editarClienteBD(c2, "x", "x", "x", "x", "x", "x");
		assertEquals(true, respuesta);

		bd.editarClienteBD(c2, "Laura", "Sanchez", "24234234K", "laura5@gmail.com", "436564574",
				"54736573673636856");
		bd.disconnect();
	}

	// test borrar cliente
	
	@Test
	void testBorrarClienteBD() {
		bd.connect();
		bd.registerBD(c1.getNombre(), c1.getApellido(), c1.getUsuario(), c1.getContrasenya(), c1.getDni(),
				c1.getEmail(), c1.getNumTelefono(), c1.getCuentaBancaria());
		
		respuesta = bd.borrarClienteBD(c1);
		assertEquals(true, respuesta);

		bd.disconnect();
	}

	// test añadir viaje
	
	@Test
	void testAnyadirViajeBD() {
		bd.connect();

		respuesta = bd.anyadirViajeBD(v3.getLocalizador(), v3.getOrigen(), v3.getDestino(), v3.getFecha(),
				v3.getAforo(), v3.getPrecio(), v3.getImagen());
		assertEquals(true, respuesta);

		bd.borrarViajeBD(v3);
		bd.disconnect();
	}
	
	// test borrar viaje
	
	@Test
	void testBorrarViajeBD() {
		bd.connect();
		bd.anyadirViajeBD(v3.getLocalizador(), v3.getOrigen(), v3.getDestino(), v3.getFecha(), v3.getAforo(),
				v3.getPrecio(), v3.getImagen());

		respuesta = bd.borrarViajeBD(v3);
		assertEquals(true, respuesta);

		bd.disconnect();
	}
	
	// test borrar viajes
	
	@Test
	void testBorrarViajesBD() {
		bd.connect();
		
		respuesta = bd.borrarViajesBD();
		assertEquals(true, respuesta);
		
		Metodos.leeFicheroViajes();
		bd.disconnect();
	}
	
	// test get viajes
	
	@Test
	void testGetViajesBD() {
		bd.connect();

		listaViajes = bd.getViajesBD();

		for (Viaje viaje : listaViajes) {
			if (viaje.getLocalizador().equals(v1.getLocalizador())) {
				assertEquals(viaje.getLocalizador(), v1.getLocalizador());
			}
		}
		
		bd.disconnect();
	}
	
	// test comprar billetes
	
	@Test
	void testComprarBilletesBD() {
		bd.connect();
		
		BD.clienteActual = c2;
		
		respuesta = bd.comprarBilletesBD("Ida y vuelta", "Bilbao", "Londres", "20-12-2022", "24-12-2022", 1, 0, 0, 0, 0, 0, 1);
		assertEquals(true, respuesta);
		
		bd.disconnect();
	}
	
	// test get billetes cliente
	
	@Test
	void testGetBilletesClienteBD() {
		bd.connect();
		
		listaBilletes2 = bd.getBilletesClienteBD(c2);
		
		for (Billete billete : listaBilletes2) {
			if (billete.getPrecio() == b1.getPrecio()) { // sin el if solo haría el assertEquals con el primero de la lista
				assertEquals(billete.getPrecio(), b1.getPrecio());
			}
		}
		
		bd.disconnect();
	}
}
