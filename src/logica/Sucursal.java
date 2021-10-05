package logica;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import comunicacion.SucursalDTO;


public class Sucursal {

	private int idSucursal; //CP
	private String direccion;
	
	private Hashtable<Integer,Reserva> lugar_devolucion = new Hashtable<Integer,Reserva>();
	private Hashtable<Integer,Reserva> lugar_recogida = new Hashtable<Integer,Reserva>();
	private Hashtable<String,Coche> esta_en = new Hashtable<String,Coche>();
	private Hashtable<String,Empleado> coleccionEmpleados = new Hashtable<String,Empleado>();
	
	
	
	@Override
	public String toString() {
		return "Sucursal [idSucursal=" + idSucursal + ", direccion=" + direccion + ", lugar_devolucion="
				+ lugar_devolucion + ", lugar_recogida=" + lugar_recogida + ", esta_en=" 
				+ ", coleccionEmpleados=" + coleccionEmpleados + "]";
	}

	public void addReservaDevolucion(Reserva r) {
		lugar_devolucion.put(r.getIdReserva(), r);
	}
	
	public Sucursal(SucursalDTO suc) {
		this.idSucursal = suc.getId();
		this.direccion = suc.getDirección();
	}

	public Reserva removeReservaDevolucion(String idReserva) {
		return lugar_devolucion.remove(idReserva);	
	}
	
	public Reserva getReservaDevolucion(String idReserva) {
		return lugar_devolucion.get(idReserva);
	}
	
	public void addReservaRecogida(Reserva r) {
		lugar_recogida.put(r.getIdReserva(), r);
	}
	
	public Reserva removeReservaRecogida(String idReserva) {
		return lugar_recogida.remove(idReserva);	
	}
	
	public Reserva getReservaRecogida(String idReserva) {
		return lugar_recogida.get(idReserva);
	}
	
	public void addCoche(Coche c) {
		esta_en.put(c.getMatricula(), c);
	}
	
	public Coche removeCoche(String matricula) {
		return esta_en.remove(matricula);	
	}
	
	public Coche getCoche(String matricula) {
		return esta_en.get(matricula);
	}
	
	public void addEmpleado(Empleado e) {
		coleccionEmpleados.put(e.getDNI(), e);
	}
	
	public Empleado removeEmpleado(String DNI) {
		return coleccionEmpleados.remove(DNI);	
	}
	
	public Empleado getEmpleado(String DNI) {
		return coleccionEmpleados.get(DNI);
	}

	public int getIdSucursal() {
		return idSucursal;
	}

	public void setIdSucursal(int i) {
		this.idSucursal = i;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public Collection<Reserva> getReservas(){
				
		Collection<Reserva> rec = lugar_recogida.values();
		Collection<Reserva> dev = lugar_devolucion.values();
		
		List<Reserva> list = new ArrayList<Reserva>(rec);
		List<Reserva> list2 = new ArrayList<Reserva>(dev);
		
		list.addAll(list2);
		
		Set<Reserva> hs = new HashSet<>();
		hs.addAll(list);
		list.clear();
		list.addAll(hs);
		
		return list;
	}

	
}
