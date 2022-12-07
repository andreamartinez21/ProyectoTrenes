package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import clases.Billete;
import clases.BilleteSegunda;
import clases.Cliente;
import clases.Viaje;

class BilleteSegundaTest {

	static BilleteSegunda b1;
	static BilleteSegunda b2;
	static Cliente c1;
	static Viaje viajeIda;
	static List<Billete> listaBilletes;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {

		c1 = new Cliente("iker2", "1236", "Iker", "Ruiz", "1234567H", "iker2@gmail.com", "67634758", "9539475837459L",
				listaBilletes);
		viajeIda = new Viaje("123B", "Bilbao", "Londres", "2022-11-23", 100, 50.90, "src/img/img1.png");

		b1 = new BilleteSegunda();
		b2 = new BilleteSegunda(c1, viajeIda, null, 55.90, 0, 1);
	}

	@Test
	void testGetSeguroViaje() {
		assertEquals(0, b2.getSeguroViaje());
	}

	@Test
	void testSetSeguroViaje() {
		int seguroViaje = 0;
		b1.setSeguroViaje(seguroViaje);
		assertEquals(b1.getSeguroViaje(), seguroViaje);
	}

	@Test
	void testGetMesa() {
		assertEquals(1, b2.getMesa());
	}

	@Test
	void testSetMesa() {
		int mesa = 1;
		b1.setMesa(mesa);
		assertEquals(b1.getMesa(), mesa);
	}
}
