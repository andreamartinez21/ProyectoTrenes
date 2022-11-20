//package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import clases.Viaje;

class ViajeTest {
	
	static Viaje v1;
	static Viaje v2;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		v1 = new Viaje();
		v2 = new Viaje("123B", "Bilbao", "Londres", "2022-11-23", 100, 50.90, "src/img/img1.png");
	}

	@Test
	void testGetLocalizador() {
		assertEquals("123B", v2.getLocalizador());
	}
	
	@Test
	void testSetLocalizador() {
		String localizador = "123B";
		v1.setLocalizador(localizador);
		assertEquals(v1.getLocalizador(), localizador);
	}
	
	@Test
	void testGetOrigen() {
		assertEquals("Bilbao", v2.getOrigen());
	}

	@Test
	void testSetOrigen() {
		String origen = "Bilbao";
		v1.setOrigen(origen);
		assertEquals(v1.getOrigen(), origen);
	}
	
	@Test
	void testGetDestino() {
		assertEquals("Londres", v2.getDestino());
	}
	
	@Test
	void testSetDestino() {
		String destino = "Londres";
		v1.setDestino(destino);
		assertEquals(v1.getDestino(), destino);
	}
	
	@Test
	void testGetFecha() {
		assertEquals("2022-11-23", v2.getFecha());
	}
	
	@Test
	void testSetFecha() {
		String fecha = "2022-11-23";
		v1.setFecha(fecha);
		assertEquals(v1.getFecha(), fecha);
	}
	
	@Test
	void testGetAforo() {
		assertEquals(100, v2.getAforo());
	}
	
	@Test
	void testSetAforo() {
		int aforo = 100;
		v1.setAforo(aforo);
		assertEquals(v1.getAforo(), aforo);
	}
	
	@Test
	void testGetPrecio() {
		assertEquals(50.90, v2.getPrecio());
	}
	
	@Test
	void testSetPrecio() {
		double precio = 50.90;
		v1.setPrecio(precio);
		assertEquals(v1.getPrecio(), precio);
	}
	
	@Test
	void testGetImagen() {
		assertEquals("src/img/img1.png", v2.getImagen());
	}
	
	@Test
	void testSetImagen() {
		String imagen = "src/img/img1.png";
		v1.setImagen(imagen);
		assertEquals(v1.getImagen(), imagen);
	}
}
