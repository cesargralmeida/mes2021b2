package logica;
import java.util.Hashtable;

import comunicacion.CategoriaDTO;
import excepciones.DAOExcepcion;


public class Categoria {
	private String nombre; //CP
	private double precioModIlimitada;
	private double precioModKms;
	private double precioKmModKms;
	private double precioSeguroTRiesgo;
	private double precioSeguroTerceros;
	
	private Categoria superior; // +superior
	
	private Hashtable<String,Coche> coleccionCoches = new Hashtable<String,Coche>();
	
	
	
	
	public Categoria(CategoriaDTO cat) throws DAOExcepcion {
		
		this.nombre = cat.getNombre();
		this.precioModIlimitada = cat.getPrecioModIlimitada();
		this.precioModKms = cat.getPrecioModKms();
		this.precioKmModKms = cat.getPrecioKMModKms();
		this.precioSeguroTRiesgo = cat.getPrecioSeguroTRiesgo();
		this.precioSeguroTerceros = cat.getPrecioSeguroTerceros();
		this.superior =null;		
	}

	public void addCoche(Coche c) {
		coleccionCoches.put(c.getMatricula(), c);
	}
	
	public Coche removeCoche(String matricula) {
		return coleccionCoches.remove(matricula);	
	}
	
	public Coche getCoche(String matricula) {
		return coleccionCoches.get(matricula);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecioModIlimitada() {
		return precioModIlimitada;
	}

	public void setPrecioModIlimitada(double precioModIlimitada) {
		this.precioModIlimitada = precioModIlimitada;
	}

	public double getPrecioModKms() {
		return precioModKms;
	}

	public void setPrecioModKms(double precioModKms) {
		this.precioModKms = precioModKms;
	}

	public double getPrecioKmModKms() {
		return precioKmModKms;
	}

	public void setPrecioKmModKms(double precioKmModKms) {
		this.precioKmModKms = precioKmModKms;
	}

	public double getPrecioSeguroTRiesgo() {
		return precioSeguroTRiesgo;
	}

	public void setPrecioSeguroTRiesgo(double precioSeguroTRiesgo) {
		this.precioSeguroTRiesgo = precioSeguroTRiesgo;
	}

	public double getPrecioSeguroTerceros() {
		return precioSeguroTerceros;
	}

	public void setPrecioSeguroTerceros(double precioSeguroTerceros) {
		this.precioSeguroTerceros = precioSeguroTerceros;
	}

	public Categoria getSuperior() {
		return superior;
	}

	public void setSuperior(Categoria catSuperior) {
		this.superior = catSuperior;
	}

	@Override
	public String toString() {
		String aux = "";
		
		if (superior != null) aux =  superior.getNombre();
		else aux = "no tiene";
		
		return "Categoria [nombre=" + nombre + ", precioModIlimitada="
				+ precioModIlimitada + ", precioModKms=" + precioModKms
				+ ", precioKmModKms=" + precioKmModKms
				+ ", \nprecioSeguroTRiesgo=" + precioSeguroTRiesgo
				+ ", precioSeguroTerceros=" + precioSeguroTerceros
				+ ", categoria superior=" + aux + ", coleccionCoches="
				+ coleccionCoches + "]";
	}
	
	
}
