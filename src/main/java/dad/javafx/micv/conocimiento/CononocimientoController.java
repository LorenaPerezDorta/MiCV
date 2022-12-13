package dad.javafx.micv.conocimiento;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.javafx.micv.model.Conocimiento;
import dad.javafx.micv.model.Nivel;
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
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CononocimientoController implements Initializable{

	private ListProperty<Conocimiento> habilidades = new SimpleListProperty<Conocimiento>(FXCollections.observableArrayList());

	
    public CononocimientoController() throws IOException {
		super();
		// TODO Auto-generated constructor stub
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ConocimientoView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@FXML
    private HBox view;

    @FXML
    private TableView<Conocimiento> experienciatableview;

    @FXML
    private TableColumn<Conocimiento, String> denominacioncolumn;

    @FXML
    private TableColumn<Conocimiento, Nivel> nivelcolumn;

    @FXML
    private VBox vboxconocimiento;

    @FXML
    private Button AddConocimientobutton;

    @FXML
    private Button addIdiomaButton;

    @FXML
    private Button deleteConocimientobutton;

    @FXML
    void onAddConocimientoAction(ActionEvent event) {
    	AddconocimientoController dialog = new AddconocimientoController();
		Optional<Conocimiento> result = dialog.showAndWait();
		if (result.isPresent()) {
			System.out.println(result.get());
			getHabilidades().add(result.get());
		}
    	
    }

    @FXML
    void onAddIdiomaAction(ActionEvent event) {
    	AddidiomaController dialog = new AddidiomaController();
		Optional<Conocimiento> result = dialog.showAndWait();
		if (result.isPresent()) {
			System.out.println(result.get());
			getHabilidades().add(result.get());
		}

    }

    @FXML
    void onDeleteConocimientoAction(ActionEvent event) {
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
		denominacioncolumn.setCellValueFactory(v -> v.getValue().denominacionProperty());
		nivelcolumn.setCellValueFactory(v -> v.getValue().nivelProperty());
		denominacioncolumn.setCellFactory(TextFieldTableCell.forTableColumn());
		nivelcolumn.setCellFactory(ComboBoxTableCell.forTableColumn(Nivel.values()));	
		experienciatableview.itemsProperty().bind(habilidades);
	}

	public final ListProperty<Conocimiento> habilidadesProperty() {
		return this.habilidades;
	}
	

	public final ObservableList<Conocimiento> getHabilidades() {
		return this.habilidadesProperty().get();
	}
	

	public final void setHabilidades(final ObservableList<Conocimiento> habilidades) {
		this.habilidadesProperty().set(habilidades);
	}

	public HBox getView() {
		return view;
	}

	public void setView(HBox view) {
		this.view = view;
	}
	

}
