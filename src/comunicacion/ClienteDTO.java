package comunicacion;

import java.time.LocalDateTime;

import logica.Cliente;

public class ClienteDTO {
	private String dni;
	private String nombreyApellidos;
	private String direccion;
	private String poblacion;
	private String codPostal;
	private LocalDateTime fechaCarnetConducir;
	private String digitosTC;
	private int mesTC;
	private int a?oTC;
	private int cvcTC;
	private String tipoTC;

	public ClienteDTO(String dni, String nombreyApellidos, String direccion,
			String poblacion, String codPostal,
			LocalDateTime fechaCanetConducir, String digitosTC, int mesTC,
			int a?oTC, int cvcTC, String tipoTC) {
		super();
		this.dni = dni;
		this.nombreyApellidos = nombreyApellidos;
		this.direccion = direccion;
		this.poblacion = poblacion;
		this.codPostal = codPostal;
		this.fechaCarnetConducir = fechaCanetConducir;
		this.digitosTC = digitosTC;
		this.mesTC = mesTC;
		this.a?oTC = a?oTC;
		this.cvcTC = cvcTC;
		this.tipoTC = tipoTC;
	}

	public ClienteDTO(Cliente c) {
		super();
		this.dni = c.getDNI();
		this.nombreyApellidos = c.getNombreyApellidos();
		this.direccion = c.getDireccion();
		this.poblacion = c.getPoblacion();
		this.codPostal = c.getCodPostal();
		this.fechaCarnetConducir = c.getFechaCarnetConducir();
		this.digitosTC = c.getDigitosTC();
		this.mesTC = c.getMesTC();
		this.a?oTC = c.getAnyoTC();
		this.cvcTC = c.getCvcTC();
		this.tipoTC = c.getTipoTC();
	}

	public String getDni() {
		return dni;
	}

	public void setIdentificador(String dni) {
		this.dni = dni;
	}

	public String getNombreyApellidos() {
		return nombreyApellidos;
	}

	public void setNombreyApellidos(String nombreyApellidos) {
		this.nombreyApellidos = nombreyApellidos;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCodPostal() {
		return codPostal;
	}

	public void setCodPostal(String codPostal) {
		this.codPostal = codPostal;
	}

	public LocalDateTime getFechaCarnetConducir() {
		return fechaCarnetConducir;
	}

	public void setFechaCanetConducir(LocalDateTime fechaCanetConducir) {
		this.fechaCarnetConducir = fechaCanetConducir;
	}

	public String getDigitosTC() {
		return digitosTC;
	}

	public void setDigitosTC(String digitosTC) {
		this.digitosTC = digitosTC;
	}

	public int getMesTC() {
		return mesTC;
	}

	public void setMesTC(int mesTC) {
		this.mesTC = mesTC;
	}

	public int getA?oTC() {
		return a?oTC;
	}

	public void setA?oTC(int a?oTC) {
		this.a?oTC = a?oTC;
	}

	public int getCvcTC() {
		return cvcTC;
	}

	public void setCvcTC(int cvcTC) {
		this.cvcTC = cvcTC;
	}

	public String getTipoTC() {
		return tipoTC;
	}

	public void setTipoTC(String tipoTC) {
		this.tipoTC = tipoTC;
	}

	public String getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

}
