package dad.javafx.micv.experiencia;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.javafx.micv.model.Experiencia;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AddExperienciaController extends Dialog<Experiencia> implements Initializable{

	
	public AddExperienciaController() {
		super();
		// TODO Auto-generated constructor stub
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AddexperienciaView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		setTitle("Nuevo Titulo");
		
		getDialogPane().setContent(view);
		
		getDialogPane().getButtonTypes().addAll(new ButtonType("Crear", ButtonData.OK_DONE), ButtonType.CANCEL);
		
		setResultConverter(d -> onAddExperiencia(d));
		
		// Get the Stage.
		Stage stage = (Stage) getDialogPane().getScene().getWindow();

		// Add a custom icon.
		stage.getIcons().add(new Image(this.getClass().getResource("/images/cv64x64.png").toString()));
		
	}
	private Experiencia onAddExperiencia(ButtonType buttonType) {
    	if (buttonType.getButtonData() == ButtonData.OK_DONE) {
    		Experiencia experiencia = new Experiencia();
    		experiencia.setDenominacion(textfieldDenomacion.textProperty().get());
    		experiencia.setEmpleador(textfieldEmpleador.textProperty().get());
    		experiencia.setDesde(datapickerdesde.getValue());
    		experiencia.setHasta(datapickerhasta.getValue());     	
    		return experiencia;
    	}
    	return null;
	}
		@FXML
	    private GridPane view;

	    @FXML
	    private Label hastalabel;

	    @FXML
	    private Label denominacionlabel;

	    @FXML
	    private Label empleadorlabel;

	    @FXML
	    private Label desdelabel;

	    @FXML
	    private TextField textfieldDenomacion;

	    @FXML
	    private TextField textfieldEmpleador;

	    @FXML
	    private DatePicker datapickerdesde;

	    @FXML
	    private DatePicker datapickerhasta;
	    
}
