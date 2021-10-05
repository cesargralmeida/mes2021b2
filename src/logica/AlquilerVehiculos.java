package logica;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import comunicacion.CategoriaDTO;
import comunicacion.ClienteDTO;
import comunicacion.CocheDTO;
import comunicacion.DAL;
import comunicacion.EmpleadoDTO;
import comunicacion.EntregaDTO;
import comunicacion.ReservaDTO;
import comunicacion.SucursalDTO;
import excepciones.DAOExcepcion;

public class AlquilerVehiculos {

	private Hashtable<String, Cliente> coleccionClientes;
	private Hashtable<Integer, Reserva> coleccionReservas;
	private Hashtable<String, Categoria> coleccionCategorias;
	private Hashtable<Integer, Sucursal> coleccionSucursales;
	private Hashtable<Integer, Entrega> coleccionEntregas;

	private DAL dal;
	private static AlquilerVehiculos alquiler;

	private AlquilerVehiculos() throws DAOExcepcion {

		coleccionClientes = new Hashtable<String, Cliente>();
		coleccionReservas = new Hashtable<Integer, Reserva>();
		coleccionCategorias = new Hashtable<String, Categoria>();
		coleccionSucursales = new Hashtable<Integer, Sucursal>();
		coleccionEntregas = new Hashtable<Integer, Entrega>();
		this.dal = DAL.getSingleton();
		cargaSistema();
	}

	public static AlquilerVehiculos getSingleton() throws DAOExcepcion {
		if (alquiler == null)
			alquiler = new AlquilerVehiculos();

		return alquiler;
	}

	public ArrayList<Reserva> listarReservasSucursal(int id)
			throws DAOExcepcion {
		ArrayList<Reserva> res = new ArrayList<Reserva>();
		List<ReservaDTO> resDTO = dal.getReservasSucursal(id);
		Reserva aux;
		ReservaDTO auxDTO;
		Entrega entrega;
		for (int i = 0; i < resDTO.size(); i++) {
			auxDTO = resDTO.get(i);
			aux = new Reserva(auxDTO);
			aux.setRealizada_por(this.getCliente(auxDTO.getDniCliente()));
			aux.setLugar_devolucion(this.getSucursal(auxDTO
					.getIdSucursalDevolucion()));
			aux.setLugar_recogida(this.getSucursal(auxDTO
					.getIdSucursalRecogida()));
			aux.setTiene_asociada(this.getCategoria(auxDTO.getNombreCategoria()));

			entrega = this.getEntrega(auxDTO.getId());
			
			if (entrega != null) {
				aux.setAsociado_a(entrega);
			}
			res.add(aux);
			coleccionReservas.put(aux.getIdReserva(), aux);
		}
		return res;
	}

	public ArrayList<Categoria> listarCategorias() {
		ArrayList<Categoria> arr = new ArrayList<Categoria>();
		arr.addAll(coleccionCategorias.values());
		return arr;
	}

	public ArrayList<Coche> listarVehiculosSucursal(int sucursal) throws DAOExcepcion {
		ArrayList<Coche> arr = new ArrayList<Coche>();
		List<CocheDTO> resDTO = dal.obtenerCoches(sucursal);
		Coche aux;
		CocheDTO auxDTO;
		for (int i = 0; i < resDTO.size(); i++) {
			auxDTO = resDTO.get(i);
			aux = new Coche(auxDTO);
			aux.setEsta_en(this.getSucursal(auxDTO.getEsta_en()));
			arr.add(aux);
		}
		return arr;
	}

	public ArrayList<Coche> listarVehiculosCategoriaSucursal(String categoria,
			int sucursal) throws DAOExcepcion {
		ArrayList<Coche> arr = this.listarVehiculosSucursal(sucursal);
		ArrayList<Coche> res = new ArrayList<Coche>();
		Categoria aux;
		while (res.size() == 0 && categoria != null) {
			for (int i = 0; i < arr.size(); i++) {
				aux = arr.get(i).getTiene();
				if (aux != null)
					if (aux.getNombre() == categoria)
						res.add(arr.get(i));
			}

			if (res.size() == 0) {
				aux = this.getCategoria(categoria).getSuperior();
				if (aux != null) categoria = aux.getNombre();
				else categoria = null;
						
			} else
				break;
		}
		return res;
	}


	public ArrayList<Sucursal> listarSucursales() {
		ArrayList<Sucursal> arr = new ArrayList<Sucursal>();
		arr.addAll(coleccionSucursales.values());
		return arr;
	}

	public void cargaSistema() throws DAOExcepcion {
		cargaCategorias();
		cargarSucursales();
	}

	private void cargarSucursales() throws DAOExcepcion {

		List<SucursalDTO> sucDTO = dal.obtenerSucursales();
		// Crear y añadir todas las categorias a la colección
		for (SucursalDTO sucI : sucDTO) {
			addSucursal(new Sucursal(sucI));
		}

	}

	private void cargaCategorias() throws DAOExcepcion {
		List<CategoriaDTO> listacatDTO = dal.obtenerCategorias();
		// Crear y añadir todas las categorias a la colección
		for (CategoriaDTO catDTO : listacatDTO) {

			addCategoria(new Categoria(catDTO));

		}
		// Actualizar los enlaces que representan la relación “superior”
		for (CategoriaDTO catDTO : listacatDTO)
			if (catDTO.getNombreCategoriaSuperior() != null)
				getCategoria(catDTO.getNombre()).setSuperior(
						getCategoria(catDTO.getNombreCategoriaSuperior()));
	}

	public Reserva crearReserva(LocalDateTime fechaRec, LocalDateTime fechaDev,
			String mod, Cliente cliente, Categoria cat, Sucursal recogida,
			Sucursal devolucion) throws DAOExcepcion {

		Reserva r1 = new Reserva(dal.countReservas() + 1, fechaRec, fechaDev,
				cliente, cat, recogida, devolucion, mod);
		addReserva(r1);
		dal.crearReserva(new ReservaDTO(r1));
		return r1;
	}

	public Cliente crearCliente(String DNI, String nombreyApellidos,
			String direccion, String poblacion, String codPostal,
			LocalDateTime fechaCarnetConducir, String digitosTC, int mesTC,
			int anyoTC, int cvcTC, String tipoTC) throws DAOExcepcion {

		Cliente cliente = new Cliente(DNI, nombreyApellidos, direccion,
				poblacion, codPostal, fechaCarnetConducir, digitosTC, mesTC,
				anyoTC, cvcTC, tipoTC);

		// Lo añade a la memoria
		addCliente(cliente);

		// Y le pide a dal que lo almacene
		dal.crearCliente(new ClienteDTO(cliente));
		return cliente;
	}
	
	public void crearEntrega(int id, LocalDateTime fecha, String tipoSeguro, 
								double kms, double combustible, String tiene_asociado, String es_realizada_por) throws DAOExcepcion{
		EntregaDTO entregaDTO = new EntregaDTO(id, fecha, tipoSeguro, kms, combustible, tiene_asociado, es_realizada_por);
		dal.crearEntrega(entregaDTO);
		dal.modificarCoche(tiene_asociado, kms);
		Entrega entrega = new Entrega(entregaDTO);
		entrega.setAsociado_a_reserva(this.getReserva(id));
		entrega.setEs_realizada_por(this.getEmpleado(es_realizada_por));
		entrega.setTiene_asociado(new Coche(dal.getCoche(tiene_asociado)));
		this.addEntrega(entrega);
	}

	private void addEntrega(Entrega e) {
		coleccionEntregas.put(e.getId(), e);
	}
	
	private void addCliente(Cliente c) {
		coleccionClientes.put(c.getDNI(), c);
	}

	public void removeCliente(String dni) {
		coleccionClientes.remove(dni);
	}

	public Cliente getCliente(String dni) throws DAOExcepcion {
		Cliente cli = this.coleccionClientes.get(dni);
		if (cli != null)
			return cli;
		ClienteDTO cliDTO = dal.getCliente(dni);
		if (cliDTO == null)
			return null;
		this.addCliente(new Cliente(cliDTO));
		return new Cliente(cliDTO);
	}
	
	public Entrega getEntrega(int id) throws DAOExcepcion {
		Entrega ent = this.coleccionEntregas.get(id);
		if (ent != null)
			return ent;
		EntregaDTO entDTO = dal.getEntrega(id);
		if (entDTO == null)
			return null;
		this.addEntrega(new Entrega(entDTO));
		return new Entrega(entDTO);
	}
	
	public Empleado getEmpleado(String dni) throws DAOExcepcion {
		EmpleadoDTO empDTO = dal.getEmpleado(dni);
		if (empDTO == null)
			return null;
		return new Empleado(empDTO);
	}

	private void addReserva(Reserva r) {
		coleccionReservas.put(r.getIdReserva(), r);
	}

	public void removeReserva(int idReserva) {
		coleccionReservas.remove(idReserva);
	}

	public Reserva getReserva(int idReserva) throws DAOExcepcion {
		Reserva res = this.coleccionReservas.get(idReserva);
		if (res != null)
			return res;
		ReservaDTO resDTO = dal.getReserva(idReserva);
		if (resDTO == null)
			return null;
		this.addReserva(new Reserva(resDTO));
		return new Reserva(resDTO);
	}

	private void addCategoria(Categoria c) {
		coleccionCategorias.put(c.getNombre(), c);
	}

	public void removeCategoria(String nombre) {
		coleccionCategorias.remove(nombre);
	}

	public Categoria getCategoria(String nombre) {
		return coleccionCategorias.get(nombre);
	}

	private void addSucursal(Sucursal s) {
		coleccionSucursales.put(s.getIdSucursal(), s);
	}

	public void removeSucursal(int idSucursal) {
		coleccionSucursales.remove(idSucursal);
	}

	public Sucursal getSucursal(int idSucursal) {
		return coleccionSucursales.get(idSucursal);
	}

}
