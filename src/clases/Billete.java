package clases;

public abstract class Billete {

	protected String id;
    protected Cliente cliente;
    protected Viaje viajeIda;
    protected Viaje viajeVuelta;
    protected double precio;
    protected String asiento;
    
	public Billete(Cliente cliente, Viaje viajeIda, Viaje viajeVuelta, double precio, String asiento) {
		super();
		this.cliente = cliente;
		this.viajeIda = viajeIda;
		this.viajeVuelta = viajeVuelta;
		this.precio = precio;
		this.asiento = asiento;
	}
	
	public Billete() {
		this.id = "";
		this.cliente = new Cliente();
		this.viajeIda = new Viaje();
		this.viajeVuelta = new Viaje();
		this.precio = 0.0;
		this.asiento = "";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Viaje getViajeIda() {
		return viajeIda;
	}

	public void setViajeIda(Viaje viajeIda) {
		this.viajeIda = viajeIda;
	}

	public Viaje getViajeVuelta() {
		return viajeVuelta;
	}

	public void setViajeVuelta(Viaje viajeVuelta) {
		this.viajeVuelta = viajeVuelta;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getAsiento() {
		return asiento;
	}

	public void setAsiento(String asiento) {
		this.asiento = asiento;
	}

	@Override
	public String toString() {
		return "Billete [id=" + id + ", cliente=" + cliente + ", viajeIda=" + viajeIda + ", viajeVuelta=" + viajeVuelta
				+ ", precio=" + precio + ", asiento=" + asiento + "]";
	}   
}