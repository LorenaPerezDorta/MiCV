package dad.javafx.micv.experiencia;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.javafx.micv.model.Experiencia;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;

public class ExperienciaController implements Initializable{

	private ListProperty<Experiencia> experiencia = new SimpleListProperty<Experiencia>(FXCollections.observableArrayList());

	
    public ExperienciaController() throws IOException {
		super();
		// TODO Auto-generated constructor stub
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ExperienciaView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@FXML
    private HBox view;

    @FXML
    private TableView<Experiencia> experienciatableview;

    @FXML
    private TableColumn<Experiencia,LocalDate> desdecolumn;

    @FXML
    private TableColumn<Experiencia,LocalDate> hastacolumn;

    @FXML
    private TableColumn<Experiencia, String> denominacioncolumn;

    @FXML
    private TableColumn<Experiencia, String> empleadorcolumn;

    @FXML
    private VBox vboxexperiencia;

    @FXML
    private Button addExperienciaButton;

    @FXML
    private Button deleteExperienciabutton;

    @FXML
    void onAddExperienciaAction(ActionEvent event) {
    	AddExperienciaController dialog = new AddExperienciaController();
		Optional<Experiencia> result = dialog.showAndWait();
		if (result.isPresent()) {
			System.out.println(result.get());
			getExperiencia().add(result.get());
		}

    }

    @FXML
    void onDeleteExperienciaAction(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Eliminar");
		alert.setHeaderText("¿Seguro que deseas borrar este registro?");
		alert.setContentText("¿Estas seguro?");
		// Get the Stage.
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();

		// Add a custom icon.
		stage.getIcons().add(new Image(this.getClass().getResource("/images/cv64x64.png").toString()));
		
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			// ... user chose OK
			experienciatableview.getItems().removeAll(experienciatableview.getSelectionModel().getSelectedItem());
		} else {
			// ... user chose CANCEL or closed the dialog
		}

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		desdecolumn.setCellValueFactory(v -> v.getValue().desdeProperty());
		hastacolumn.setCellValueFactory(v -> v.getValue().hastaProperty());
		denominacioncolumn.setCellValueFactory(v -> v.getValue().denominacionProperty());
		empleadorcolumn.setCellValueFactory(v -> v.getValue().empleadorProperty());

		desdecolumn.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
		hastacolumn.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
		denominacioncolumn.setCellFactory(TextFieldTableCell.forTableColumn());
		empleadorcolumn.setCellFactory(TextFieldTableCell.forTableColumn());
		
		experienciatableview.itemsProperty().bind(experiencia);
		deleteExperienciabutton.disableProperty().bind(Bindings.isEmpty(experiencia));

	}

	public HBox getView() {
		return view;
	}

	public void setView(HBox view) {
		this.view = view;
	}

	public final ListProperty<Experiencia> experienciaProperty() {
		return this.experiencia;
	}
	

	public final ObservableList<Experiencia> getExperiencia() {
		return this.experienciaProperty().get();
	}
	

	public final void setExperiencia(final ObservableList<Experiencia> experiencia) {
		this.experienciaProperty().set(experiencia);
	}
	
	
}

