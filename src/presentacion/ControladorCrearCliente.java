package presentacion;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import excepciones.DAOExcepcion;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.StringConverter;
import logica.AlquilerVehiculos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ControladorCrearCliente extends ControladorCasoDeUso {
    @FXML
    private TextField dni;
    @FXML
    private TextField nombreApellidos;
    @FXML
    private TextField direccion;
    @FXML
    private TextField añoTC;
    @FXML
    private TextField mesTC;
    @FXML
    private TextField codigoPostal;
    @FXML
    private TextField poblacion;
    @FXML
    private DatePicker fechaCarnet;
    @FXML
    private TextField cvc;
    @FXML
    private TextField tipoTarjeta;
    @FXML
    private TextField digitosTC;
    @FXML
    private Button aceptar;
    @FXML
    private Button cancelar;
    LocalDateTime hoy = LocalDateTime.now();

	DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("dd/MM/yyyy");

	StringConverter converter = new StringConverter<LocalDate>()
			{
	    @Override
	    public String toString(LocalDate localDate)
	    {
	        if(localDate==null)
	            return "";
	        return dateTimeFormatter.format(localDate);
	    }

	    @Override
	    public LocalDate fromString(String dateString)
	    {
	        if(dateString==null || dateString.trim().isEmpty())
	        {
	            return null;
	        }
	        return LocalDate.parse(dateString,dateTimeFormatter);
	    }
	};
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("CREAR CLIENTE");
        
        fechaCarnet.setConverter(converter);
        
        aceptar.setOnAction(event -> {
            try {
                if (isInputValid()) {

                    // DATOS
                    String DNI = dni.getText();
                    String NyA = nombreApellidos.getText();
                    String dir = direccion.getText();
                    String pob = poblacion.getText();
                    String CP = codigoPostal.getText();
                    LocalDateTime fechaC = fechaCarnet.getValue().atStartOfDay();
                    String digTC = digitosTC.getText();
                    int mTC = Integer.parseInt(mesTC.getText());
                    int aTC = Integer.parseInt(añoTC.getText());
                    int CVC = Integer.parseInt(cvc.getText());
                    String tT = tipoTarjeta.getText();

                    // Invocamos el servicio encargado de Crear un nuevo cliente
                    AlquilerVehiculos.getSingleton().crearCliente(DNI, NyA,
                            dir, pob, CP, fechaC, digTC, mTC, aTC, CVC, tT);
                    stage.close();
                }// fin isInputValid
            } catch (DAOExcepcion e) {
                e.printStackTrace();
            }
        });

        // Constructor de fechas válidas
        Callback<DatePicker, DateCell> dayCellFactory = dp -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);

                if (item.isAfter(LocalDate.now())) {
                    setStyle("-fx-background-color: #ffc0cb;");
                    setDisable(true);
                }
            }
        };

        fechaCarnet.setDayCellFactory(dayCellFactory);
        fechaCarnet.setEditable(false);

    }

    @FXML
    private void cerrar() {
        stage.close();
    }

    private boolean isInputValid() throws NumberFormatException, DAOExcepcion {
        String errorMessage = "";

            if (dni.getText().length() == 0 || dni.getText().length() > 10 || dni.getText().length() < 6) {
                errorMessage += "DNI introducido no válido.\n";
            } else {
                if (AlquilerVehiculos.getSingleton().getCliente(dni.getText()) != null)
                    errorMessage += "El DNI introducido ya está en nuestra base de datos.\n";
            }

            if (nombreApellidos.getText().length() == 0
                    || nombreApellidos.getText().length() > 20) {
                errorMessage += "Nombre y apellidos no indicado o es demasiado largo (tamaño máximo 20).\n";
            }

            if (direccion.getText().length() == 0 || direccion.getText().length() > 20) {
                errorMessage += "Dirección no indicada o es demasiado larga (tamaño máximo 20).\n";
            }

            if (poblacion.getText().length() == 0 || poblacion.getText().length() > 20) {
                errorMessage += "Población no indicada o es demasiado larga (tamaño máximo 20).\n";
            }

            if (codigoPostal.getText().length() == 0
                    || codigoPostal.getText().length() > 5 || !esInt(codigoPostal.getText())) {
                errorMessage += "El código postal es incorrecto.\n";
            }

            if (fechaCarnet.getValue() == null) {
                errorMessage += "No ha introducido la fecha de obtención del carnet.\n";
            }

            if (!esLong(digitosTC.getText())) {
            	errorMessage += "Número de tarjeta incorrecto. Asegurese de que sea un valor numérico.\n";
            } else if (digitosTC.getText().length() == 0 || digitosTC.getText().length() >= 16) {
                errorMessage += "Longitud de los dígitos de la tarjeta incorrecta.\n";
            }

            if (esInt(mesTC.getText())) {
                if (Integer.parseInt(mesTC.getText()) <= 0
                        || Integer.parseInt(mesTC.getText()) > 12) {
                    errorMessage += "Mes incorrecto. Asegurese de que sea un valor numerico entre 1 y 12\n";
                }
            } else {
                errorMessage += "Mes incorrecto. Asegurese de que sea un valor numérico.\n";
            }

            if (esInt(añoTC.getText())) {
                if (Integer.parseInt(añoTC.getText()) < hoy.getYear()) {
                    errorMessage += "Su tarjeta de crédito está caducada. El año introducido es anterior al actual.\n";
                }
            } else {
                errorMessage += "Año incorrecto. Asegurese de que sea un valor numérico.\n";
            }

            if (cvc.getText().length() != 3 || !esInt(cvc.getText())) {
                errorMessage += "CVC incorrecto. Asegurese de que sea un valor numérico de tres dígitos.\n";
            }
            if (tipoTarjeta.getText().length() == 0
                    || tipoTarjeta.getText().length() > 10) {
                errorMessage += "Tipo de tarjeta no indicado o demasiado largo (tamaño máximo 10).\n";
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
    
    public boolean esLong(String s) {
        try {
            Long.parseLong(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}