package logica;

import java.time.LocalDateTime;

import comunicacion.ClienteDTO;

public class Cliente {
	private String DNI; // CP
	private String nombreyApellidos;
	private String direccion;
	private String poblacion;
	private String codPostal;
	private LocalDateTime fechaCarnetConducir;
	// Tarjeta de crédito
	private String digitosTC;
	private int mesTC;
	private int anyoTC;
	private int cvcTC;
	private String tipoTC;

	public Cliente(String dNI, String nombreyApellidos, String direccion,
			String poblacion, String codPostal,
			LocalDateTime fechaCarnetConducir, String digitosTC, int mesTC,
			int anyoTC, int cvcTC, String tipoTC) {
		DNI = dNI;
		this.nombreyApellidos = nombreyApellidos;
		this.direccion = direccion;
		this.poblacion = poblacion;
		this.codPostal = codPostal;
		this.fechaCarnetConducir = fechaCarnetConducir;
		this.digitosTC = digitosTC;
		this.mesTC = mesTC;
		this.anyoTC = anyoTC;
		this.cvcTC = cvcTC;
		this.tipoTC = tipoTC;
	}

	public Cliente(ClienteDTO c) {
		this.DNI = c.getDni();
		this.nombreyApellidos = c.getNombreyApellidos();
		this.direccion = c.getDireccion();
		this.poblacion = c.getPoblacion();
		this.codPostal = c.getCodPostal();
		this.fechaCarnetConducir = c.getFechaCarnetConducir();
		this.digitosTC = c.getDigitosTC();
		this.mesTC = c.getMesTC();
		this.anyoTC = c.getAñoTC();
		this.cvcTC = c.getCvcTC();
		this.tipoTC = c.getTipoTC();
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
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

	public String getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
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

	public void setFechaCarnetConducir(LocalDateTime fechaCarnetConducir) {
		this.fechaCarnetConducir = fechaCarnetConducir;
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

	public int getAnyoTC() {
		return anyoTC;
	}

	public void setAnyoTC(int anyoTC) {
		this.anyoTC = anyoTC;
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

	@Override
	public String toString() {
		return "Cliente [DNI=" + DNI + ", nombreyApellidos=" + nombreyApellidos
				+ ", direccion=" + direccion + ", poblacion=" + poblacion
				+ ", codPostal=" + codPostal + ", fechaCarnetConducir="
				+ fechaCarnetConducir + ", digitosTC=" + digitosTC + ", mesTC="
				+ mesTC + ", anyoTC=" + anyoTC + ", cvcTC=" + cvcTC
				+ ", tipoTC=" + tipoTC + "]";
	}
	

}
