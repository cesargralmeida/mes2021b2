package persistencia;

import comunicacion.EntregaDTO;
import excepciones.DAOExcepcion;

public interface IEntregaDAO {
	public void crearEntrega(EntregaDTO entrega) throws DAOExcepcion;
	public EntregaDTO buscarEntrega(int id) throws DAOExcepcion;

}