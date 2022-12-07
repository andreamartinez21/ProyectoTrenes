package clases;

public class BilleteSegunda extends Billete {
	
	protected int seguroViaje; // 0 = no, 1 = sí
	protected int mesa;
	
	public BilleteSegunda(Cliente cliente, Viaje viajeIda, Viaje viajeVuelta, double precio, int seguroViaje, int mesa) {
		super(cliente, viajeIda, viajeVuelta, precio);
		this.seguroViaje = seguroViaje;
		this.mesa = mesa;
	}
	
	public BilleteSegunda() {
		super();
		this.seguroViaje = 0;
		this.mesa = 0;
	}

	public int getSeguroViaje() {
		return seguroViaje;
	}

	public void setSeguroViaje(int seguroViaje) {
		this.seguroViaje = seguroViaje;
	}

	public int getMesa() {
		return mesa;
	}

	public void setMesa(int mesa) {
		this.mesa = mesa;
	}

	@Override
	public String toString() {
		return "BilleteSegunda [seguroViaje=" + seguroViaje + ", mesa=" + mesa + ", id=" + id + ", cliente=" + cliente
				+ ", viajeIda=" + viajeIda + ", viajeVuelta=" + viajeVuelta + ", precio=" + precio + "]";
	}
}
