package comunicacion;

import logica.Coche;

public class CocheDTO {
	private String matricula;
	private double kmsActuales;
	int esta_en;
	String categoria;

	public CocheDTO(String matricula, double kmsActuales,int esta_en,String categoria) {
		super();
		this.matricula = matricula;
		this.kmsActuales = kmsActuales;
		this.esta_en=esta_en;
		this.categoria=categoria;
	}

	public CocheDTO(Coche c) {
		super();
		this.matricula=c.getMatricula();
		this.kmsActuales=c.getKmsActuales();
		this.esta_en=c.getEsta_en().getIdSucursal();
		this.categoria=c.getTiene().getNombre();
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


	public void setKmsActuales(double kmsActuales) {
		this.kmsActuales = kmsActuales;
	}



	public int getEsta_en() {
		return esta_en;
	}


	public void setEsta_en(int esta_en) {
		this.esta_en = esta_en;
	}


	public String getCategoria() {
		return categoria;
	}


	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
}
