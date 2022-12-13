package dad.javafx.micv.conocimiento;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.javafx.micv.model.Conocimiento;
import dad.javafx.micv.model.Idioma;
import dad.javafx.micv.model.Nivel;
import javafx.fxml.Initializable;
import javafx.scene.control.Dialog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class AddidiomaController extends Dialog <Conocimiento> implements Initializable{
	
	
	public AddidiomaController() {
		super();
		// TODO Auto-generated constructor stub
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AddidiomaView.fxml"));
		loader.setController(this);
		try {
			loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		comboboxnivel.getItems().addAll(Nivel.values());
		setTitle("Nuevo conocimiento");
		getDialogPane().setContent(view);
		getDialogPane().getButtonTypes().addAll(new ButtonType("Crear", ButtonData.OK_DONE), ButtonType.CANCEL);
		setResultConverter(d -> onAddConocimiento(d));
		// Get the Stage.
				Stage stage = (Stage) getDialogPane().getScene().getWindow();

				// Add a custom icon.
				stage.getIcons().add(new Image(this.getClass().getResource("/images/cv64x64.png").toString()));
	}
	private Conocimiento onAddConocimiento(ButtonType buttonType) {
    	if (buttonType.getButtonData() == ButtonData.OK_DONE) {
    		Conocimiento conocimiento = new Conocimiento();
    		conocimiento.setDenominacion(denominacionTextfield.textProperty().get());
    		conocimiento.setNivel(comboboxnivel.getValue());
    		Idioma idioma = new Idioma();
    		idioma.setCertificacion(certificacionTextfield.textProperty().get());
    		conocimiento.setIdioma(idioma);
    		return conocimiento;
    	}
    	return null;
	}

    @FXML
    private GridPane view;

    @FXML
    private ComboBox<Nivel> comboboxnivel;

    @FXML
    private Button deletenivelButton;

    @FXML
    private TextField certificacionTextfield;

    @FXML
    private TextField denominacionTextfield;

    @FXML
    void onDeletenivelAction(ActionEvent event) {
    	comboboxnivel.valueProperty().set(null);

    }

}