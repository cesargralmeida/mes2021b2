package logica;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import excepciones.DAOExcepcion;

public class TestCapaLogica {

	public static void main(String args[]) throws DAOExcepcion {

		AlquilerVehiculos av = AlquilerVehiculos.getSingleton();
		av.cargaSistema();
		ArrayList<Categoria> list = av.listarCategorias();
		ArrayList<Sucursal> list1 = av.listarSucursales();
		//System.out.print(av.listarVehiculosSucursal(2));
		System.out.println(av.listarVehiculosCategoriaSucursal("luxury",2));
//		for (int i = 0; i < list.size(); i++)	
//			for (int j = 0; j < list1.size(); j++)
//				System.out.println(av.listarVehiculosCategoriaSucursal(list.get(i).getNombre(), list1.get(j).getIdSucursal()));
	}
}
