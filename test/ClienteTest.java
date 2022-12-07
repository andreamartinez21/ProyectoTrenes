//package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import clases.Billete;
import clases.BilletePrimera;
import clases.BilleteSegunda;
import clases.Cliente;
import clases.Viaje;

class ClienteTest {

	static Cliente c1;
	static Cliente c2;
	static BilletePrimera b1;
	static BilleteSegunda b2;
	static List<Billete> listaBilletes;
	static Viaje viajeIda;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {

		listaBilletes = new ArrayList<Billete>();

		viajeIda = new Viaje("123B", "Bilbao", "Londres", "2022-11-23", 100, 50.90, "src/img/img1.png");

		c1 = new Cliente();
		c2 = new Cliente("iker2", "1236", "Iker", "Ruiz", "1234567H", "iker2@gmail.com", "67634758", "9539475837459L",
				listaBilletes);

		b1 = new BilletePrimera(c2, viajeIda, null, 60.90, 1, 1);
		b2 = new BilleteSegunda(c2, viajeIda, null, 55.90, 0, 1);

		listaBilletes.add(b1);
		listaBilletes.add(b2);

		c2.setListaBilletes(listaBilletes);
	}

	@Test
	void testGetUsuario() {
		assertEquals("iker2", c2.getUsuario());
	}

	@Test
	void testSetUsuario() {
		String usuario = "iker2";
		c1.setUsuario(usuario);
		assertEquals(c1.getUsuario(), usuario);
	}

	@Test
	void testGetContrasenya() {
		assertEquals("1236", c2.getContrasenya());
	}

	@Test
	void testSetContrasenya() {
		String contrasenya = "1236";
		c1.setContrasenya(contrasenya);
		assertEquals(c1.getContrasenya(), contrasenya);
	}

	@Test
	void testGetNombre() {
		assertEquals("Iker", c2.getNombre());
	}

	@Test
	void testSetNombre() {
		String nombre = "Iker";
		c1.setNombre(nombre);
		assertEquals(c1.getNombre(), nombre);
	}

	@Test
	void testGetApellido() {
		assertEquals("Ruiz", c2.getApellido());
	}

	@Test
	void testSetApellido() {
		String apellido = "Ruiz";
		c1.setApellido(apellido);
		assertEquals(c1.getApellido(), apellido);
	}

	@Test
	void testGetDni() {
		assertEquals("1234567H", c2.getDni());
	}

	@Test
	void testSetDni() {
		String dni = "1234567H";
		c1.setDni(dni);
		assertEquals(c1.getDni(), dni);
	}

	@Test
	void testGetEmail() {
		assertEquals("iker2@gmail.com", c2.getEmail());
	}

	@Test
	void testSetEmail() {
		String email = "iker2@gmail.com";
		c1.setEmail(email);
		assertEquals(c1.getEmail(), email);
	}

	@Test
	void testGetNumTelefono() {
		assertEquals("67634758", c2.getNumTelefono());
	}

	@Test
	void testSetNumTelefono() {
		String numTelefono = "67634758";
		c1.setNumTelefono(numTelefono);
		assertEquals(c1.getNumTelefono(), numTelefono);
	}

	@Test
	void testGetCuentaBancaria() {
		assertEquals("9539475837459L", c2.getCuentaBancaria());
	}

	@Test
	void testSetCuentaBancaria() {
		String cuentaBancaria = "9539475837459L";
		c1.setCuentaBancaria(cuentaBancaria);
		assertEquals(c1.getCuentaBancaria(), cuentaBancaria);
	}

	@Test
	void testGetListaBilletes() {
		assertEquals(listaBilletes, c2.getListaBilletes());
	}

	@Test
	void testSetListaBilletes() {
		c1.setListaBilletes(listaBilletes);
		assertEquals(c1.getListaBilletes(), listaBilletes);
	}

	@Test
	void testAnyadirBillete() {
		Billete b3 = new BilletePrimera(c2, viajeIda, null, 70.90, 0, 1);
		c2.anyadirBillete(b3);
		int longitud = c2.getListaBilletes().size();
		assertEquals(c2.getListaBilletes().get(longitud - 1), b3);
	}
}
