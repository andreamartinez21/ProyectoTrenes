package clases;

import java.util.Comparator;

public class Ordenar implements Comparator<Viaje> {

	@Override
	public int compare(Viaje v1, Viaje v2) {
		return v1.getDestino().compareTo(v2.getDestino());
	}
}
