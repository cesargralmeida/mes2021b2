//ICategoriaDAO
package persistencia;

import java.util.List;

import comunicacion.CategoriaDTO;

import excepciones.*;

public interface ICategoriaDAO {
 public CategoriaDTO buscarCategoria(String nombre)throws DAOExcepcion;

 public List <CategoriaDTO> obtenerCategorias() throws DAOExcepcion;
}
