package logica;

import java.util.Collection;

import excepciones.DAOExcepcion;

public class DAOPersistenceTester {
	
	public static void main(String[] args) {
		AlquilerVehiculos av;
		try {
			av = AlquilerVehiculos.getSingleton();
		
		Collection<Categoria> categorias = av.listarCategorias();
		categorias.forEach(categoria -> System.out.println(categoria));
		} catch (DAOExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
