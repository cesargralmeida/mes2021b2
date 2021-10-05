package presentacion;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import excepciones.DAOExcepcion;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logica.AlquilerVehiculos;
import logica.Reserva;

public class ControladorListarReservasSucursales extends ControladorCasoDeUso {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Reserva, String> fechaRec;

    @FXML
    private TableColumn<Reserva, String> lugarDev;

    @FXML
    private TableView<Reserva> reservaT;

    @FXML
    private TableColumn<Reserva, String> modalidad;

    @FXML
    private TableColumn<Reserva, String> lugarRec;

    @FXML
    private TableColumn<Reserva, String> entregado;

    @FXML
    private TableColumn<Reserva, Integer> id;

    @FXML
    private TableColumn<Reserva, String> fechaDev;

    @FXML
    private TableColumn<Reserva, String> dni;

    @FXML
    private Button aceptarB;

    @FXML
    private Button buscarB;

    @FXML
    private TextField buscarT;

    @FXML
    private Text soloIntL;

    DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    private String esEntregado(CellDataFeatures<Reserva, String> param) {
        if (param.getValue().getAsociado_a() != null)
            return "Si";
        return "No";
    }
    
    private String modalidad(String m) {
        if (m.trim().equals("1")) return "Kilometraje ilimitado";
            return "kilometraje minimo + cantidad/kilometro";
    }

    private void esInt(String text) {
        try {
            Integer.parseInt(text);
            soloIntL.setVisible(false);
            buscarB.setDisable(false);;
        } catch (Exception e) {
            if (text.equals("")) {
                soloIntL.setVisible(false);
            } else {
                soloIntL.setVisible(true);
            }
            buscarB.setDisable(true);

        }
    }

    private void listar() {
        try {
			if(AlquilerVehiculos.getSingleton().listarReservasSucursal(
			        Integer.parseInt(buscarT.getText())).isEmpty()){
				 Alert idNoValido = new Alert(AlertType.WARNING);
			     idNoValido.setTitle("ID NO VÁLIDA");
			     idNoValido.setContentText("No has seleccionado una sucursal válida");
			     idNoValido.showAndWait();
			}
			else{
			    this.reservaT.getItems().clear();
			    this.reservaT.getItems().addAll(
			            AlquilerVehiculos.getSingleton().listarReservasSucursal(
			                    Integer.parseInt(buscarT.getText())));
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (DAOExcepcion e) {
			e.printStackTrace();
		}
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("LISTADO DE RESERVAS POR SUCURSAL DE ORIGEN");
        
        buscarB.setDisable(true);
        soloIntL.setVisible(false);
        
        aceptarB.setOnAction(event -> stage.close());
        
        id.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param
                .getValue().getIdReserva()));
        id.setStyle("-fx-alignment: CENTER;");
        lugarRec.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param
                .getValue().getLugar_recogida().getDireccion()));
        lugarRec.setStyle("-fx-alignment: CENTER;");
        lugarDev.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param
                .getValue().getLugar_devolucion().getDireccion()));
        lugarDev.setStyle("-fx-alignment: CENTER;");
        fechaRec.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param
                .getValue().getFechaRecogida().format(dateTimeFormatter)));
        fechaRec.setStyle("-fx-alignment: CENTER;");
        fechaDev.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param
                .getValue().getFechaDevolucion().format(dateTimeFormatter)));
        fechaDev.setStyle("-fx-alignment: CENTER;");
        dni.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param
                .getValue().getRealizada_por().getDNI()));
        dni.setStyle("-fx-alignment: CENTER;");
        modalidad.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(
                modalidad(param.getValue().getModalidadAlquiler())));
        modalidad.setStyle("-fx-alignment: CENTER;");
        entregado.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(
                esEntregado(param)));
        entregado.setStyle("-fx-alignment: CENTER;");
        
        buscarT.textProperty().addListener(
                (observable, oldValue, newValue) -> esInt(newValue));
        
        buscarB.setOnAction(event -> listar());

    }
}
