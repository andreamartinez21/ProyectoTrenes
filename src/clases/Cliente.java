package clases;

import java.util.List;

public class Cliente {

	protected String usuario;
    protected String contrasenya;
    protected String nombre;
    protected String apellido;
    protected String dni; // comprobar si es correcto el dni
    protected String email;
    protected String numTelefono;
    protected String cuentaBancaria;
    protected List<Billete> listaBilletes;
    
	public Cliente(String usuario, String contrasenya, String nombre, String apellido, String dni, String email,
			String numTelefono, String cuentaBancaria, List<Billete> listaBilletes) {
		super();
		this.usuario = usuario;
		this.contrasenya = contrasenya;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.email = email;
		this.numTelefono = numTelefono;
		this.cuentaBancaria = cuentaBancaria;
		this.listaBilletes = listaBilletes;
	}
	
	public Cliente() {
		this.usuario = "";
		this.contrasenya = "";
		this.nombre = "";
		this.apellido = "";
		this.dni = "";
		this.email = "";
		this.numTelefono = "";
		this.cuentaBancaria = "";
		this.listaBilletes = null;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasenya() {
		return contrasenya;
	}

	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumTelefono() {
		return numTelefono;
	}

	public void setNumTelefono(String numTelefono) {
		this.numTelefono = numTelefono;
	}

	public String getCuentaBancaria() {
		return cuentaBancaria;
	}

	public void setCuentaBancaria(String cuentaBancaria) {
		this.cuentaBancaria = cuentaBancaria;
	}

	public List<Billete> getListaBilletes() {
		return listaBilletes;
	}

	public void setListaBilletes(List<Billete> listaBilletes) {
		this.listaBilletes = listaBilletes;
	}
	
	public void anyadirBillete(Billete billete) {
		this.listaBilletes.add(billete);
	}

	@Override
	public String toString() {
		return "Cliente [usuario=" + usuario + ", contrasenya=" + contrasenya + ", nombre=" + nombre + ", apellido="
				+ apellido + ", dni=" + dni + ", email=" + email + ", numTelefono=" + numTelefono + ", cuentaBancaria="
				+ cuentaBancaria + ", listaBilletes=" + listaBilletes + "]";
	}
}
