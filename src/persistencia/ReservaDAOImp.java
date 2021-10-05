package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import comunicacion.ReservaDTO;
import excepciones.DAOExcepcion;

public class ReservaDAOImp implements IReservaDAO {

	protected ConnectionManager connManager;
	
	public ReservaDAOImp() throws DAOExcepcion {
		super();
		try{
		connManager= new ConnectionManager("alquilervehiculosBD");
		}
		catch (ClassNotFoundException e){	
			throw new DAOExcepcion(e);
			}
	}
	

	@Override
	public ReservaDTO buscarReserva(int id) throws DAOExcepcion {
		try {
			connManager.connect();
			ResultSet rs = connManager
					.queryDB("select * from RESERVA where ID= '" + id + "'");
			connManager.close();

			if (rs.next())
				return new ReservaDTO(rs.getInt("ID"), LocalDateTime.of(rs
						.getDate("FECHARECOGIDA").toLocalDate(),
						rs.getTime("FECHARECOGIDA").toLocalTime()),
						LocalDateTime.of(rs.getDate("FECHADEVOLUCION")
								.toLocalDate(), rs.getTime("FECHADEVOLUCION")
								.toLocalTime()), 
						rs.getString("MODALIDADALQUILER"),
						rs.getString("CLIENTEREALIZA"),
						rs.getString("CATEGORIA"),
						rs.getInt("SucursalRecogida"),
						rs.getInt("SucursalDevolucion"));
			else
				return null;
		} catch (SQLException e) {
			throw new DAOExcepcion(e);
		}
	}
	
	
	public int countReservas() throws DAOExcepcion {
		try {
			connManager.connect();
			ResultSet rs = connManager.queryDB("select max(id) as total from RESERVA");
			connManager.close();
			rs.next();
			return rs.getInt("total");

		} catch (SQLException e) {
			throw new DAOExcepcion(e);
		}
	}

	@Override
	public void crearReserva(ReservaDTO r) throws DAOExcepcion {
		try {
			connManager.connect();
			
			Timestamp recogida = Timestamp.valueOf(r.getFechaRecogida());
			Timestamp devolucion = Timestamp.valueOf(r.getFechaDevolucion());
			
			String DBQ = "insert into Reserva values(" 
					+ r.getId()
					+ ",'"+ recogida
					+ "','" + devolucion
					+ "','" + r.getModalidadAlquiler()
					+ "','" + r.getNombreCategoria()
					+ "','" + r.getDniCliente()  
					+"',"+ r.getIdSucursalRecogida() 
					+ ","+ r.getIdSucursalDevolucion() + ")";

			connManager.queryDB(DBQ);
			connManager.close();

		} catch (SQLException e) {
			throw new DAOExcepcion(e);
		}

	}

	@Override
	public List<ReservaDTO> obtenerReservasPorSucursalOrigen(int idSucursal) throws DAOExcepcion{
	try{
		connManager.connect();
		ResultSet rs=connManager.queryDB("select * from RESERVA where SucursalRecogida="+idSucursal);						
		connManager.close();
  	  
		List<ReservaDTO> listaReservaDTO = new ArrayList<ReservaDTO>();
			
		try{				
			while (rs.next()){

				ReservaDTO resDTO = new ReservaDTO(
						rs.getInt("ID"),
						LocalDateTime.of(rs.getDate("FECHARECOGIDA").toLocalDate()
								,rs.getTime("FECHARECOGIDA").toLocalTime()),
						LocalDateTime.of(rs.getDate("FECHADEVOLUCION").toLocalDate()
								,rs.getTime("FECHADEVOLUCION").toLocalTime()),
						rs.getString("MODALIDADALQUILER").trim(),
						rs.getString("CLIENTEREALIZA").trim(),
						rs.getString("CATEGORIA").trim(),
						idSucursal,
						rs.getInt("SucursalDevolucion"));
				listaReservaDTO.add(resDTO);
			}
			return listaReservaDTO;
		}
		catch (Exception e){	throw new DAOExcepcion(e);}
	}
	catch (SQLException e){	throw new DAOExcepcion(e);}	
	catch (DAOExcepcion e){		throw e;}

	}
}
