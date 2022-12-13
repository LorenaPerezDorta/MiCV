package dad.javafx.micv.contacto;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.javafx.micv.model.Telefono;
import dad.javafx.micv.model.TipoTelefono;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
public class AddtelefonoController extends Dialog <Telefono> implements Initializable{

	public AddtelefonoController() {
	super();
		// TODO Auto-generated constructor stub
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AddtelefonoView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
			}
		}
	@FXML
	private GridPane view;

	@FXML
	private TextField telefonotextfield;

	@FXML
	private ComboBox<TipoTelefono> addTipocombobox;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		addTipocombobox.getItems().addAll(TipoTelefono.values());
		setTitle("Nuevo telefono");
		setHeaderText("Introduzca el nuevo número de teléfono.");
		getDialogPane().setContent(view);
		getDialogPane().getButtonTypes().addAll(new ButtonType("Añadir", ButtonData.OK_DONE), ButtonType.CANCEL);
		setResultConverter(d -> onAddTelefonobutton(d));
		// Get the Stage.
		Stage stage = (Stage) getDialogPane().getScene().getWindow();

		// Add a custom icon.
		stage.getIcons().add(new Image(this.getClass().getResource("/images/cv64x64.png").toString()));
		}
	private Telefono onAddTelefonobutton(ButtonType buttonType) {
	    if (buttonType.getButtonData() == ButtonData.OK_DONE) {
	    	Telefono telefono = new Telefono();
	    	telefono.setNumero(telefonotextfield.textProperty().get());
	        telefono.setTipo(addTipocombobox.getValue());
	    	return telefono;
	    }
	    return null;
	 }

}
