package csekosys.stockregistry.database;

import csekosys.stockregistry.data.model.Part;
import csekosys.stockregistry.data.model.PartCategory;
import csekosys.stockregistry.tools.DialogMaker;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DatabaseHelper {

    public static boolean insertNewPart(Part part) {
        try {
            String sql = "INSERT INTO parts(part_category_id,name,barcode,place,comment) VALUES(?,?,?,?,?)";
            PreparedStatement statement = DatabaseHandler.getInstance().getConnection().prepareStatement(sql);
            statement.setInt(1, part.getPartCategoryId());
            statement.setString(2, part.getName());
            statement.setString(3, part.getBarcode());
            statement.setString(4, part.getPlace());
            statement.setString(5, part.getComment());
            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            DialogMaker.showErrorAlert("Hiba", "Az alkatrész beszúrása nem sikerült", ex.getLocalizedMessage());
            System.err.println("Hiba az alkatrés beszúrása közben: " + ex);
        }
        return false;
    }

    public static List<Part> findAllParts() {
        List list = new ArrayList();

        DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
        String query = "SELECT * FROM parts";
        ResultSet rs = databaseHandler.execQuery(query);
        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                int partCategoryId = rs.getInt("part_category_id");
                String name = rs.getString("name");
                String barcode = rs.getString("barcode");
                String place = rs.getString("place");
                String comment = rs.getString("comment");

                list.add(new Part(id, partCategoryId, name, barcode, place, comment));
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
        String query = "SELECT * FROM part_categories";
        ResultSet rs = databaseHandler.execQuery(query);
        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String comment = rs.getString("comment");

                list.add(new PartCategory(id, name, comment));
            }
            return list;
        } catch (SQLException ex) {
            DialogMaker.showErrorAlert("Hiba", "Az alkatrész típusok listába írása nem sikerült", ex.getLocalizedMessage());
            return null;
        }
    }
}
