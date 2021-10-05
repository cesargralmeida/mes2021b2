package presentacion;

import java.net.URL;
import java.util.ResourceBundle;

import excepciones.DAOExcepcion;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logica.AlquilerVehiculos;
import logica.Sucursal;

public class ControladorListarSucursales extends ControladorCasoDeUso {
	@FXML
	private TableView<Sucursal> sucursales;
	@FXML
	private TableColumn<Sucursal, Integer> id;
	@FXML
	private TableColumn<Sucursal, String> direccion;
	@FXML
	private Button aceptar;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		stage = new Stage(StageStyle.DECORATED);
		stage.setTitle("LISTADO DE SUCURSALES");
		//id.sort(null);
		aceptar.setOnAction(event -> stage.close());
		id.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param
				.getValue().getIdSucursal()));
		id.setStyle("-fx-alignment: CENTER;");
		direccion.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(
				param.getValue().getDireccion()));
		direccion.setStyle("-fx-alignment: CENTER;");

		try {
			this.sucursales.getItems().addAll(
					AlquilerVehiculos.getSingleton().listarSucursales());
		} catch (DAOExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}