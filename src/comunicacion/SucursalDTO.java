package comunicacion;

import logica.Sucursal;

public class SucursalDTO {
	private int id;
	private String direcci�n;
	
	public SucursalDTO(int id, String direcci�n) {
		super();
		this.id = id;
		this.direcci�n = direcci�n;
	}
	
	
	public SucursalDTO(Sucursal suc) {
		this.id = suc.getIdSucursal();
		this.direcci�n = suc.getDireccion();
	}	


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDirecci�n() {
		return direcci�n;
	}
	public void setDirecci�n(String direcci�n) {
		this.direcci�n = direcci�n;
	}
	
}
