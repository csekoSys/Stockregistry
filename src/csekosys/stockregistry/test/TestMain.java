
package csekosys.stockregistry.test;

import csekosys.stockregistry.database.DatabaseHandler;
import csekosys.stockregistry.database.DatabaseHelper;
import java.util.ArrayList;

public class TestMain {
    
    public static void main(String[] args) {
        DatabaseHandler.getInstance();
        
        ArrayList list = (ArrayList) DatabaseHelper.findAllPartCategories();
        
        System.err.println("list mérete: " + list.size());
        
        for (Object object : list) {
            System.out.println(object.toString());
        }
        
    }
}
