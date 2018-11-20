
package csekosys.stockregistry.test;

import csekosys.stockregistry.database.DatabaseHandler;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestMain {
    
    public static void main(String[] args) {
        DatabaseHandler databaseHandler = DatabaseHandler.getInstance();       

        String query = "SELECT parts.partId, parts.partName, partCategories.partCategoryName, parts.partPlace "
                + "FROM parts "
                + "INNER JOIN partCategories "
                + "ON parts.partCategoryId=partCategories.partCategoryId";
        
        System.out.println("query: " + query);
        
        ResultSet rs = databaseHandler.execQuery(query);
        try {
            while (rs.next()) {
                int id = rs.getInt("partId");
                String name = rs.getString("partName");
                String partCategoryName = rs.getString("partCategoryName");
                String place = rs.getString("partPlace");

 
                System.out.println("\n\nid: " + id + "\nname: " + name + "\npartCategoryName: " + partCategoryName + "\nplace: " + place);
            }
        } catch (SQLException ex) {
        }
        
    }
}
