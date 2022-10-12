package clases;

public class BilletePrimera extends Billete {

	protected boolean comida;
	protected boolean asientoIndividual;
		
	public BilletePrimera(Cliente cliente, Viaje viajeIda, Viaje viajeVuelta, double precio, String asiento,
			boolean comida, boolean asientoIndividual) {
		super(cliente, viajeIda, viajeVuelta, precio, asiento);
		this.comida = comida;
		this.asientoIndividual = asientoIndividual;
	}
	
	public BilletePrimera() {
		super();
		this.comida = false;
		this.asientoIndividual = false;
	}
	
	public boolean isComida() {
		return comida;
	}
	public void setComida(boolean comida) {
		this.comida = comida;
	}
	public boolean isAsientoIndividual() {
		return asientoIndividual;
	}
	public void setAsientoIndividual(boolean asientoIndividual) {
		this.asientoIndividual = asientoIndividual;
	}

	@Override
	public String toString() {
		return "BilletePrimera [comida=" + comida + ", asientoIndividual=" + asientoIndividual + ", id=" + id
				+ ", cliente=" + cliente + ", viajeIda=" + viajeIda + ", viajeVuelta=" + viajeVuelta + ", precio="
				+ precio + ", asiento=" + asiento + "]";
	}
}
