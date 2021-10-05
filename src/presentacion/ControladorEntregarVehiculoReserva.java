package presentacion;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import comunicacion.DAL;
import excepciones.DAOExcepcion;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.StringConverter;
import logica.AlquilerVehiculos;
import logica.Coche;
import logica.Reserva;
import logica.Sucursal;

public class ControladorEntregarVehiculoReserva extends ControladorCasoDeUso {
	@FXML
	private ResourceBundle resources;
	@FXML
	private URL location;
	@FXML
	private TableView<Reserva> reservaT;
	@FXML
	private ComboBox<Integer> sucursalCB;
	
	@FXML
	private TableColumn<Reserva, String> nombreCC;
	@FXML
	private TableColumn<Reserva, Integer> idC;
	@FXML
	private TableColumn<Reserva, String> fechaRecC;
	@FXML
	private TextField empleadoDNIT;
	@FXML
	private TableColumn<Reserva, String> dniC;
	@FXML
	private TextField kmsActualesT;
	@FXML
	private TableColumn<Reserva, String> categoriaC;
	@FXML
	private ComboBox<String> tipoSeguroCB;
	@FXML
	private TableColumn<Reserva, String> fechaDevC;
	@FXML
	private TableColumn<Reserva, String> modalidadC;
	@FXML
	private TableView<Coche> cochesT;
	@FXML
	private TableColumn<Coche, Double> kmsActualesC;
	@FXML
	private TableColumn<Coche, String> matriculaC;
	@FXML
	private TableColumn<Coche, String> categoriaCC;
	@FXML
	private Button aceptarB;
	@FXML
	private Button cancelarB;
	@FXML
	private TextField combustibleT;


	DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private void popularTablaReservas(int sucursal) {
		try {
			this.reservaT.getItems().clear();
			ArrayList<Reserva> reservas = AlquilerVehiculos.getSingleton().listarReservasSucursal(sucursal);
			for (int i = 0; i < reservas.size(); i++)
				if (reservas.get(i).getAsociado_a() == null) this.reservaT.getItems().add(reservas.get(i));

		} catch (DAOExcepcion e) {
			Alert alerta = new Alert(AlertType.WARNING);
			alerta.setTitle("Error");
			alerta.setContentText(e.getMessage());
			alerta.showAndWait();
		}
	}

	private void listarCoches(Reserva r) {
		if (r != null) {
		try {
			this.cochesT.getItems().clear();
			this.cochesT.getItems().addAll(
					AlquilerVehiculos.getSingleton()
							.listarVehiculosCategoriaSucursal(
									r.getTiene_asociada().getNombre(),
									sucursalCB.getSelectionModel()
											.getSelectedItem()));
		if (this.cochesT.getItems().size() == 0) {
			Alert alerta = new Alert(AlertType.WARNING);
			alerta.setTitle("Error");
			alerta.setContentText("No hay vehículos disponibles en esta sucursal para la reserva seleccionada.");
			alerta.showAndWait();
		}

		} catch (DAOExcepcion e) {
			Alert alerta = new Alert(AlertType.WARNING);
			alerta.setTitle("Error");
			alerta.setContentText(e.getMessage());
			alerta.showAndWait();
		}
		}
	}

	private String modalidad(String m) {
		if (m.trim().equals("1"))
			return "Kilometraje ilimitado";
		return "kilometraje minimo + cantidad/kilometro";
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		stage = new Stage(StageStyle.DECORATED);
		stage.setTitle("ENTREGAR VEHICULO RESERVADO");
		
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
		
		List<String> tipoSeguro = new ArrayList<String>();
		tipoSeguro.add("todoriesgo");
		tipoSeguro.add("terceros");
		
		tipoSeguroCB.setItems(FXCollections.observableList(tipoSeguro));
		sucursalCB.setItems(FXCollections.observableList(idSucurs));

		aceptarB.setOnAction(event -> aceptar());
		
		cancelarB.setOnAction(event -> stage.close());

		
		nombreCC.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param
				.getValue().getRealizada_por().getNombreyApellidos()));
		nombreCC.setStyle("-fx-alignment: CENTER;");
		
		idC.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param
				.getValue().getIdReserva()));
		idC.setStyle("-fx-alignment: CENTER;");

		fechaRecC.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(
				param.getValue().getFechaRecogida().format(dateTimeFormatter)));
		fechaRecC.setStyle("-fx-alignment: CENTER;");
		
		fechaDevC.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(
				param.getValue().getFechaDevolucion().format(dateTimeFormatter)));
		fechaDevC.setStyle("-fx-alignment: CENTER;");
		
		dniC.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param
				.getValue().getRealizada_por().getDNI()));
		dniC.setStyle("-fx-alignment: CENTER;");
		
		modalidadC.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(
				modalidad(param.getValue().getModalidadAlquiler())));
		modalidadC.setStyle("-fx-alignment: CENTER;");
		
		categoriaC.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(
				param.getValue().getTiene_asociada().getNombre()));
		categoriaC.setStyle("-fx-alignment: CENTER;");
		
		matriculaC.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(
				param.getValue().getMatricula()));
		matriculaC.setStyle("-fx-alignment: CENTER;");
		
		kmsActualesC.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(
				param.getValue().getKmsActuales()));
		kmsActualesC.setStyle("-fx-alignment: CENTER;");
		
		categoriaCC.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(
				param.getValue().getTiene().getNombre()));
		categoriaCC.setStyle("-fx-alignment: CENTER;");
		
		sucursalCB.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> popularTablaReservas(newValue));

		reservaT.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> listarCoches(newValue));

		sucursalCB.getSelectionModel().select(0);

	}
	
	private void aceptar() {
		try {
			if (isInputValid()) {
				AlquilerVehiculos.getSingleton().crearEntrega(reservaT.getSelectionModel().selectedItemProperty().getValue().getIdReserva(),
						LocalDateTime.now(), tipoSeguroCB.getValue(), Double.parseDouble(kmsActualesT.getText()), Double.parseDouble(combustibleT.getText()),
						cochesT.getSelectionModel().selectedItemProperty().getValue().getMatricula(), empleadoDNIT.getText());
				stage.close();
			}
		} catch (DAOExcepcion e) {
			e.printStackTrace();
		}
	}
	
    private boolean isInputValid() throws NumberFormatException, DAOExcepcion {
        String errorMessage = "";
        
        if(reservaT.getSelectionModel().isEmpty())
            errorMessage += "No ha seleccionado ninguna reserva en la tabla.\n";
              if(cochesT.getSelectionModel().isEmpty())
               errorMessage += "No ha seleccionado un vehiculo en la tabla.\n";
              
              
        if (tipoSeguroCB.getSelectionModel().isEmpty()) 
        	errorMessage += "No has seleccionado un tipo de seguro.\n";
        if (kmsActualesT.getText().length() == 0 || !esDouble(kmsActualesT.getText()))
        	errorMessage += "No has introducido un valor válido para el kilometraje.\n";
        if (combustibleT.getText().length() == 0 || !esDouble(combustibleT.getText()))
        	errorMessage += "No has introducido un valor válido para el combustible.\n";	
        if (empleadoDNIT.getText().length() == 0) {
            errorMessage += "DNI introducido no válido.\n";
        } else {
            if (AlquilerVehiculos.getSingleton().getEmpleado(empleadoDNIT.getText()) == null)
                errorMessage += "No existe un empleado con el DNI introducido.\n";
        }
           
        if (errorMessage.length() == 0) {
            return true;
        } else {

            Alert alerta = new Alert(AlertType.WARNING);
            alerta.setTitle("Error");
            alerta.setContentText(errorMessage);
            alerta.showAndWait();
            return false;
        }
    }

    public boolean esInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public boolean esDouble(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
