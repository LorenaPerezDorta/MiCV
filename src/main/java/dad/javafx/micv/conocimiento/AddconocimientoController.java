package dad.javafx.micv.conocimiento;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.javafx.micv.model.Nivel;
import dad.javafx.micv.model.Conocimiento;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AddconocimientoController extends Dialog <Conocimiento> implements Initializable{

	

	public AddconocimientoController() {
		super();
		// TODO Auto-generated constructor stub
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AddconocimientoView.fxml"));
		loader.setController(this);
		try {
			loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	    private GridPane view;

	    @FXML
	    private Label labeldenominacion;

	    @FXML
	    private Label labelnivel;

	    @FXML
	    private TextField denominacionTextfield;

	    @FXML
	    private HBox hboxnivel;

	    @FXML
	    private ComboBox<Nivel> nivelcombobox;

	    @FXML
	    private Button deletenivelButton;

	    @FXML
	    void onDeleteNivelAction(ActionEvent event) {
			nivelcombobox.valueProperty().set(null);

	    }
	    @Override
		public void initialize(URL location, ResourceBundle resources) {
			// TODO Auto-generated method stub
			nivelcombobox.getItems().addAll(Nivel.values());
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
			// TODO Auto-generated method stub
			  if (buttonType.getButtonData() == ButtonData.OK_DONE) {
		    		Conocimiento conocimiento = new Conocimiento();
		    		conocimiento.setDenominacion(denominacionTextfield.textProperty().get());
		    		conocimiento.setNivel(nivelcombobox.getValue());
		    		return conocimiento;
		    	}
			return null;
		}
}
