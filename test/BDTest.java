import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import BD.BD;
import clases.Billete;
import clases.Cliente;
import clases.Viaje;

class BDTest {

	BD bd = new BD();

	static Cliente c1;
	static List<Billete> listaBilletes;
	static Viaje v1;
	static List<Viaje> listaViajes;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
				
		c1 = new Cliente("iker4", "4321", "Iker", "Gonzalez", "847598374H", "iker@gmail.com", "64534545",
				"3464565675867867K", listaBilletes);

		v1 = new Viaje("09A", "Barcelona", "Dublin", "23-11-2022", 9, 89, "src/img/img9.png");
	}

//	@Test
//	void testLoginBD() {
//		BD.connect();
//		Cliente c2 = BD.loginBD(c1.getUsuario(), c2.getContrasenya());
//	}

	@Test
	void testGetViajesBD() {
		bd.connect();
		listaViajes = new ArrayList<Viaje>();
		listaViajes = bd.getViajesBD();

		for (Viaje viaje : listaViajes) {
			if (viaje.getLocalizador().equals(v1.getLocalizador())) {
				assertEquals(viaje.getLocalizador(), v1.getLocalizador());
			}
		}
		bd.disconnect();
	}
}
