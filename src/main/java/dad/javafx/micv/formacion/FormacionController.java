package dad.javafx.micv.formacion;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.javafx.micv.model.Titulo;
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

public class FormacionController implements Initializable{
	private ListProperty<Titulo> formacion = new SimpleListProperty<Titulo>(FXCollections.observableArrayList());


	public FormacionController() throws IOException {
		super();
		// TODO Auto-generated constructor stub
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/FormacionView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@FXML
    private HBox view;

    @FXML
    private TableView<Titulo> formaciontableview;

    @FXML
    private TableColumn<Titulo, LocalDate> desdecolumn;

    @FXML
    private TableColumn<Titulo, LocalDate> hastacolumn;

    @FXML
    private TableColumn<Titulo, String> denominacioncolumn;

    @FXML
    private TableColumn<Titulo, String> organizadorcolumn;

    @FXML
    private VBox vboxformacion;

    @FXML
    private Button addFormacionButton;

    @FXML
    private Button deleteformacionbutton;

    @FXML
    void onAddFormacionAction(ActionEvent event) {
    	AddFormacionController dialog = new AddFormacionController();
		Optional<Titulo> result = dialog.showAndWait();
		if (result.isPresent()) {
			System.out.println(result.get());
			getFormacion().add(result.get());
		}

    }

    @FXML
    void onDeleteFormacionAction(ActionEvent event) {
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
			formaciontableview.getItems().removeAll(formaciontableview.getSelectionModel().getSelectedItem());
		} else {
			// ... user chose CANCEL or closed the dialog
		}

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		formaciontableview.itemsProperty().bind(formacion);
		desdecolumn.setCellValueFactory(v -> v.getValue().desdeProperty());
		hastacolumn.setCellValueFactory(v -> v.getValue().hastaProperty());
		denominacioncolumn.setCellValueFactory(v -> v.getValue().denominacionProperty());
		organizadorcolumn.setCellValueFactory(v -> v.getValue().organizadorProperty());
		desdecolumn.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
		hastacolumn.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
		denominacioncolumn.setCellFactory(TextFieldTableCell.forTableColumn());
		organizadorcolumn.setCellFactory(TextFieldTableCell.forTableColumn());
		deleteformacionbutton.disableProperty().bind(Bindings.isEmpty(formacion));


	}	
	
	public final ListProperty<Titulo> formacionProperty() {
		return this.formacion;
	}
	
	public final ObservableList<Titulo> getFormacion() {
		return this.formacionProperty().get();
	}
	

	public final void setFormacion(final ObservableList<Titulo> formacion) {
		this.formacionProperty().set(formacion);
	}

	public HBox getView() {
		return view;
	}

	public void setView(HBox view) {
		this.view = view;
	}
	
}

