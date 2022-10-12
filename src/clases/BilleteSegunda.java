package clases;

public class BilleteSegunda extends Billete {
	
	boolean seguroViaje;
	boolean mesa;
	
	public BilleteSegunda(Cliente cliente, Viaje viajeIda, Viaje viajeVuelta, double precio, String asiento,
			boolean seguroViaje, boolean mesa) {
		super(cliente, viajeIda, viajeVuelta, precio, asiento);
		this.seguroViaje = seguroViaje;
		this.mesa = mesa;
	}
	
	public BilleteSegunda() {
		super();
		this.seguroViaje = false;
		this.mesa = false;
	}

	public boolean isSeguroViaje() {
		return seguroViaje;
	}

	public void setSeguroViaje(boolean seguroViaje) {
		this.seguroViaje = seguroViaje;
	}

	public boolean isMesa() {
		return mesa;
	}

	public void setMesa(boolean mesa) {
		this.mesa = mesa;
	}

	@Override
	public String toString() {
		return "BilleteSegunda [seguroViaje=" + seguroViaje + ", mesa=" + mesa + ", id=" + id + ", cliente=" + cliente
				+ ", viajeIda=" + viajeIda + ", viajeVuelta=" + viajeVuelta + ", precio=" + precio + ", asiento="
				+ asiento + "]";
	}
}
