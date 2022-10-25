package clases;

public class BilletePrimera extends Billete {

	protected int comida; // poner boolean
	protected int asientoIndividual; // poner boolean
		
	public BilletePrimera(Cliente cliente, Viaje viajeIda, Viaje viajeVuelta, double precio, String asiento,
			int comida, int asientoIndividual) {
		super(cliente, viajeIda, viajeVuelta, precio, asiento);
		this.comida = comida;
		this.asientoIndividual = asientoIndividual;
	}
	
	public BilletePrimera() {
		super();
		this.comida = 0;
		this.asientoIndividual = 0;
	}
	
	public int isComida() {
		return comida;
	}
	public void setComida(int comida) {
		this.comida = comida;
	}
	public int isAsientoIndividual() {
		return asientoIndividual;
	}
	public void setAsientoIndividual(int asientoIndividual) {
		this.asientoIndividual = asientoIndividual;
	}

	@Override
	public String toString() {
		return "BilletePrimera [comida=" + comida + ", asientoIndividual=" + asientoIndividual + ", id=" + id
				+ ", cliente=" + cliente + ", viajeIda=" + viajeIda + ", viajeVuelta=" + viajeVuelta + ", precio="
				+ precio + ", asiento=" + asiento + "]";
	}
}
