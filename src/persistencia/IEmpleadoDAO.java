//IClienteDAO
package persistencia;

import comunicacion.EmpleadoDTO;

import excepciones.*;

public interface IEmpleadoDAO {
 public EmpleadoDTO buscarEmpleado(String dni)throws DAOExcepcion;

 public void crearEmpleado(EmpleadoDTO empleado) throws DAOExcepcion;
}
