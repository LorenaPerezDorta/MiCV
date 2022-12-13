package dad.javafx.micv.formacion;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.javafx.micv.model.Titulo;
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

public class AddFormacionController extends Dialog<Titulo> implements Initializable {

    @FXML
    private GridPane view;

    @FXML
    private Label hastalabel;

    @FXML
    private Label denominacionlabel;

    @FXML
    private Label organizadorlabel;

    @FXML
    private Label desdelabel;

    @FXML
    private TextField textfieldDenomacion;

    @FXML
    private TextField textfieldOrganizador;

    @FXML
    private DatePicker datapickerdesde;

    @FXML
    private DatePicker datapickerhasta;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		setTitle("Nuevo Titulo");
		
		getDialogPane().setContent(view);
		
		getDialogPane().getButtonTypes().addAll(new ButtonType("Crear", ButtonData.OK_DONE), ButtonType.CANCEL);
		
		setResultConverter(d -> onAddTitulo(d));
		
		// Get the Stage.
		Stage stage = (Stage) getDialogPane().getScene().getWindow();

		// Add a custom icon.
		stage.getIcons().add(new Image(this.getClass().getResource("/images/cv64x64.png").toString()));
	}
	private Titulo onAddTitulo(ButtonType buttonType) {
    	if (buttonType.getButtonData() == ButtonData.OK_DONE) {
    		Titulo titulo = new Titulo();
    		titulo.setDenominacion(textfieldDenomacion.textProperty().get());
    		titulo.setOrganizador(textfieldOrganizador.textProperty().get());
    		titulo.setDesde(datapickerdesde.getValue());
    		titulo.setHasta(datapickerhasta.getValue());     	
    		return titulo;
    	}
    	return null;
    }
	public AddFormacionController() {
		super();
		// TODO Auto-generated constructor stub
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AddformacionView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

