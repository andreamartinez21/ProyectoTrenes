package clases;

public abstract class Billete {

	protected String id;
    protected Cliente cliente;
    protected Viaje viajeIda;
    protected Viaje viajeVuelta;
    protected double precio;
    
	public Billete(Cliente cliente, Viaje viajeIda, Viaje viajeVuelta, double precio) {
		super();
		this.cliente = cliente;
		this.viajeIda = viajeIda;
		this.viajeVuelta = viajeVuelta;
		this.precio = precio;
	}
	
	public Billete() {
		this.id = "";
		this.cliente = new Cliente();
		this.viajeIda = new Viaje();
		this.viajeVuelta = new Viaje();
		this.precio = 0.0;
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

	@Override
	public String toString() {
		return "Billete [id=" + id + ", cliente=" + cliente + ", viajeIda=" + viajeIda + ", viajeVuelta=" + viajeVuelta
				+ ", precio=" + precio + "]";
	}   
}