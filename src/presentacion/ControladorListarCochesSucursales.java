	package presentacion;

	import java.net.URL;
	import java.util.ArrayList;
	import java.util.List;
	import java.util.ResourceBundle;
	import excepciones.DAOExcepcion;
	import javafx.beans.property.ReadOnlyObjectWrapper;
	import javafx.collections.FXCollections;
	import javafx.fxml.FXML;
	import javafx.scene.control.Alert;
	import javafx.scene.control.Button;
	import javafx.scene.control.ComboBox;
	import javafx.scene.control.TableColumn;
	import javafx.scene.control.TableView;
	import javafx.scene.control.Alert.AlertType;
	import javafx.stage.Stage;
	import javafx.stage.StageStyle;
	import logica.AlquilerVehiculos;
	import logica.Coche;
	import logica.Sucursal;

	public class ControladorListarCochesSucursales extends ControladorCasoDeUso{

	    @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private TableColumn<Coche, String> categoriaC;

	    @FXML
	    private ComboBox<Integer> sucursalCB;

	    @FXML
	    private TableColumn<Coche, Double> kmsActualesC;

	    @FXML
	    private TableView<Coche> cochesT;

	    @FXML
	    private TableColumn<Coche, String> matriculaC;

	    @FXML
	    private Button aceptarB;


	private void listarCoches(int idsucursal) {
		
		try {
			this.cochesT.getItems().clear();
			this.cochesT.getItems().addAll(
					AlquilerVehiculos.getSingleton()
							.listarVehiculosSucursal(idsucursal));

		} catch (DAOExcepcion e) {
			Alert alerta = new Alert(AlertType.WARNING);
			alerta.setTitle("Error");
			alerta.setContentText(e.getMessage());
			alerta.showAndWait();
		}
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		stage = new Stage(StageStyle.DECORATED);
		stage.setTitle("LISTADO DE COCHES");

		ArrayList<Sucursal> sucurs = new ArrayList<Sucursal>();
		try {
			sucurs = AlquilerVehiculos.getSingleton().listarSucursales();
		} catch (DAOExcepcion e) {
			Alert alerta = new Alert(AlertType.WARNING);
			alerta.setTitle("Error");
			alerta.setContentText(e.getMessage());
			alerta.showAndWait();
		}
		
		List<Integer> idSucurs = new ArrayList<Integer>();
		for (int i = 0; i < sucurs.size(); i++) {
			idSucurs.add(sucurs.get(i).getIdSucursal());
		}
		idSucurs.sort(null); //ordena los valores ascendemente
		sucursalCB.setItems(FXCollections.observableList(idSucurs));
		
		matriculaC.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(
				param.getValue().getMatricula()));
		
		kmsActualesC.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(
				param.getValue().getKmsActuales()));
		
		categoriaC.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(
				param.getValue().getTiene().getNombre()));
		
		sucursalCB.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> listarCoches(newValue));
		aceptarB.setOnAction(event -> aceptar());
	}
	
	private void aceptar() {
		stage.close();
	}
	
}
