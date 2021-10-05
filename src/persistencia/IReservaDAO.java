//IReservaDAO
package persistencia;

import java.util.List;

import comunicacion.ReservaDTO;

import excepciones.*;

public interface IReservaDAO {
 public ReservaDTO buscarReserva(int identificador)throws DAOExcepcion;

 public List <ReservaDTO> obtenerReservasPorSucursalOrigen(int idSucursal) throws DAOExcepcion;
 
 public void crearReserva(ReservaDTO reserva) throws DAOExcepcion;
 
 public int countReservas() throws DAOExcepcion;
}
