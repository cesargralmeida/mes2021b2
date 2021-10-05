package logica;

import java.time.LocalDateTime;
import java.util.Hashtable;

import comunicacion.DAL;
import comunicacion.EntregaDTO;
import excepciones.DAOExcepcion;

public class Entrega {
	
	private int id;
	private LocalDateTime fecha;
	private String tipoSeguro;
	private double kms;
	private double combustible;
	private Coche tiene_asociado;
	private Devolucion asociado_a_devolucion;
	private Reserva asociado_a_reserva;
	private Empleado es_realizada_por;
		
	private Hashtable<String,Danyo> coleccionDanyos = new Hashtable<String,Danyo>();
	
	public Entrega(int id, LocalDateTime fecha, String tipoSeguro, double kms,
			double combustible, Coche tiene_asociado, Reserva asociado_a_reserva,
			Empleado es_realizada_por) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.tipoSeguro = tipoSeguro;
		this.kms = kms;
		this.combustible = combustible;
		this.tiene_asociado = tiene_asociado;
		this.asociado_a_reserva = asociado_a_reserva;
		this.es_realizada_por = es_realizada_por;

	}

	public Entrega(EntregaDTO e) throws DAOExcepcion {
		this.id = e.getId();
		this.fecha = e.getFecha();
		this.tipoSeguro = e.getTipoSeguro();
		this.kms = e.getKms();
		this.combustible = e.getCombustible();
		this.tiene_asociado = new Coche(DAL.getSingleton().getCoche(e.getTiene_asociado()));
		this.asociado_a_reserva = new Reserva(DAL.getSingleton().getReserva(e.getId()));
		this.es_realizada_por = new Empleado(DAL.getSingleton().getEmpleado(e.getEs_realizada_por()));
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

	public int getId(){
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

	public Coche getTiene_asociado() {
		return tiene_asociado;
	}

	public void setTiene_asociado(Coche tiene_asociado) {
		this.tiene_asociado = tiene_asociado;
	}

	public Devolucion getAsociado_a_devolucion() {
		return asociado_a_devolucion;
	}

	public void setAsociado_a_devolucion(Devolucion asociado_a_devolucion) {
		this.asociado_a_devolucion = asociado_a_devolucion;
	}

	public Reserva getAsociado_a_reserva() {
		return asociado_a_reserva;
	}

	public void setAsociado_a_reserva(Reserva asociado_a_reserva) {
		this.asociado_a_reserva = asociado_a_reserva;
	}

	public Empleado getEs_realizada_por() {
		return es_realizada_por;
	}

	public void setEs_realizada_por(Empleado es_realizada_por) {
		this.es_realizada_por = es_realizada_por;
	}

	public Hashtable<String, Danyo> getColeccionDanyos() {
		return coleccionDanyos;
	}

	public void setColeccionDanyos(Hashtable<String, Danyo> coleccionDanyos) {
		this.coleccionDanyos = coleccionDanyos;
	}
	
	

}
