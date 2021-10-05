package comunicacion;

import java.time.LocalDateTime;

import logica.Reserva;

public class ReservaDTO {
	private int id;
	private LocalDateTime fechaRecogida;
	private LocalDateTime fechaDevolucion;
	private String modalidadAlquiler;
	private String dniCliente;
	private String nombreCategoria;
	private int idSucursalRecogida;
	private int idSucursalDevolucion;
	
	public ReservaDTO(int id, LocalDateTime fechaRecogida,
			LocalDateTime fechaDevolucion, String modalidadAlquiler,
			String dniCliente, String nombreCategoria, int idSucursalRecogida,
			int idSucursalDevolucion) {
		super();
		this.id = id;
		this.fechaRecogida = fechaRecogida;
		this.fechaDevolucion = fechaDevolucion;
		this.modalidadAlquiler = modalidadAlquiler;
		this.dniCliente = dniCliente;
		this.nombreCategoria = nombreCategoria;
		this.idSucursalRecogida = idSucursalRecogida;
		this.idSucursalDevolucion = idSucursalDevolucion;
	}
	
	public ReservaDTO(Reserva res) {
		super();
		this.id = res.getIdReserva();
		this.fechaRecogida = res.getFechaRecogida();
		this.fechaDevolucion = res.getFechaDevolucion();
		this.modalidadAlquiler = res.getModalidadAlquiler();
		this.dniCliente = res.getRealizada_por().getDNI();
		this.nombreCategoria = res.getTiene_asociada().getNombre();
		this.idSucursalRecogida = res.getLugar_recogida().getIdSucursal();
		this.idSucursalDevolucion = res.getLugar_devolucion().getIdSucursal();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getFechaRecogida() {
		return fechaRecogida;
	}

	public void setFechaRecogida(LocalDateTime fechaRecogida) {
		this.fechaRecogida = fechaRecogida;
	}

	public LocalDateTime getFechaDevolucion() {
		return fechaDevolucion;
	}

	public void setFechaDevolucion(LocalDateTime fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}

	public String getModalidadAlquiler() {
		return modalidadAlquiler;
	}

	public void setModalidadAlquiler(String modalidadAlquiler) {
		this.modalidadAlquiler = modalidadAlquiler;
	}

	public String getDniCliente() {
		return dniCliente;
	}

	public void setDniCliente(String dniCliente) {
		this.dniCliente = dniCliente;
	}

	public String getNombreCategoria() {
		return nombreCategoria;
	}

	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}

	public int getIdSucursalRecogida() {
		return idSucursalRecogida;
	}

	public void setIdSucursalRecogida(int idSucursalRecogida) {
		this.idSucursalRecogida = idSucursalRecogida;
	}

	public int getIdSucursalDevolucion() {
		return idSucursalDevolucion;
	}

	public void setIdSucursalDevolucion(int idSucursalDevolucion) {
		this.idSucursalDevolucion = idSucursalDevolucion;
	}
		
	
}
