package comunicacion;

import logica.Empleado;

public class EmpleadoDTO {
	private String dni;
	private String nombreyApellidos;
	private boolean administrador;

	public EmpleadoDTO(String dni, String nombreyApellidos, boolean administrador) {
		super();
		this.dni = dni;
		this.nombreyApellidos = nombreyApellidos;
		this.administrador = administrador;
	}

	public EmpleadoDTO(Empleado e) {
		super();
		this.dni = e.getDNI();
		this.nombreyApellidos = e.getNombre();
		this.administrador = e.getAdministrador();
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombreyApellidos() {
		return nombreyApellidos;
	}

	public void setNombreyApellidos(String nombreyApellidos) {
		this.nombreyApellidos = nombreyApellidos;
	}

	public boolean getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Boolean administrador) {
		this.administrador = administrador;
	}


}
