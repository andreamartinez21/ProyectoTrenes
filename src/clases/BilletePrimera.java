package clases;

public class BilletePrimera extends Billete {

	protected int comida; // 0 = no, 1 = sí
	protected int asientoIndividual;
		
	public BilletePrimera(Cliente cliente, Viaje viajeIda, Viaje viajeVuelta, double precio, int comida, int asientoIndividual) {
		super(cliente, viajeIda, viajeVuelta, precio);
		this.comida = comida;
		this.asientoIndividual = asientoIndividual;
	}
	
	public BilletePrimera() {
		super();
		this.comida = 0;
		this.asientoIndividual = 0;
	}
	
	public int getComida() {
		return comida;
	}
	public void setComida(int comida) {
		this.comida = comida;
	}
	public int getAsientoIndividual() {
		return asientoIndividual;
	}
	public void setAsientoIndividual(int asientoIndividual) {
		this.asientoIndividual = asientoIndividual;
	}

	@Override
	public String toString() {
		return "BilletePrimera [comida=" + comida + ", asientoIndividual=" + asientoIndividual + ", id=" + id
				+ ", cliente=" + cliente + ", viajeIda=" + viajeIda + ", viajeVuelta=" + viajeVuelta + ", precio="
				+ precio + "]";
	}
}
