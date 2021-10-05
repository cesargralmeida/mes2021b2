package presentacion;

import excepciones.LogicaExcepcion;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class ControladorPrincipal {
	 private static final String CREAR_CLIENTE = "crear-cliente.fxml";
	 private static final String CREAR_RESERVA = "crear-reserva.fxml";
	 private static final String LISTAR_RESERVAS_SUCURSAL ="listado-reservas-sucursales.fxml"; 
	 private static final String LISTAR_SUCURSALES = "listado-sucursales.fxml";
	 private static final String LISTAR_CATEGORIAS = "listado-categorias.fxml";
	 private static final String ENTREGAR_VEHICULO_RESERVA = "entregar-vehiculo-reserva.fxml";
	 private static final String LISTAR_VEHICULO_SUCURSAL = "listado-coches-sucursales.fxml";
	 //TODO añadir constantes de tipo String para la vistas correspondientes a los
	 //casos de uso Crear Reserva y Listar Reservas de una Sucursal
	 private Stage primaryStage;
	 @FXML
	 void listarSucursales(ActionEvent event) throws LogicaExcepcion {
		 initCasoDeUso(LISTAR_SUCURSALES,ControladorListarSucursales.class).show();
	 }
	 @FXML
	 void listarCochesSucursal(ActionEvent event) throws LogicaExcepcion {
	 //TODO implementar el manejador del C.U. Listar reservas de una sucursal
		 initCasoDeUso(LISTAR_VEHICULO_SUCURSAL,ControladorListarCochesSucursales.class).show();
	 }
	 @FXML
	 void listarCategorias(ActionEvent event) throws LogicaExcepcion {
		 initCasoDeUso(LISTAR_CATEGORIAS,ControladorListarCategorias.class).show();
	 }
	 @FXML
	 void crearCliente(ActionEvent event) throws LogicaExcepcion {
		 initCasoDeUso(CREAR_CLIENTE, ControladorCrearCliente.class).show();
	 }
	 @FXML
	 void crearReserva(ActionEvent event) {
	 // TODO implementar el manejador del C.U. Crear Reserva
		 initCasoDeUso(CREAR_RESERVA,ControladorCrearReserva.class).show();
	 }
	 @FXML
	 void listarReservasSucursal(ActionEvent event) throws LogicaExcepcion {
	 //TODO implementar el manejador del C.U. Listar reservas de una sucursal
		 initCasoDeUso(LISTAR_RESERVAS_SUCURSAL,ControladorListarReservasSucursales.class).show();
	 }
	 @FXML
	 void entregarVehiculoReserva(ActionEvent event) throws LogicaExcepcion {
	 //TODO implementar el manejador del C.U. Listar reservas de una sucursal
		 initCasoDeUso(ENTREGAR_VEHICULO_RESERVA,ControladorEntregarVehiculoReserva.class).show();
	 }
	 @FXML
	 void salir(ActionEvent event) {
	 Platform.exit();
	 }
	 public void setPrimaryStage(Stage primaryStage) {
	 this.primaryStage = primaryStage;
	 }
	 private <T extends ControladorCasoDeUso> T initCasoDeUso(String urlVista,	Class<T> controlClass) {
		 return ControladorCasoDeUso.initCasoDeUso(urlVista, controlClass,	primaryStage, ControladorPrincipal.this);
	 }
	}

