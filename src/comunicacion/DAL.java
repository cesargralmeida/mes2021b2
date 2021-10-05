package comunicacion;

import java.util.List;

import excepciones.DAOExcepcion;
import persistencia.*;


public class DAL {

	private ICategoriaDAO catDAO;
	private IClienteDAO cliDAO;
	private ISucursalDAO sucDAO;
	private IReservaDAO resDAO;
	private IEntregaDAO entDAO;
	private ICocheDAO cocDAO;
	private IEmpleadoDAO empDAO;
	
	private static DAL dal = null;

	private DAL() throws DAOExcepcion {

		this.catDAO = new CategoriaDAOImp();
		this.sucDAO = new SucursalDAOImp();
		this.resDAO = new ReservaDAOImp();
		this.cliDAO = new ClienteDAOImp();
		this.entDAO = new EntregaDAOImp();
		this.cocDAO = new CocheDAOImp();
		this.empDAO = new EmpleadoDAOImp();

	}

	public static DAL getSingleton() throws DAOExcepcion {
		if (dal == null)
			dal = new DAL();
		return dal;
	}



	public void crearCliente(ClienteDTO cliente) throws DAOExcepcion {
		cliDAO.crearCliente(cliente);
	}

	public void crearReserva(ReservaDTO reserva) throws DAOExcepcion {
		resDAO.crearReserva(reserva);
	}
	
	public void crearEntrega(EntregaDTO entrega) throws DAOExcepcion{
		entDAO.crearEntrega(entrega);
	}
	
	public int countReservas() throws DAOExcepcion {
		return resDAO.countReservas();
	}

	public List<ReservaDTO> getReservasSucursal(int id) throws DAOExcepcion {
		return resDAO.obtenerReservasPorSucursalOrigen(id);
	}
	
	public List<SucursalDTO> obtenerSucursales() throws DAOExcepcion {
		return sucDAO.obtenerSucursales();
	}
	
	public List<CocheDTO> obtenerCoches(int sucursal) throws DAOExcepcion{
		return cocDAO.listarCoches(sucursal);
	}
	
	public List<CategoriaDTO> obtenerCategorias() throws DAOExcepcion {
		return catDAO.obtenerCategorias();
	}

	public ReservaDTO getReserva(int id) throws DAOExcepcion{
		return resDAO.buscarReserva(id);
	}
	
	public CocheDTO getCoche(String matricula) throws DAOExcepcion{
		return cocDAO.buscarCoche(matricula);
	}
	
	public ClienteDTO getCliente(String dni) throws DAOExcepcion{
		return cliDAO.buscarCliente(dni);
	}
	
	public EmpleadoDTO getEmpleado(String dni) throws DAOExcepcion{
		return empDAO.buscarEmpleado(dni);
	}
	
	public EntregaDTO getEntrega(int id) throws DAOExcepcion{
		return entDAO.buscarEntrega(id);
	}
	
	public void modificarCoche(String matricula, double kms) throws DAOExcepcion{
		cocDAO.modificarVehiculo(matricula, kms);		
	}
	
}
