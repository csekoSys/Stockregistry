package csekosys.stockregistry.database;

import csekosys.stockregistry.tools.DialogMaker;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseHandler {

    private static DatabaseHandler handler = null;

    private static final String DB_URL = "jdbc:sqlite:Database/Stock.db";
    private static Connection conn = null;
    private static Statement stmt = null;

    private DatabaseHandler() {
        createConnection();

        createPartsTable();
        createPartCategoriesTable();
        createCashregisterTypesTable();

        createPartnersTable();

        createCashregisterTypesTable();

        createStockMovementTypesTable();
        createStockMovementsTable();
        createStockMoventItemsTable();
    }

    public static DatabaseHandler getInstance() {
        if (handler == null) {
            handler = new DatabaseHandler();
        }
        return handler;
    }

    private void createConnection() {
        try {
            conn = DriverManager.getConnection(DB_URL);
        } catch (SQLException ex) {
            DialogMaker.showErrorAlert("SQLException", null, ex.getMessage());
        }
        System.out.println("csekosys.stockregistry.database.DatabaseHandler.createConnection()");
    }

    private void createPartsTable() {
        String TABLE_NAME = "parts";

        try {
            stmt = conn.createStatement();
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);

            if (tables.next()) {
                System.out.println("A(z) " + TABLE_NAME + " tábla már létezik!");
            } else {
                stmt.execute("CREATE TABLE " + TABLE_NAME + " ("
                        + "id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                        + "part_categories_id INTEGER,\n"
                        + "name VARCHAR(255),\n"
                        + "barcode VARCHAR(255),\n"
                        + "place VARCHAR(255),\n"
                        + "comment VARCHAR(255)\n"
                        + ")");
            }

        } catch (SQLException ex) {
        }
    }

    private void createPartCategoriesTable() {
        String TABLE_NAME = "part_categories";

        try {
            stmt = conn.createStatement();
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);

            if (tables.next()) {
                System.out.println("A(z) " + TABLE_NAME + " tábla már létezik!");
            } else {
                stmt.execute("CREATE TABLE " + TABLE_NAME + " ("
                        + "id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                        + "name VARCHAR(255),\n"
                        + "comment VARCHAR(255)\n"
                        + ")");
            }

        } catch (SQLException ex) {
            System.err.println("Hiba: " + ex);
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void createCashregisterTypesTable() {
        String TABLE_NAME = "cashregister_types";

        try {
            stmt = conn.createStatement();
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);

            if (tables.next()) {
                System.out.println("A(z) " + TABLE_NAME + " tábla már létezik!");
            } else {
                stmt.execute("CREATE TABLE " + TABLE_NAME + " ("
                        + "id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                        + "licensenumber VARCHAR(255),\n"
                        + "name VARCHAR(255)\n"
                        + ")");
            }

        } catch (SQLException ex) {
        }
    }

    private void createPartnersTable() {
        String TABLE_NAME = "partners";

        try {
            stmt = conn.createStatement();
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);

            if (tables.next()) {
                System.out.println("A(z) " + TABLE_NAME + " tábla már létezik!");
            } else {
                stmt.execute("CREATE TABLE " + TABLE_NAME + " ("
                        + "id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                        + "name VARCHAR(255),\n"
                        + "address VARCHAR(255)\n"
                        + ")");
            }

        } catch (SQLException ex) {
        }
    }

    private void createStockMovementTypesTable() {
        String TABLE_NAME = "stock_movement_types";
        
        try {
            stmt = conn.createStatement();
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);
            
            if(tables.next()) {
                System.out.println("A(z) " + TABLE_NAME + " tábla már létezik!");
            } else {
                stmt.execute("CREATE TABLE " + TABLE_NAME + " ("
                        + "id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                        + "new INTEGER(1),\n"
                        + "waster INTEGER(1),\n"
                        + "name VARCHAR(255),\n"
                        + "increase INTEGER(2),\n"                        
                        + "prefix VARCHAR(255)\n"
                        + ")");
            }
            
        } catch (SQLException ex) {
            System.err.println("Hiba: " + ex);
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    private void createStockMovementsTable() {
    }

    private void createStockMoventItemsTable() {
    }

    public ResultSet execQuery(String query) {
        ResultSet result;
        try {
            stmt = conn.createStatement();
            result = stmt.executeQuery(query);
        } catch (SQLException ex) {
            return null;
        }
        return result;
    }

    public boolean execAction(String query) {
        try {
            stmt = conn.createStatement();
            stmt.execute(query);
            return true;
        } catch (SQLException ex) {
            return false;
        }

    }
}
