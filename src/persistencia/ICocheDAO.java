//ICategoriaDAO
package persistencia;

import java.util.List;

import comunicacion.CocheDTO;
import excepciones.*;

public interface ICocheDAO {
	
	//public CocheDTO buscarCoche(String matricula)throws DAOExcepcion;
	public void modificarVehiculo(String matricula, double kms) throws DAOExcepcion;
	public List <CocheDTO> listarCoches(int sucursal) throws DAOExcepcion;
	public CocheDTO buscarCoche(String matricula) throws DAOExcepcion;

 
}
