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
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
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
    private ObservableList<StockMovementItem> itemList = FXCollections.observableArrayList();

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
        databaseHandler = DatabaseHandler.getInstance();
        Date date= new Date();
        addQuantitySpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000));

        initStockMovementTypesComboBox();
        initPartnersComboBox();
    }

    @FXML
    private void handleAddStockMovementAction(ActionEvent event) {
        testStockMovementSave();
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

    private void testItemList() {
        itemList.clear();
        itemNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        itemCashregisterTypeCol.setCellValueFactory(new PropertyValueFactory<>("partCategoryName"));
        itemQuantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    }

    private void testStockMovementSave() {
        int stockMovementTypeId = 1;
        String stockMovementTypePrefix = "TTT";
        String identification = initIdentification(stockMovementTypePrefix);
        Date dateX = new Date();
        String stockMovementcereatedDate = timestampFormat.format(dateX);
        
        boolean newItem = true;
        boolean goodItem = true;
        int partId = 1;
        String comment = "comment 1";
        String transfering = "test átadó";
        String recipient = "teszt átvevő";    
        
        for (int i = 0; i < 10; i++) {
            itemList.add(new StockMovementItem(1, 1, 1, 1, i));
        }
        StockMovement stockMuvement = new StockMovement(identification, partId, stockMovementTypeId, transfering, recipient, comment, stockMovementcereatedDate);       
        DatabaseHelper.insertStockMovement(stockMuvement);
        
        for (StockMovementItem item : itemList) {
            DatabaseHelper.insertStockMovementItems(item);
        }
        
    }
    
    private void testSave() {
        int stockMovementTypeId = stockMovementTypeComboBox.getSelectionModel().getSelectedItem().getId();
        String stockMovementTypePrefix = stockMovementTypeComboBox.getSelectionModel().getSelectedItem().getPrefix();
        String identification = initIdentification(stockMovementTypePrefix);
        
        boolean newItem = stockMovementTypeComboBox.getSelectionModel().getSelectedItem().isNewPart();
        boolean goodItem = stockMovementTypeComboBox.getSelectionModel().getSelectedItem().isGoodPart();
        int partId = partnerComboBox.getSelectionModel().getSelectedItem().getId();
        String comment = commentTextArea.getText();
        String transfering = transferringTextField.getText();
        String recipient = recipientTextField.getText();

        partList.clear();
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        cashregisterTypeCol.setCellValueFactory(new PropertyValueFactory<>("partCategoryName"));
        partList = DatabaseHelper.selecktStockMovementPartList();
        partListTable.setItems(partList);

        partListTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                chosenPartNameLabel.setText(newSelection.getName());
                int selecktedItemId = newSelection.getId();
                String selecktedItemName = newSelection.getName();
                int partCategoryId = newSelection.getPartCategoryId();
            }
        });

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

    private String initIdentification(String prefix) {
        return prefix + "-20181129-001";
    }

    private void initPartsTable() {
        partList.clear();
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        cashregisterTypeCol.setCellValueFactory(new PropertyValueFactory<>("partCategoryName"));
        partList = DatabaseHelper.selecktStockMovementPartList();
        partListTable.setItems(partList);

    }

}
