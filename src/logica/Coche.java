package logica;

import comunicacion.CocheDTO;
import excepciones.DAOExcepcion;

public class Coche {
	private String matricula; //CP
	private double kmsActuales;
	
	private Categoria tiene; //Tiene categoria
	private Sucursal esta_en; // Esta en

	
	
	public Coche(String matricula, int kmsActuales, Categoria tiene) {
		super();
		this.matricula = matricula;
		this.kmsActuales = kmsActuales;
		this.tiene = tiene;
	}
	
	public Coche (CocheDTO coche) throws DAOExcepcion {
		this.matricula = coche.getMatricula();
		this.kmsActuales = coche.getKmsActuales();
		this.tiene = AlquilerVehiculos.getSingleton().getCategoria(coche.getCategoria());
	}

	public String getMatricula() {
		return matricula;
	}
	
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public double getKmsActuales() {
		return kmsActuales;
	}
	public void setKmsActuales(int kmsActuales) {
		this.kmsActuales = kmsActuales;
	}
	public Categoria getTiene() {
		return tiene;
	}
	public void setTiene(Categoria tiene) {
		this.tiene = tiene;
	}
	public Sucursal getEsta_en() {
		return esta_en;
	}
	public void setEsta_en(Sucursal esta_en) {
		this.esta_en = esta_en;
	}

	@Override
	public String toString() {
		return "Coche [matricula=" + matricula + ", kmsActuales=" + kmsActuales
				+ ", tiene=" + tiene + ", esta_en=" + esta_en + "]";
	}
	
}
