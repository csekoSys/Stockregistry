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
        createCashregisterPartNumbersTable();

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

    public Connection getConnection() {
        return conn;
    }

    private void createConnection() {
        try {
            conn = DriverManager.getConnection(DB_URL);
        } catch (SQLException ex) {
            DialogMaker.showErrorAlert("SQLException", null, ex.getMessage());
        }
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
                        + "partId INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                        + "partCategoryId INTEGER,\n"
                        + "partName VARCHAR(255),\n"
                        + "partBarcode VARCHAR(255),\n"
                        + "partPlace VARCHAR(255),\n"
                        + "partComment VARCHAR(255),\n"
                        + "partActive INTEGER(1) DEFAULT 1\n"
                        + ")");
            }

        } catch (SQLException ex) {
        }
    }

    private void createPartCategoriesTable() {
        String TABLE_NAME = "partCategories";

        try {
            stmt = conn.createStatement();
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);

            if (tables.next()) {
                System.out.println("A(z) " + TABLE_NAME + " tábla már létezik!");
            } else {
                stmt.execute("CREATE TABLE " + TABLE_NAME + " ("
                        + "partCategoryId INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                        + "partCategoryName VARCHAR(255),\n"
                        + "partCategoryComment VARCHAR(255),\n"
                        + "partCategoryActive INTEGER(1) DEFAULT 1\n"
                        + ")");
            }

        } catch (SQLException ex) {
            System.err.println("Hiba: " + ex);
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void createCashregisterTypesTable() {
        String TABLE_NAME = "cashregisterTypes";

        try {
            stmt = conn.createStatement();
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);

            if (tables.next()) {
                System.out.println("A(z) " + TABLE_NAME + " tábla már létezik!");
            } else {
                stmt.execute("CREATE TABLE " + TABLE_NAME + " ("
                        + "cashregisterTypeId INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                        + "cashregisterTypeLicenseNumber VARCHAR(255),\n"
                        + "cashregisterTypeName VARCHAR(255),\n"
                        + "cashregisterTypeActive INTEGER(1) DEFAULT 1\n"
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
                        + "partnerId INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                        + "partnerName VARCHAR(255),\n"
                        + "partnerAddress VARCHAR(255),\n"
                        + "partnerActive INTEGER(1)\n"
                        + ")");
            }

        } catch (SQLException ex) {
        }
    }

    private void createStockMovementTypesTable() {
        String TABLE_NAME = "stockMovementTypes";

        try {
            stmt = conn.createStatement();
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);

            if (tables.next()) {
                System.out.println("A(z) " + TABLE_NAME + " tábla már létezik!");
            } else {
                stmt.execute("CREATE TABLE " + TABLE_NAME + " ("
                        + "stockMovementTypeId INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                        + "stockMovementTypeNewPart INTEGER(1),\n"
                        + "stockMovementTypeGoodPart INTEGER(1),\n"
                        + "stockMovementTypeName VARCHAR(255),\n"
                        + "stockMovementTypeIncrease INTEGER(2),\n"
                        + "stockMovementTypePrefix VARCHAR(255),\n"
                        + "stockMovementTypeActive INTEGER(1) DEFAULT 1\n"
                        + ")");
            }

        } catch (SQLException ex) {
            System.err.println("Hiba: " + ex);
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void createStockMovementsTable() {
        String TABLE_NAME = "stockMovements";

        try {
            stmt = conn.createStatement();
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);

            if (tables.next()) {
                System.out.println("A(z) " + TABLE_NAME + " tábla már létezik!");
            } else {
                stmt.execute("CREATE TABLE " + TABLE_NAME + " ("
                        + "stockMovementId INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                        + "stockMovementIdentification VARCHAR(255),\n"
                        + "partnerId INTEGER,\n"
                        + "stockMovementTypeId INTEGER,\n"
                        + "stockMovementTransfering VARCHAR(255),\n"
                        + "stockMovementRecipient VARCHAR(255),\n"
                        + "stockMovementComment VARCHAR(255),\n"
                        + "stockMovementDate DATETIME\n"
                        + ")");
            }

        } catch (SQLException ex) {
        }
    }

    private void createStockMoventItemsTable() {
        String TABLE_NAME = "stockMovementItems";

        try {
            stmt = conn.createStatement();
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);

            if (tables.next()) {
                System.out.println("A(z) " + TABLE_NAME + " tábla már létezik!");
            } else {
                stmt.execute("CREATE TABLE " + TABLE_NAME + " ("
                        + "stockMovementItemId INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                        + "stockMovementId INTEGER,\n"
                        + "partId INTEGER,\n"
                        + "stockMovementItemNew INTEGER,\n"
                        + "stockMovementItemGood INTEGER,\n"
                        + "stockMovementItemQuntity INTEGER\n"
                        + ")");
            }

        } catch (SQLException ex) {
        }
    }

    private void createCashregisterPartNumbersTable() {
        String TABLE_NAME = "cashregisterPartNumbers";

        try {
            stmt = conn.createStatement();
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);

            if (tables.next()) {
                System.out.println("A(z) " + TABLE_NAME + " tábla már létezik!");
            } else {
                stmt.execute("CREATE TABLE " + TABLE_NAME + " ("
                        + "cashregisterPartNumberId INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                        + "cashregisterTypeId INTEGER,\n"
                        + "partId INTEGER,\n"
                        + "cashregisterPartNumberPartQuntity INTEGER DEFAULT 0\n"
                        + ")");
            }

        } catch (SQLException ex) {
        }
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
