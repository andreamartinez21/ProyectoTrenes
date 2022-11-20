package clases;

public class Viaje {

    protected String localizador;
    protected String origen;
    protected String destino;
    protected String fecha; // jcalendar
    protected int aforo;
    protected double precio;
    protected String imagen;
    
	public Viaje(String localizador, String origen, String destino, String fecha, int aforo, double precio, String imagen) {
		super();
		this.localizador = localizador;
		this.origen = origen;
		this.destino = destino;
		this.fecha = fecha;
		this.aforo = aforo;
		this.precio = precio;		this.imagen = imagen;
	}
	
	public Viaje() {
		super();
		this.localizador = "";
		this.origen = "";
		this.destino = "";
		this.fecha = "";
		this.aforo = 0;
		this.precio = 0.0;
		this.imagen = "";
	}

	public String getLocalizador() {
		return localizador;
	}

	public void setLocalizador(String localizador) {
		this.localizador = localizador;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public int getAforo() {
		return aforo;
	}

	public void setAforo(int aforo) {
		this.aforo = aforo;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	@Override
	public String toString() {
		return "Viaje [localizador=" + localizador + ", origen=" + origen + ", destino=" + destino + ", fecha=" + fecha
				+ ", aforo=" + aforo + ", precio=" + precio + "]";
	}
}