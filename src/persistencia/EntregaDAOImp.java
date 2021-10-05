package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import comunicacion.EntregaDTO;
import comunicacion.ReservaDTO;
import excepciones.DAOExcepcion;

public class EntregaDAOImp implements IEntregaDAO {

	protected ConnectionManager connManager;
	
	public EntregaDAOImp() throws DAOExcepcion {
		super();
		try{
		connManager= new ConnectionManager("alquilervehiculosBD");
		}
		catch (ClassNotFoundException e){	
			throw new DAOExcepcion(e);
			}
	}

	@Override
	public void crearEntrega(EntregaDTO e) throws DAOExcepcion {
		try{
			connManager.connect();
			
			Timestamp fecha = Timestamp.valueOf(e.getFecha());
			
			String DBQ = "insert into Entrega values('"
			+e.getId()
			+"','"+fecha
			+"','"+e.getTipoSeguro()
			+"','"+e.getKms()
			+"','"+e.getCombustible()
			+"','"+e.getTiene_asociado()
			+"','"+e.getEs_realizada_por()+"')";
			
			connManager.queryDB(DBQ);
			connManager.close();
			
		} catch (SQLException s){	throw new DAOExcepcion(s);}	

		
		
	}
	
	@Override
	public EntregaDTO buscarEntrega(int id) throws DAOExcepcion {
		try {
			connManager.connect();
			ResultSet rs = connManager
					.queryDB("select * from ENTREGA where ID= '" + id + "'");
			connManager.close();

			if (rs.next())
				return new EntregaDTO(rs.getInt("ID"),
						LocalDateTime.of(rs
								.getDate("FECHA").toLocalDate(),
								rs.getTime("FECHA").toLocalTime()),
						rs.getString("TIPOSEGURO"),
						rs.getDouble("KMS"),
						rs.getDouble("COMBUSTIBLE"),
						rs.getString("COCHEASIGNADO"),
						rs.getString("EMPLEADOREALIZA"));
			else
				return null;
		} catch (SQLException e) {
			throw new DAOExcepcion(e);
		}
	}
}