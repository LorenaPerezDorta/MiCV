package dad.javafx.micv;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.hildan.fxgson.FxGson;

import com.google.gson.Gson;

import dad.javafx.micv.conocimiento.CononocimientoController;
import dad.javafx.micv.contacto.ContactoController;
import dad.javafx.micv.experiencia.ExperienciaController;
import dad.javafx.micv.formacion.FormacionController;

import dad.javafx.micv.model.CV;
import dad.javafx.micv.personal.PersonalController;
import dad.javafx.micv.utils.JSONUtils;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class MainController implements Initializable {
	
	//variable para que sea posible guardar el fichero abierto 
	String openfile;

	// controllers
	
	private PersonalController personalController = new PersonalController();
	private ContactoController contactoController = new ContactoController();
	private FormacionController formacionController = new FormacionController();
	private ExperienciaController experienciaController = new ExperienciaController();
	private CononocimientoController conocimientosController = new CononocimientoController();


	// model
	
	private ObjectProperty<CV> cv = new SimpleObjectProperty<>();
	
	// view

    @FXML
    private BorderPane view;

    @FXML
    private Tab personalTab;

    @FXML
    private Tab contactoTab;

    @FXML
    private Tab formacionTab;

    @FXML
    private Tab experienciaTab;

    @FXML
    private Tab conocimientosTab;
	
	public MainController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
		loader.setController(this);
		loader.load();
	}
	
	public BorderPane getView() {
		return view;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		personalTab.setContent(personalController.getView()); //recuerda generar setters y getter de la view en el controller
		contactoTab.setContent(contactoController.getView());
		formacionTab.setContent(formacionController.getView());
		experienciaTab.setContent(experienciaController.getView());
		conocimientosTab.setContent(conocimientosController.getView());

		cv.addListener((o, ov, nv) -> onCVChanged(o, ov, nv));
		
		cv.set(new CV());
		
	}

    private void onCVChanged(ObservableValue<? extends CV> o, CV ov, CV nv) {
    	
    	if (ov != null) {
    		
    		personalController.personalProperty().unbind(); // desbindeo personalProperty del CV anterior
			contactoController.contactoProperty().unbind();
			formacionController.formacionProperty().unbind();
			experienciaController.experienciaProperty().unbind();//no te olvides de generar getters y setters de javafx
			conocimientosController.habilidadesProperty().unbind();
    		// desbindear resto de controllers
    		
    	}

    	if (nv != null) {
    		
    		personalController.personalProperty().bind(nv.personalProperty()); // bindeo personalProperty del nuevo CV
			contactoController.contactoProperty().bind(nv.contactoProperty());
			formacionController.formacionProperty().bind(nv.formacionProperty());
			experienciaController.experienciaProperty().bind(nv.experienciaProperty()); //crear list en la clase CV y generar getters y setters
			conocimientosController.habilidadesProperty().bind(nv.habilidadesProperty());

    		// bindear resto de controllers
    		
    	}
    	
	}

	@FXML
    void onAbrirAction(ActionEvent event) {

    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Abrir un curriculum");
    	fileChooser.getExtensionFilters().add(new ExtensionFilter("Curriculum Vitae (*.cv)", "*.cv"));
    	fileChooser.getExtensionFilters().add(new ExtensionFilter("Todos los archivos", "*.*"));
    	File cvFile = fileChooser.showOpenDialog(App.getPrimaryStage());
    	if (cvFile != null) {
    		try {
				cv.set(JSONUtils.fromJson(cvFile, CV.class));
				openfile = cvFile.getAbsolutePath();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
		
    }

    @FXML
    void onAcercaDeAction(ActionEvent event) {

    }

    @FXML
    void onGuardarAction(ActionEvent event) {
    	if (openfile != null) {
			File cvFile = new File(openfile);
			try {
				cv.set(JSONUtils.fromJson(cvFile, CV.class));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

    	}	else {
			onGuardarComoAction(event);
		}		
    }

    @FXML
    void onGuardarComoAction(ActionEvent event) {

    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Guardar un curriculum");
    	fileChooser.getExtensionFilters().add(new ExtensionFilter("Curriculum Vitae (*.cv)", "*.cv"));
    	fileChooser.getExtensionFilters().add(new ExtensionFilter("Todos los archivos", "*.*"));
    	File cvFile = fileChooser.showSaveDialog(App.getPrimaryStage());
    	if (cvFile != null) {
    		try {
				JSONUtils.toJson(cvFile, cv.get());
			} catch (IOException e) {
				e.printStackTrace();
			}
    		
    	}
    	
    }

    @FXML
    void onNuevoAction(ActionEvent event) {
    	System.out.println("Has pulsado nuevo");
    	cv.set(new CV());
    	openfile = null;
    }

    @FXML
    void onSalirAction(ActionEvent event) {
    	//ventana de alerta al salir 
    	Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Dialogo de confirmacion");
		alert.setHeaderText("Vas a cerrar el programa");
		alert.setContentText("¿Estás seguro?");
		// Get the Stage.
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();

		// Add a custom icon.
		stage.getIcons().add(new Image(this.getClass().getResource("/images/cv64x64.png").toString()));
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			Platform.exit();
		} else {
			// ... user chose CANCEL or closed the dialog
		}
    }
	
}
