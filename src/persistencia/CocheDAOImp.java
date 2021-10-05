package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import comunicacion.CocheDTO;
import excepciones.DAOExcepcion;

	public class CocheDAOImp implements ICocheDAO {

	protected ConnectionManager connManager;
	
	public CocheDAOImp() throws DAOExcepcion {
		super();
		try{
		connManager= new ConnectionManager("alquilervehiculosBD");
		}
		catch (ClassNotFoundException e){	
			throw new DAOExcepcion(e);
			}
	}

	@Override
	public List<CocheDTO> listarCoches(int sucursal) throws DAOExcepcion {
		try{
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from COCHE where SUCURSAL= '"+sucursal+"'");						
			connManager.close();
			
			List<CocheDTO> listaCocheDTO = new ArrayList<CocheDTO>();
			
			try{				
				while (rs.next()){
					CocheDTO cocDTO = new CocheDTO(
							rs.getString("MATRICULA"),
							rs.getDouble("KMSACTUALES"),
							rs.getInt("SUCURSAL"),
							rs.getString("NOMBRE"));//NOMBRE ES CATEGORIA EN LA BASE DE DATOS
					listaCocheDTO.add(cocDTO);
				}
				return listaCocheDTO;
			}
			catch (Exception e){ throw new DAOExcepcion(e);}
		}
		catch (SQLException e){	throw new DAOExcepcion(e);}	
		catch (DAOExcepcion e){	throw e;}
	}
	
	@Override
	public void modificarVehiculo(String matricula, double kms) throws DAOExcepcion{	
		try{
			connManager.connect();
			connManager.queryDB("UPDATE COCHE SET SUCURSAL = NULL WHERE MATRICULA= '"+matricula+"'");				
			connManager.queryDB("UPDATE COCHE SET KMSACTUALES = '"+kms+"' WHERE MATRICULA= '"+matricula+"'");				
			connManager.close();
		}
		catch (SQLException e){	throw new DAOExcepcion(e);}
	}
	
	
	@Override
	public CocheDTO buscarCoche(String matricula) throws DAOExcepcion {
		try{
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from COCHE where MATRICULA= '"+matricula+"'");						
			connManager.close();
			
			
			try{				
				if (rs.next()){
					return new CocheDTO(
							rs.getString("MATRICULA"),
							rs.getDouble("KMSACTUALES"),
							rs.getInt("SUCURSAL"),
							rs.getString("NOMBRE"));//NOMBRE ES CATEGORIA EN LA BASE DE DATOS

			} else return null;
			}
			catch (Exception e){ throw new DAOExcepcion(e);}
		}
		catch (SQLException e){	throw new DAOExcepcion(e);}	
		catch (DAOExcepcion e){	throw e;}
	}
}
