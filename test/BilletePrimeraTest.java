//package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import clases.Billete;
import clases.BilletePrimera;
import clases.Cliente;
import clases.Viaje;

class BilletePrimeraTest {
	
	static BilletePrimera b1;
	static BilletePrimera b2;
	static Cliente c1;
	static Viaje viajeIda;
	static List<Billete> listaBilletes;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		c1 = new Cliente("iker2", "1236", "Iker", "Ruiz", "1234567H", "iker2@gmail.com", "67634758", "9539475837459L", listaBilletes);
		viajeIda = new Viaje("123B", "Bilbao", "Londres", "2022-11-23", 100, 50.90);
		
		b1 = new BilletePrimera();
		b2 = new BilletePrimera(c1, viajeIda, null, 55.90, "20C", 0, 1);
	}

	@Test
	void testGetComida() {
		assertEquals(0, b2.getComida());
	}
	
	@Test
	void testSetComida() {
		int comida = 0;
		b1.setComida(comida);
		assertEquals(b1.getComida(), comida);
	}
	
	@Test
	void testGetAsientoIndividual() {
		assertEquals(1, b2.getAsientoIndividual());
	}
	
	@Test
	void testSetAsientoIndividual() {
		int asientoIndividual = 1;
		b1.setAsientoIndividual(asientoIndividual);
		assertEquals(b1.getAsientoIndividual(), asientoIndividual);
	}
}
