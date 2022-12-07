package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import clases.Billete;
import clases.BilleteSegunda;
import clases.Cliente;
import clases.Viaje;

class BilleteTest {

	static BilleteSegunda b1;
	static BilleteSegunda b2;
	static Cliente c1;
	static Viaje v1;
	static List<Billete> listaBilletes;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {

		c1 = new Cliente("iker2", "1236", "Iker", "Ruiz", "1234567H", "iker2@gmail.com", "67634758", "9539475837459L",
				listaBilletes);
		v1 = new Viaje("123B", "Bilbao", "Londres", "2022-11-23", 100, 50.90, "src/img/img1.png");

		b1 = new BilleteSegunda();
		b2 = new BilleteSegunda(c1, v1, null, 55.90, 0, 1);
		b2.setId("001");
	}

	@Test
	void testGetId() {
		assertEquals("001", b2.getId());
	}

	@Test
	void testSetId() {
		String id = "001";
		b1.setId(id);
		assertEquals(b1.getId(), id);
	}

	@Test
	void testGetCliente() {
		assertEquals(c1, b2.getCliente());
	}

	@Test
	void testSetCliente() {
		Cliente cliente = c1;
		b1.setCliente(cliente);
		assertEquals(b1.getCliente(), cliente);
	}

	@Test
	void testGetViajeIda() {
		assertEquals(v1, b2.getViajeIda());
	}

	@Test
	void testSetViajeIda() {
		Viaje viajeIda = v1;
		b1.setViajeIda(viajeIda);
		assertEquals(b1.getViajeIda(), viajeIda);
	}

	@Test
	void testGetViajeVuelta() {
		assertEquals(null, b2.getViajeVuelta());
	}

	@Test
	void testSetViajeVuelta() {
		Viaje viajeVuelta = null;
		b1.setViajeVuelta(viajeVuelta);
		assertEquals(b1.getViajeVuelta(), viajeVuelta);
	}

	@Test
	void testGetPrecio() {
		assertEquals(55.90, b2.getPrecio());
	}

	@Test
	void testSetPrecio() {
		double precio = 55.90;
		b1.setPrecio(precio);
		assertEquals(b1.getPrecio(), precio);
	}
}
