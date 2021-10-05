package logica;

import java.time.LocalDateTime;
import java.util.Hashtable;

public class Devolucion {

	private LocalDateTime fecha;
	private double totalACobrar;
	private boolean cobrado;
	private double kms;
	private double combustible;
	private Entrega asociado_a;
	private Empleado realizada_por;
	private Hashtable<String,Danyo> coleccionDanyos = new Hashtable<String,Danyo>();
	
	public Empleado getRealizada_por() {
		return realizada_por;
	}

	public void setRealizada_por(Empleado realizada_por) {
		this.realizada_por = realizada_por;
	}

	
	
	public void addDanyo(Danyo c) {
		coleccionDanyos.put(c.getIdDanyo(), c);
	}
	
	public Danyo removeDanyo(String idDanyo) {
		return coleccionDanyos.remove(idDanyo);	
	}
	
	public Danyo getDanyo(String idDanyo) {
		return coleccionDanyos.get(idDanyo);
	}
	
	public LocalDateTime getFecha() {
		return fecha;
	}
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	public double getTotalACobrar() {
		return totalACobrar;
	}
	public void setTotalACobrar(double totalACobrar) {
		this.totalACobrar = totalACobrar;
	}
	public boolean isCobrado() {
		return cobrado;
	}
	public void setCobrado(boolean cobrado) {
		this.cobrado = cobrado;
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

	public Entrega getAsociado_a() {
		return asociado_a;
	}

	public void setAsociado_a(Entrega asociado_a) {
		this.asociado_a = asociado_a;
	}
	
	
	
}
