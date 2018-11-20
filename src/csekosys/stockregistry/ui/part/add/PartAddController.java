package csekosys.stockregistry.ui.part.add;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import csekosys.stockregistry.data.model.CashregisterType;
import csekosys.stockregistry.data.model.Part;
import csekosys.stockregistry.data.model.PartCategory;
import csekosys.stockregistry.database.DatabaseHandler;
import csekosys.stockregistry.database.DatabaseHelper;
import csekosys.stockregistry.tools.DialogMaker;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PartAddController implements Initializable {

    private DatabaseHandler databaseHandler;
    private ObservableList<PartCategory> partCategoriesList = FXCollections.observableArrayList();

    @FXML
    private JFXTextField nameTextField;
    @FXML
    private JFXTextField barcodeTextField;
    @FXML
    private JFXTextField palceTextField;
    @FXML
    private JFXTextArea commentTextArea;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private JFXComboBox<PartCategory> partCategoryComboBox;
    @FXML
    private JFXButton addAndCancelButton;
    @FXML
    private JFXButton addAndNewButton;
    @FXML
    private JFXButton cancelButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        databaseHandler = DatabaseHandler.getInstance();
        initPartCategoriesComboBox();
    }

    @FXML
    private void handleAddAndCancelAction(ActionEvent event) {
        addPart();
        cancel();
    }

    @FXML
    private void handleAddAndNewAction(ActionEvent event) {
        addPart();
        clearFields();
        initPartCategoriesComboBox();
    }

    @FXML
    private void hangleCancelAction(ActionEvent event) {
        cancel();
    }

    @FXML
    private void handleAddNewPartCategoryAction(ActionEvent event) {

        initPartCategoriesComboBox();
    }

    private void addPart() {

        boolean isSelecktedPartType = partCategoryComboBox.getSelectionModel().isEmpty();

        String name = nameTextField.getText();
        String barcode = barcodeTextField.getText();
        String place = palceTextField.getText();
        String comment = commentTextArea.getText();

        boolean flag = isSelecktedPartType || name.isEmpty();

        if (flag) {
            DialogMaker.showErrorAlert("Hiba", null, "A csilagozot mezők kitöltése kötelező!");
        } else {
            int cashregisterTypeId = partCategoryComboBox.getSelectionModel().getSelectedItem().getId();
            Part part = new Part(cashregisterTypeId, name, barcode, place, comment);
            DatabaseHelper.insertNewPart(part);
        }

    }

    private void initPartCategoriesComboBox() {
        partCategoriesList = DatabaseHelper.findAllPartCategories();
        partCategoryComboBox.setItems(partCategoriesList);

    }

    private void cancel() {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    private void clearFields() {
        partCategoryComboBox.getSelectionModel().clearSelection();
        partCategoryComboBox.getItems().clear();
        nameTextField.clear();
        barcodeTextField.clear();
        palceTextField.clear();
        commentTextArea.clear();
    }

}
