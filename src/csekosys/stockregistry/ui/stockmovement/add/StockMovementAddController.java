package csekosys.stockregistry.ui.stockmovement.add;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import csekosys.stockregistry.data.model.CashregisterType;
import csekosys.stockregistry.data.model.Part;
import csekosys.stockregistry.data.model.PartCategory;
import csekosys.stockregistry.data.model.Partner;
import csekosys.stockregistry.data.model.StockMovement;
import csekosys.stockregistry.data.model.StockMovementItem;
import csekosys.stockregistry.data.model.StockMovementType;
import csekosys.stockregistry.database.DatabaseHandler;
import csekosys.stockregistry.database.DatabaseHelper;
import csekosys.stockregistry.tools.DialogMaker;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StockMovementAddController implements Initializable {

    private DatabaseHandler databaseHandler;
    private ObservableList<Partner> partnersList = FXCollections.observableArrayList();
    private ObservableList<StockMovementType> stockMovementTypesList = FXCollections.observableArrayList();
    private ObservableList<Part> partList = FXCollections.observableArrayList();

    SimpleDateFormat timestampFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat identificationDateFormat = new SimpleDateFormat("yyyyMMdd");
    @FXML
    private AnchorPane stockMovementPane;
    @FXML
    private JFXComboBox<StockMovementType> stockMovementTypeComboBox;
    @FXML
    private JFXComboBox<Partner> partnerComboBox;
    @FXML
    private JFXTextArea commentTextArea;
    @FXML
    private JFXTextField transferringTextField;
    @FXML
    private JFXTextField recipientTextField;
    @FXML
    private JFXTextField searchPartTextField;
    @FXML
    private TableView<Part> partListTable;
    @FXML
    private TableColumn<Part, String> partNameCol;
    @FXML
    private TableColumn<PartCategory, String> cashregisterTypeCol;
    @FXML
    private TableColumn<String, String> partFunctionsCol;
    @FXML
    private Text chosenPartNameLabel;
    @FXML
    private Spinner<Integer> addQuantitySpinner;
    @FXML
    private TableView<StockMovementItem> itemListTable;
    @FXML
    private TableColumn<Part, String> itemNameCol;
    @FXML
    private TableColumn<CashregisterType, String> itemCashregisterTypeCol;
    @FXML
    private TableColumn<Integer, Integer> itemQuantityCol;
    @FXML
    private TableColumn<String, String> itemFunctionsCol;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Date data = new Date();

        databaseHandler = DatabaseHandler.getInstance();

        initStockMovementTypesComboBox();
        initPartnersComboBox();

        addQuantitySpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000));

        System.out.println("timestamp: " + timestampFormat.format(data));
        System.out.println("identification: " + identificationDateFormat.format(data));

        initPartsTable();

//        initIdentification();
    }

    @FXML
    private void handleAddStockMovementAction(ActionEvent event) {
    }

    @FXML
    private void handleAddPartnerAction(ActionEvent event) {
    }

    @FXML
    private void hadndleSearchPartAction(ActionEvent event) {
    }

    @FXML
    private void handleAddItemAction(ActionEvent event) {
    }

    @FXML
    private void handelDeleteAllItemAction(ActionEvent event) {
    }

    @FXML
    private void handleCancelAction(ActionEvent event) {
        cancel();
    }

    private void initPartnersComboBox() {
        partnersList = DatabaseHelper.findAllPartners();
        partnerComboBox.setItems(partnersList);
    }

    private void initStockMovementTypesComboBox() {
        stockMovementTypesList = DatabaseHelper.findAllStockMovementTypes();
        stockMovementTypeComboBox.setItems(stockMovementTypesList);
    }

    private void cancel() {
        Stage stage = (Stage) stockMovementPane.getScene().getWindow();
        stage.close();
    }

    private void initIdentification() {
        String prefix = stockMovementTypeComboBox.getSelectionModel().getSelectedItem().getPrefix();
        StockMovement lastStockMovement = DatabaseHelper.getLastStockMovement(prefix);

        System.out.println("csekosys.stockregistry.ui.stockmovement.add.StockMovementAddController.initIdentification(): " + lastStockMovement);

        if (lastStockMovement == null) {

        } else {
            String identification = lastStockMovement.getIdentification();
            String[] parts = identification.split("-");
            String identificationPrefix = parts[0];
            String identificationDate = parts[1];
            String identificationCount = parts[2];
        }
    }

    private void initPartsTable() {
        partList.clear();
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        cashregisterTypeCol.setCellValueFactory(new PropertyValueFactory<>("partCategoryName"));
        partList = DatabaseHelper.selecktStockMovementPartList();
        partListTable.setItems(partList);
        
        partListTable.getSelectionModel().
    }

    private void selectPartInTable() {
        Part selecktedPart = partListTable.getSelectionModel().getSelectedItem();

        if (selecktedPart == null) {
            System.out.println("csekosys.stockregistry.ui.stockmovement.add.StockMovementAddController.selectPartInTable()" + " Nincs kiválasztva alkatrész!");
        } else {
            chosenPartNameLabel.setText(selecktedPart.getName());
        }
    }

}
