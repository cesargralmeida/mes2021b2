package comunicacion;

import java.time.LocalDateTime;

import logica.Coche;
import logica.Devolucion;
import logica.Empleado;
import logica.Entrega;
import logica.Reserva;

public class EntregaDTO {
	private int id;
	private LocalDateTime fecha;
	private String tipoSeguro;
	private double kms;
	private double combustible;
	private String tiene_asociado;
	private String es_realizada_por;
	
	public EntregaDTO(int id, LocalDateTime fecha, String tipoSeguro,
			double kms, double combustible, String tiene_asociado,
			String es_realizada_por) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.tipoSeguro = tipoSeguro;
		this.kms = kms;
		this.combustible = combustible;
		this.tiene_asociado = tiene_asociado;
		this.es_realizada_por = es_realizada_por;
	}
	
	public EntregaDTO(Entrega e){		
		this.id = e.getId();
		this.fecha =e.getFecha();
		this.tipoSeguro =e.getTipoSeguro();
		this.kms = e.getKms();
		this.combustible = e.getCombustible();
		this.tiene_asociado = e.getTiene_asociado().getMatricula();
		this.es_realizada_por = e.getEs_realizada_por().getDNI();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public String getTipoSeguro() {
		return tipoSeguro;
	}

	public void setTipoSeguro(String tipoSeguro) {
		this.tipoSeguro = tipoSeguro;
	}

	public double getKms() {
		return kms;
	}

	public void setKms(double kms) {
		this.kms = kms;
	}

	public double getCombustible() {
		return combustible;
	}

	public void setCombustible(double combustible) {
		this.combustible = combustible;
	}

	public String getTiene_asociado() {
		return tiene_asociado;
	}

	public void setTiene_asociado(String tiene_asociado) {
		this.tiene_asociado = tiene_asociado;
	}

	public String getEs_realizada_por() {
		return es_realizada_por;
	}

	public void setEs_realizada_por(String es_realizada_por) {
		this.es_realizada_por = es_realizada_por;
	}
	
}
