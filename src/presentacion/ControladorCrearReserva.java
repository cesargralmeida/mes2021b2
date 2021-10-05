package presentacion;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import excepciones.DAOExcepcion;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DateCell;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logica.AlquilerVehiculos;
import logica.Categoria;
import logica.Cliente;
import logica.Sucursal;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ControladorCrearReserva extends ControladorCasoDeUso {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> direccionDevolucion;

    @FXML
    private Button cancelar;

    @FXML
    private DatePicker fechaDevolucion;

    @FXML
    private ComboBox<String> categoria;

    @FXML
    private ComboBox<String> modalidadAlquiler;
    
    @FXML
    private Button aceptar;
    
    @FXML
    private Label noExisteL;

    @FXML
    private DatePicker fechaRecogida;

    @FXML
    private ComboBox<String> direccionRecogida;

    @FXML
    private TextField dni;
    
    Cliente cRealiza;
	String categoriaNom="";
	int idSucursalRec=0;
	int idSucursalDev=0;
	String mod=""; 
	
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
	
	public void dniValido(String d){
		try {
			if (AlquilerVehiculos.getSingleton().getCliente(d) == null) {
				noExisteL.setVisible(true);
			} else 	noExisteL.setVisible(false);

		} catch (DAOExcepcion e) {
			
		}
	}
	
	
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("CREAR RESERVA");

		noExisteL.setVisible(false);

        dni.textProperty().addListener(
                (observable, oldValue, newValue) -> dniValido(newValue));
        
        
        
        // Constructor de fechas válidas
        Callback<DatePicker, DateCell> dayCellFactory = dp -> new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item.isBefore(LocalDate.now())) {
                            setStyle("-fx-background-color: #ffc0cb;");
                            setDisable(true);
                        }
                    }
                };
               
        fechaRecogida.setDayCellFactory(dayCellFactory);
        fechaDevolucion.setDayCellFactory(dayCellFactory);
        fechaRecogida.setEditable(false);
        fechaDevolucion.setEditable(false);
        fechaRecogida.setConverter(converter);
        fechaDevolucion.setConverter(converter);
        
        
        fechaRecogida.setOnAction(event -> {
        	if (fechaDevolucion.getValue() != null)
	        	if (fechaRecogida.getValue().isAfter(fechaDevolucion.getValue())) {
	        		fechaDevolucion.setValue(null);
	        	}
        	Callback<DatePicker, DateCell> dayCellFactory2 = dp -> new DateCell() {
                @Override
                public void updateItem(LocalDate item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item.isBefore(LocalDate.now()) || item.isBefore(fechaRecogida.getValue())) {
                        setStyle("-fx-background-color: #ffc0cb;");
                        setDisable(true);
                    }
                }
            };
            fechaDevolucion.setDayCellFactory(dayCellFactory2);

           
        });
  
        ArrayList<Sucursal> sucursales;
		try {
		sucursales = AlquilerVehiculos.getSingleton().listarSucursales();
		ArrayList<Categoria> categorias=AlquilerVehiculos.getSingleton().listarCategorias();
    	List<String> nomCategorias =new ArrayList<String>();
    	List<String> direcciones = new ArrayList<String>();
    	List<String> modalidadesAlquiler = new ArrayList<String>();
    	ArrayList<String> modalidades = new ArrayList<String>();
    	
    	modalidades.add("1");
    	modalidades.add("2");
    	
    	modalidadesAlquiler.add("kilometraje ilimitado");
    	modalidadesAlquiler.add("kilometraje mínimo");
    	
    	
    	
		int[] idSucursales = new int[sucursales.size()]; 
		
		for (int i = 0; i < categorias.size(); i++) {
    		Categoria aux=categorias.get(i);
    		nomCategorias.add(aux.getNombre());
    	}

    	for (int i = 0; i < sucursales.size(); i++) {
    		Sucursal aux=sucursales.get(i);
    		direcciones.add(aux.getDireccion());
    		idSucursales[i]= aux.getIdSucursal();
    	}
    	ObservableList<String> categoriasOL = FXCollections.observableList(nomCategorias);
    	ObservableList<String> modalidadesOL = FXCollections.observableList(modalidadesAlquiler);

		categoria.setItems(categoriasOL); 
		categoria.getSelectionModel().selectedIndexProperty()
				.addListener(new ChangeListener<Number>() {
					public void changed(ObservableValue ov, Number value,Number new_value){
						categoriaNom=nomCategorias.get(new_value.intValue());
					}
				});
		
		
		modalidadAlquiler.setItems(modalidadesOL); 
		modalidadAlquiler.getSelectionModel().selectedIndexProperty()
				.addListener(new ChangeListener<Number>() {
					public void changed(ObservableValue ov, Number value,Number new_value){
						mod=modalidades.get(new_value.intValue());
					}
				});
		
		
    	ObservableList<String> direccionesSuc = FXCollections.observableList(direcciones);

		direccionDevolucion.setItems(direccionesSuc); 
		direccionDevolucion.getSelectionModel().selectedIndexProperty()
				.addListener(new ChangeListener<Number>() {
					public void changed(ObservableValue ov, Number value,Number new_value){
						idSucursalDev=idSucursales[new_value.intValue()]; 
					}
				});
		
		direccionRecogida.setItems(direccionesSuc); 
		direccionRecogida.getSelectionModel().selectedIndexProperty()
				.addListener(new ChangeListener<Number>() {
					public void changed(ObservableValue ov, Number value,Number new_value){
						idSucursalRec=idSucursales[new_value.intValue()];
					}
				});
		} catch (DAOExcepcion e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
        aceptar.setOnAction(event -> {
            try {
                if (isInputValid()) {
                         	
                    // DATOS

                    String DNI = dni.getText();
                	cRealiza=AlquilerVehiculos.getSingleton().getCliente(DNI);
                    LocalDateTime fecRec = fechaRecogida.getValue().atStartOfDay();
                    LocalDateTime fecDev = fechaDevolucion.getValue().atStartOfDay();         
                    Sucursal recogida = AlquilerVehiculos.getSingleton().getSucursal(idSucursalRec);
                    Sucursal devolucion = AlquilerVehiculos.getSingleton().getSucursal(idSucursalDev);
                    Categoria categoria=AlquilerVehiculos.getSingleton().getCategoria(categoriaNom);
                    
                    
                    AlquilerVehiculos.getSingleton().crearReserva(fecRec,fecDev,mod,cRealiza,categoria,recogida,devolucion);
                    stage.close();
                }// fin isInputValid
            } catch (DAOExcepcion e) {
                e.printStackTrace();
            }
        });
        
    }

    @FXML
    private void cerrar() {
        stage.close();
    }

    private boolean isInputValid() throws NumberFormatException, DAOExcepcion {
        String errorMessage = "";
        LocalDateTime hoy= LocalDateTime.now();
        Sucursal dev=AlquilerVehiculos.getSingleton().getSucursal(idSucursalDev); 
        Sucursal rec=AlquilerVehiculos.getSingleton().getSucursal(idSucursalRec);
        //fecRec,fecDev,mAlq,cRealiza,categoria,recogida,devolucion
        //DNI,CATEGORIA,MODALIDAD,DIRRECOGIDA,FECHAREC,DIRDEVOLUCION,FECHADEVOLUCION
        
            if (AlquilerVehiculos.getSingleton().getCliente(dni.getText()) == null) {
                errorMessage += "El DNI introducido no está en nuestra base de datos.\n";
            }
            if (dni.getText().length() == 0|| dni.getText().length() > 10) {
                errorMessage += "DNI introducido no válido\n";
            } 
            
            if (categoriaNom== "") {
                errorMessage += "Categoria no seleccionada.\n";
            }
            if (mod == "") {
                errorMessage += "Modalidad no seleccionada.\n";
            }
            if (rec == null) {
                errorMessage += "La sucursal de recogida no ha sido seleccionada.\n";
            }
            if (fechaRecogida.getValue() == null) {
                errorMessage += "Seleccione una fecha de recogida.\n";
            }
            if (dev == null) {
                errorMessage += "La sucursal de devolución no ha sido seleccionada.\n";
            }
            if (fechaDevolucion.getValue() == null) {
                errorMessage += "Seleccione una fecha de devolucion.\n";
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
    public boolean esInt ( String s ) {
        try {
            Integer.parseInt( s );
            return true;
        }
        catch( NumberFormatException e ) {
            return false;
        }
    }
}