package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import comunicacion.EmpleadoDTO;
import excepciones.DAOExcepcion;

public class EmpleadoDAOImp implements IEmpleadoDAO {

	protected ConnectionManager connManager;
	
	public EmpleadoDAOImp() throws DAOExcepcion {
		super();
		try{
		connManager= new ConnectionManager("alquilervehiculosBD");
		}
		catch (ClassNotFoundException e){	
			throw new DAOExcepcion(e);
			}
	}
	//DNI CHAR(10) NOT NULL PRIMARY KEY,NOMBRE CHAR(25) NOT NULL,
	//ADMINISTRADOR BOOLEAN NOT NULL,SUCURSAL INTEGER NOT NULL,
	//CONSTRAINT FK_EMPLEADO_1 FOREIGN KEY(SUCURSAL) REFERENCES SUCURSAL(ID))
	@Override
	public EmpleadoDTO buscarEmpleado(String dni) throws DAOExcepcion {
		try{
			connManager.connect();
			ResultSet rs=connManager.queryDB("select * from EMPLEADO where DNI= '"+dni+"'");
			connManager.close();
		
			if (rs.next())
				return new EmpleadoDTO(
							rs.getString("DNI"),
							rs.getString("NOMBRE"),
							rs.getBoolean("ADMINISTRADOR"));
							
			else
				return null;	
		}
		catch (SQLException e){	throw new DAOExcepcion(e);}	
	}

	@Override
	public void crearEmpleado(EmpleadoDTO e) throws DAOExcepcion {
		try{
			connManager.connect();
			
			String DBQ = "insert into Cliente values('"+
			e.getDni()+"','"+
			e.getNombreyApellidos()+"','"+
			e.getAdministrador();
					
			connManager.queryDB(DBQ);
			connManager.close();
			
		} catch (SQLException s){	throw new DAOExcepcion(s);}	

	}


}
