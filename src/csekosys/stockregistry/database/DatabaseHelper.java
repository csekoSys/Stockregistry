package csekosys.stockregistry.database;

import csekosys.stockregistry.data.model.CashregisterType;
import csekosys.stockregistry.data.model.Part;
import csekosys.stockregistry.data.model.PartCategory;
import csekosys.stockregistry.data.model.Partner;
import csekosys.stockregistry.data.model.StockMovement;
import csekosys.stockregistry.data.model.StockMovementType;
import csekosys.stockregistry.tools.DialogMaker;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DatabaseHelper {

    public static boolean insertNewPart(Part part) {
        try {
            String sql = "INSERT INTO parts(partCategoryId,partName,partBarcode,partPlace,partComment,partActive) VALUES(?,?,?,?,?,?)";
            PreparedStatement statement = DatabaseHandler.getInstance().getConnection().prepareStatement(sql);
            statement.setInt(1, part.getPartCategoryId());
            statement.setString(2, part.getName());
            statement.setString(3, part.getBarcode());
            statement.setString(4, part.getPlace());
            statement.setString(5, part.getComment());
            statement.setBoolean(6, part.isActive());
            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            DialogMaker.showErrorAlert("Hiba", "Az alkatrész beszúrása nem sikerült", ex.getLocalizedMessage());
            System.err.println("Hiba az alkatrész beszúrása közben: " + ex);
        }
        return false;
    }

    public static ObservableList<Part> findAllParts() {
        ObservableList list = FXCollections.observableArrayList();

        DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
        String query = "SELECT * FROM parts";
        ResultSet rs = databaseHandler.execQuery(query);
        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                int partCategoryId = rs.getInt("partCategoryId");
                String name = rs.getString("partName");
                String barcode = rs.getString("partbarcode");
                String place = rs.getString("partPlace");
                String comment = rs.getString("partComment");
                boolean active = rs.getBoolean("partActive");

                list.add(new Part(id, partCategoryId, name, barcode, place, comment, active));
            }
            return list;
        } catch (SQLException ex) {
            DialogMaker.showErrorAlert("Hiba", "Az alkatrészek listába írása nem sikerült", ex.getLocalizedMessage());
            return null;
        }
    }

    public static ObservableList<PartCategory> findAllPartCategories() {
        ObservableList<PartCategory> list = FXCollections.observableArrayList();

        DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
        String query = "SELECT * FROM partCategories";
        ResultSet rs = databaseHandler.execQuery(query);
        try {
            while (rs.next()) {
                int id = rs.getInt("partCategoryId");
                String name = rs.getString("partCategoryName");
                String comment = rs.getString("partCategoryComment");
                boolean active = rs.getBoolean("partCategoryActive");

                list.add(new PartCategory(id, name, comment, active));
            }
            return list;
        } catch (SQLException ex) {
            DialogMaker.showErrorAlert("Hiba", "Az alkatrész típusok listába írása nem sikerült", ex.getLocalizedMessage());
            return null;
        }
    }

    public static ObservableList<StockMovementType> findAllStockMovementTypes() {
        ObservableList<StockMovementType> list = FXCollections.observableArrayList();

        DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
        String query = "SELECT * FROM stockMovementTypes";
        ResultSet rs = databaseHandler.execQuery(query);
        try {
            while (rs.next()) {
                int id = rs.getInt("stockMovementTypeId");
                boolean newPart = rs.getBoolean("stockMovementTypeNewPart");
                boolean goodPart = rs.getBoolean("stockMovementTypeGoodPart");
                String name = rs.getString("stockMovementTypeName");
                boolean increase = rs.getBoolean("stockMovementTypeIncrease");
                String prefix = rs.getString("stockMovementTypePrefix");
                boolean active = rs.getBoolean("stockMovementTypeActive");

                list.add(new StockMovementType(id, newPart, goodPart, name, increase, prefix, active));
            }
            return list;
        } catch (SQLException ex) {
            DialogMaker.showErrorAlert("Hiba", "Az alkatrész típusok listába írása nem sikerült", ex.getLocalizedMessage());
            return null;
        }
    }

    public static ObservableList<Partner> findAllPartners() {
        ObservableList<Partner> list = FXCollections.observableArrayList();

        DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
        String query = "SELECT * FROM partners";
        ResultSet rs = databaseHandler.execQuery(query);
        try {
            while (rs.next()) {
                int id = rs.getInt("partnerId");
                String name = rs.getString("partnerName");
                String address = rs.getString("partnerAddress");
                boolean active = rs.getBoolean("partnerActive");

                list.add(new Partner(id, name, address, active));
            }
            return list;
        } catch (SQLException ex) {
            DialogMaker.showErrorAlert("Hiba", "A partnerek listába írása nem sikerült", ex.getLocalizedMessage());
            return null;
        }
    }

    /**
     * Az utolsó Készletmozgás lekérdezése, az azonosító előtagja alapján
     *
     * @return
     */
    public static StockMovement getLastStockMovement(String prefix) {
        StockMovement lastStockMovement = null;
        DatabaseHandler databaseHandler = DatabaseHandler.getInstance();

        String query = "SELECT MAX(stockMovementId) FROM stockMovements WHERE stockMovementIdentification LIKE '" + prefix + "_____________'";
        ResultSet rs = databaseHandler.execQuery(query);
        try {
            while (rs.next()) {
                int id = rs.getInt("stockMovementId");
                String identification = rs.getString("stockMovementIdentification");
                int partnerId = rs.getInt("partnerId");
                int stockMovementTypeId = rs.getInt("stockMovementTypeId");
                String transfering = rs.getString("stockMovementTransfering");
                String recipient = rs.getString("stockMovementRecipient");
                String comment = rs.getString("stockMovementComment");
                Date timestamp = rs.getDate("stockMovementDate");

                lastStockMovement = new StockMovement(id, identification, partnerId, stockMovementTypeId, transfering, recipient, comment, (java.sql.Date) timestamp);
            }
            return lastStockMovement;
        } catch (SQLException ex) {
            return null;
        }
    }

    public static ObservableList<Part> selecktStockMovementPartList() {
        DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
        ObservableList<Part> list = FXCollections.observableArrayList();

        String query = "SELECT parts.partId, parts.partName, partCategories.partCategoryName "
                + "FROM parts "
                + "INNER JOIN partCategories "
                + "ON parts.partCategoryId=partCategories.partCategoryId";

        ResultSet rs = databaseHandler.execQuery(query);
        
        try {
            while (rs.next()) {
                int id = rs.getInt("partId");
                String name = rs.getString("partName");
                String partCategoryName = rs.getString("partCategoryName");

                list.add(new Part(id, partCategoryName, name, "", "", "", true));
            }
            return list;
        } catch (SQLException ex) {
            DialogMaker.showErrorAlert("Hiba", "Az alkatrészek listába írása nem sikerült", ex.getLocalizedMessage());
            return null;
        }

    }

    /*
    public static PartCategory getOnePartCategory(int partCategoryId) {
        PartCategory partCategory = null;
        DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
        String query = "SELECT * FROM part_categories WHERE id=" + partCategoryId;
        ResultSet rs = databaseHandler.execQuery(query);
        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String comment = rs.getString("comment");
                partCategory = new PartCategory(id, name, comment);
                return partCategory;
            }
        } catch (SQLException ex) {
            DialogMaker.showErrorAlert("Hiba", "Az alkatrész típusok listába írása nem sikerült", ex.getLocalizedMessage());
            return null;
        }
        return partCategory;
    }
     */
    public static ObservableList<CashregisterType> findAllCashregisterTypes() {
        ObservableList<CashregisterType> list = FXCollections.observableArrayList();

        DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
        String query = "SELECT * FROM cashregister_types";
        ResultSet rs = databaseHandler.execQuery(query);
        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                String licenseNumber = rs.getString("licensenumber");
                String name = rs.getString("name");

                list.add(new CashregisterType(id, licenseNumber, name));
            }
            return list;
        } catch (SQLException ex) {
            DialogMaker.showErrorAlert("Hiba", "Az pénztárgép típusok listába írása nem sikerült", ex.getLocalizedMessage());
            return null;
        }
    }
}
