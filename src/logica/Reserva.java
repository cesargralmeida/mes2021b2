	package logica;
import java.time.LocalDateTime;

import comunicacion.ReservaDTO;
import excepciones.DAOExcepcion;


public class Reserva {
	private int idReserva; //CP
	private LocalDateTime fechaRecogida;
	private LocalDateTime fechaDevolucion;
	private Cliente realizada_por; //Realizada_por
	private Categoria tiene_asociada; //Tiene_asociada
	private Sucursal lugar_recogida; //lugar_recogida
	private Sucursal lugar_devolucion; //lugar_devolucion
	private Entrega asociado_a; // asociado_a
	private String modalidadAlquiler;

	
	public Reserva() {}
	
	public Reserva(int idReserva, LocalDateTime fechaRecogida,
			LocalDateTime fechaDevolucion, Cliente realizada_por,
			Categoria tiene_asociada, Sucursal lugar_recogida,
			Sucursal lugar_devolucion,String modalidadAlquiler) {
		super();
		this.idReserva = idReserva;
		this.fechaRecogida = fechaRecogida;
		this.fechaDevolucion = fechaDevolucion;
		this.realizada_por = realizada_por;
		this.tiene_asociada = tiene_asociada;
		this.lugar_recogida = lugar_recogida;
		this.lugar_devolucion = lugar_devolucion;
		this.modalidadAlquiler = modalidadAlquiler;		
	}
	
	public Reserva(ReservaDTO res) {
		
		super();
		try {
		this.idReserva = res.getId();
		this.fechaRecogida = res.getFechaRecogida();
		this.fechaDevolucion = res.getFechaDevolucion();			
		this.realizada_por = AlquilerVehiculos.getSingleton().getCliente(res.getDniCliente());		
		this.tiene_asociada = AlquilerVehiculos.getSingleton().getCategoria(res.getNombreCategoria());
		this.lugar_recogida =  AlquilerVehiculos.getSingleton().getSucursal(res.getIdSucursalRecogida());
		this.lugar_devolucion =  AlquilerVehiculos.getSingleton().getSucursal(res.getIdSucursalDevolucion());		
		this.modalidadAlquiler = res.getModalidadAlquiler();
		} catch (DAOExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public int getIdReserva() {
		return idReserva;
	}
	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
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
	public Cliente getRealizada_por() {
		return realizada_por;
	}
	public void setRealizada_por(Cliente realizada_por) {
		this.realizada_por = realizada_por;
	}
	public Categoria getTiene_asociada() {
		return tiene_asociada;
	}
	public void setTiene_asociada(Categoria tiene_asociada) {
		this.tiene_asociada = tiene_asociada;
	}
	public Sucursal getLugar_recogida() {
		return lugar_recogida;
	}
	public void setLugar_recogida(Sucursal lugar_recogida) {
		this.lugar_recogida = lugar_recogida;
	}
	public Sucursal getLugar_devolucion() {
		return lugar_devolucion;
	}
	public void setLugar_devolucion(Sucursal lugar_devolucion) {
		this.lugar_devolucion = lugar_devolucion;
	}
	public Entrega getAsociado_a() {
		return asociado_a;
	}
	public void setAsociado_a(Entrega asociado_a) {
		this.asociado_a = asociado_a;
	}

	@Override
	public String toString() {
		String dni ="", nombre ="";
		int idSucursalRecogida = -1, idSucursalEntrega = -1, entrega = -1;
		
		if (realizada_por != null) dni = realizada_por.getDNI();
		if (tiene_asociada != null) nombre = tiene_asociada.getNombre();
		if (lugar_recogida != null) idSucursalRecogida = lugar_recogida.getIdSucursal();
		if (lugar_devolucion != null) idSucursalEntrega = lugar_devolucion.getIdSucursal();
		if (asociado_a != null) entrega = asociado_a.getId();
		 
		return "Reserva [idReserva=" + idReserva + ", fechaRecogida=" + fechaRecogida + ", fechaDevolucion="
				+ fechaDevolucion + ", realizada_por=" + dni + ", tiene_asociada=" + nombre
				+ ", lugar_recogida=" + idSucursalRecogida + ", lugar_devolucion=" + idSucursalEntrega + ", asociado_a="
				+ entrega + ", modalidadAlquiler=" + modalidadAlquiler + "]";
	}


}
