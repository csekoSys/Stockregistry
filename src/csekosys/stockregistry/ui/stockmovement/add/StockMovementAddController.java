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
        Date date = new Date();
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

        boolean isSelecktedStockMovementType = stockMovementTypeComboBox.getSelectionModel().isEmpty();
        boolean isPartner = partnerComboBox.getSelectionModel().isEmpty();
        boolean isTransfering = transferringTextField.getText().isEmpty();
        boolean isRecipient = recipientTextField.getText().isEmpty();

        boolean flag = isSelecktedStockMovementType && isPartner && isTransfering && isRecipient;

        if (!flag) {
            int stockMovementTypeId = stockMovementTypeComboBox.getSelectionModel().getSelectedItem().getId();
            String stockMovementTypePrefix = stockMovementTypeComboBox.getSelectionModel().getSelectedItem().getPrefix();
            String identification = createdIdentification(stockMovementTypePrefix);
            int partnerId = partnerComboBox.getSelectionModel().getSelectedItem().getId();
            boolean newItem = stockMovementTypeComboBox.getSelectionModel().getSelectedItem().isNewPart();
            boolean goodItem = stockMovementTypeComboBox.getSelectionModel().getSelectedItem().isGoodPart();
            String comment = commentTextArea.getText();
            String transfering = transferringTextField.getText();
            String recipient = recipientTextField.getText();

            Date dateX = new Date();
            String stockMovementcereatedDate = timestampFormat.format(dateX);

            StockMovement stockMuvement = new StockMovement(identification, partnerId, stockMovementTypeId, transfering, recipient, comment, stockMovementcereatedDate);
            DatabaseHelper.insertStockMovement(stockMuvement);

        } else {
            DialogMaker.showErrorAlert("Hiba", null, "Minden * jelölt mező kitöltése kötelező!");
        }

        for (int i = 0; i < 1; i++) {
            itemList.add(new StockMovementItem(1, 1, 1, 1, 10));
        }

        for (StockMovementItem item : itemList) {
            DatabaseHelper.insertStockMovementItems(item);
        }

    }

    private void testSave() {
        int stockMovementTypeId = stockMovementTypeComboBox.getSelectionModel().getSelectedItem().getId();
        String stockMovementTypePrefix = stockMovementTypeComboBox.getSelectionModel().getSelectedItem().getPrefix();
        String identification = createdIdentification(stockMovementTypePrefix);

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

    /**
     * Megkeresi az utolsó egyező prefix-ű azonosítót az adott napon és növeli a
     * számlálót vagy újat hoz létre
     *
     * @param prefix
     * @return
     */
    private String createdIdentification(String prefix) {
        String lastStockMovement = DatabaseHelper.getLastStockMovementIdentification(prefix);

        System.out.println("csekosys.stockregistry.ui.stockmovement.add.StockMovementAddController.initIdentification()");
        System.out.println("prefix: " + prefix);
        System.out.println("lastStockMovement: " + lastStockMovement);

        Date dateNow = new Date();
        String identificationDateNow = identificationDateFormat.format(dateNow);

        String newIdentification = "";

        if (lastStockMovement == "" || lastStockMovement.isEmpty()) {
            newIdentification = prefix + "-" + identificationDateNow + "-" + "001";
        } else {
            String identification = lastStockMovement;
            String[] parts = identification.split("-");
            String identificationPrefix = parts[0];
            String identificationDate = parts[1];
            String identificationCount = parts[2];

            if (identificationDateNow.equals(identificationDate)) {
                int identificationCountInt = Integer.parseInt(identificationCount);

                if (identificationCountInt < 10) {
                    newIdentification = prefix + "-" + identificationDate + "-00" + (identificationCountInt + 1);
                } else if (identificationCountInt < 100) {
                    newIdentification = prefix + "-" + identificationDate + "-0" + (identificationCountInt + 1);
                } else {
                    newIdentification = prefix + "-" + identificationDate + "-" + (identificationCountInt + 1);
                }
            } else {
                newIdentification = prefix + "-" + identificationDateNow + "-" + "001";
            }

        }
        return newIdentification;

    }

    private void initPartsTable() {
        partList.clear();
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        cashregisterTypeCol.setCellValueFactory(new PropertyValueFactory<>("partCategoryName"));
        partList = DatabaseHelper.selecktStockMovementPartList();
        partListTable.setItems(partList);
    }

}
