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
import logica.Categoria;

public class ControladorListarCategorias extends ControladorCasoDeUso  {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Categoria, Double> precioSeguroTercerosC;

    @FXML
    private TableColumn<Categoria, String> categoriaSuperiorC;

    @FXML
    private TableColumn<Categoria, String> nombreC;

    @FXML
    private TableView<Categoria> categoriasT;

    @FXML
    private Button aceptar;

    @FXML
    private TableColumn<Categoria, Double> precioSeguroTodoRiesgoC;

    @FXML
    private TableColumn<Categoria, Double> precioModLimitadaC;

    @FXML
    private TableColumn<Categoria, Double> precioModKmsC;
    
    
    private String nombreSuperior(Categoria c) {
    
    	if(c != null) return c.getNombre();
    	else return "";
    }
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		stage = new Stage(StageStyle.DECORATED);
		stage.setTitle("LISTADO DE CATEGORIAS");
		aceptar.setOnAction(event -> stage.close());
		nombreC.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param
				.getValue().getNombre()));
		precioSeguroTercerosC.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(
				param.getValue().getPrecioSeguroTerceros()));
		categoriaSuperiorC.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(nombreSuperior(param
				.getValue().getSuperior())));
		precioSeguroTodoRiesgoC.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param
				.getValue().getPrecioSeguroTRiesgo()));
		precioModLimitadaC.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param
				.getValue().getPrecioModIlimitada()));
		precioModKmsC.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param
				.getValue().getPrecioKmModKms()));
		
		try {
			
			
			this.categoriasT.getItems().addAll(
					AlquilerVehiculos.getSingleton().listarCategorias());
		} catch (DAOExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
