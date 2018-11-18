package csekosys.stockregistry.ui.part.add;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import csekosys.stockregistry.data.model.Part;
import csekosys.stockregistry.data.model.PartCategory;
import csekosys.stockregistry.database.DatabaseHandler;
import csekosys.stockregistry.database.DatabaseHelper;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PartAddController implements Initializable {

    private DatabaseHandler databaseHandler;
    private ObservableList<PartCategory> partCategoriesList = FXCollections.observableArrayList();

    @FXML
    private JFXComboBox<PartCategory> cashregisterCategoryComboBox;
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
//        int cashregisterTypeId = 1;
        int cashregisterTypeId = cashregisterCategoryComboBox.getSelectionModel().getSelectedItem().getId();
        System.out.println("csekosys.stockregistry.ui.part.add.PartAddController.addPart() cashregisterTypeId: " + cashregisterTypeId);
        String name = nameTextField.getText();
        String barcode = barcodeTextField.getText();
        String place = palceTextField.getText();
        String comment = commentTextArea.getText();

        Part part = new Part(cashregisterTypeId, name, barcode, place, comment);
        DatabaseHelper.insertNewPart(part);
    }

    private void initPartCategoriesComboBox() {
        partCategoriesList = DatabaseHelper.findAllPartCategories();
        cashregisterCategoryComboBox.setItems(partCategoriesList);

    }

    private void loadPartCategory() {


        /*
        String query = "SELECT * FROM part_categories";
        ResultSet rs = databaseHandler.execQuery(query);

        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String comment = rs.getString("comment");

                partCategoriesList.add(new PartCategory(id, name, comment));
            }
        } catch (SQLException ex) {
            System.err.println("Hiba a loadPartCategory közben: " + ex);
        }
        
        System.out.println("csekosys.stockregistry.ui.part.add.PartAddController.loadPartCategory() MÉRET: "  + partCategoriesList.size());
         */
    }

    private void cancel() {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    private void clearFields() {
        //       cashregisterCategoryComboBox.
        nameTextField.clear();
        barcodeTextField.clear();
        palceTextField.clear();
        commentTextArea.clear();
    }

}
