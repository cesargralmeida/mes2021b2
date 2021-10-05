package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import comunicacion.ClienteDTO;
import excepciones.DAOExcepcion;

public class ClienteDAOImp implements IClienteDAO {

	protected ConnectionManager connManager;
	
	public ClienteDAOImp() throws DAOExcepcion {
		super();
		try{
		connManager= new ConnectionManager("alquilervehiculosBD");
		}
		catch (ClassNotFoundException e){	
			throw new DAOExcepcion(e);
			}
	}
	
	@Override
	public ClienteDTO buscarCliente(String dni) throws DAOExcepcion {
		try{
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from CLIENTE where DNI= '"+dni.trim()+"'");
			connManager.close();
		
			if (rs.next())
				return new ClienteDTO(
							dni.trim(),
							rs.getString("NOMBREAPELLIDOS"),
							rs.getString("DIRECCION"),
							rs.getString("POBLACION"),
							rs.getString("CODPOSTAL"),
							LocalDateTime.of(rs.getDate("FECHACARNETCONDUCIR").toLocalDate()
									,rs.getTime("FECHACARNETCONDUCIR").toLocalTime()),
							rs.getString("DIGITOSTC"),
							rs.getInt("MESTC"),
							rs.getInt("añoTC"),
							rs.getInt("CVCTC"),
							rs.getString("TipoTC"));
			else
				return null;	
		}
		catch (SQLException e){	throw new DAOExcepcion(e);}	
	}

	@Override
	public void crearCliente(ClienteDTO c) throws DAOExcepcion {
		try{
			connManager.connect();
			
			Timestamp fechaCarnet = Timestamp.valueOf(c.getFechaCarnetConducir());
			
			String DBQ = "insert into Cliente values('"
			+c.getDni()
			+"','"+c.getNombreyApellidos()
			+"','"+c.getDireccion()
			+"','"+c.getPoblacion()
			+"','"+c.getCodPostal()
			+"','"+fechaCarnet
			+"','"+c.getDigitosTC()
			+"',"+c.getMesTC()
			+","+c.getAñoTC()
			+","+c.getCvcTC()
			+",'"+c.getTipoTC()+"')";
			
			connManager.queryDB(DBQ);
			connManager.close();
			
		} catch (SQLException e){	throw new DAOExcepcion(e);}	

	}

}
