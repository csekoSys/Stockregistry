
package csekosys.stockregistry.test;

import csekosys.stockregistry.database.DatabaseHandler;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestMain {
    
    public static void main(String[] args) {
        DatabaseHandler databaseHandler = DatabaseHandler.getInstance();       

        String query = "SELECT parts.id, parts.name, part_categories.namee, parts.place "
                + "FROM parts "
                + "INNER JOIN part_categories "
                + "ON parts.part_category_id=part_categories.id";
        
        ResultSet rs = databaseHandler.execQuery(query);
        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String partCategoryName = rs.getString("namee");
 //               String partCategoryName = rs.getString("part_categories.name");
                String place = rs.getString("place");

 
                System.out.println("\n\nid: " + id + "\nname: " + name + "\npartCategoryName: " + partCategoryName + "\nplace: " + place);
            }
        } catch (SQLException ex) {
        }
        
    }
}
