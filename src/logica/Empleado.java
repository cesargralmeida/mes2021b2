package logica;

import comunicacion.EmpleadoDTO;

public class Empleado {
	private String DNI; //CP
	private String nombre;
	private Sucursal trabaja;
	private boolean administrador;
	
	public Empleado(String dNI, String nombre, boolean administrador,Sucursal trabaja) {
		super();
		DNI = dNI;
		this.nombre = nombre;
		this.administrador = administrador;
		this.trabaja=trabaja;
	}
	
	public Empleado(EmpleadoDTO e) {
		super();
		DNI = e.getDni();
		this.nombre = e.getNombreyApellidos();
		this.administrador = e.getAdministrador();
		this.trabaja=null;
	}
	
	public String getDNI() {
		return DNI;
	}
	public void setDNI(String dNI) {
		DNI = dNI;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public boolean getAdministrador() {
		return administrador;
	}
	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}
	
	
	
}
