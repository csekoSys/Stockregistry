package csekosys.stockregistry.ui.part.list;

import csekosys.stockregistry.data.model.Part;
import csekosys.stockregistry.data.model.PartCategory;
import csekosys.stockregistry.database.DatabaseHandler;
import csekosys.stockregistry.database.DatabaseHelper;
import csekosys.stockregistry.tools.DialogMaker;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class PartListController implements Initializable {

    DatabaseHandler databaseHandler;
    ObservableList<Part> partList = FXCollections.observableArrayList();

    @FXML
    private TableView<Part> partsTable;
    @FXML
    private TableColumn<Part, Integer> idCol;
    @FXML
    private TableColumn<Part, String> nameCol;
    @FXML
    private TableColumn<PartCategory, String> partCategoryCol;
    @FXML
    private TableColumn<Part, String> placeCol;
    @FXML
    private TableColumn<String, String> functionsCol;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        loadPartData();
    }

    private void initCol() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partCategoryCol.setCellValueFactory(new PropertyValueFactory<>("partCategoryId"));
        placeCol.setCellValueFactory(new PropertyValueFactory<>("place"));
        //       functionsCol.setCellValueFactory(new PropertyValueFactory<>("id"));
    }

    private void loadPartData() {
//        partList.clear();
        databaseHandler = DatabaseHandler.getInstance();
        //       partList = DatabaseHelper.findAllParts();
        

        String query = "SELECT parts.id, parts.name, part_categories.name, parts.place "
                + "FROM parts"
                + "INNER JOIN part_categories"
                + "ON parts.part_category_id=part_categories.id";
        
        ResultSet rs = databaseHandler.execQuery(query);
        try {
            while (rs.next()) {
                int id = rs.getInt("id");
//                int partCategoryId = rs.getInt("part_category_id");
                String name = rs.getString("name");
 //               String barcode = rs.getString("barcode");
                String partCategoryName = rs.getString("name");
                String place = rs.getString("place");
 //               String comment = rs.getString("comment");

 
                System.out.println("id: " + id + " name: " + name + " partCategoryName: " + partCategoryName + " place: " + place);
 //               partList.add(new Part(id, partCategoryId, name, barcode, place, comment));
            }
        } catch (SQLException ex) {
            DialogMaker.showErrorAlert("Hiba", "Az alkatrészek listába írása nem sikerült", ex.getLocalizedMessage());
        }

 //       partsTable.setItems(partList);
    }

}
