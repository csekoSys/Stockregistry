package csekosys.stockregistry.test;

import csekosys.stockregistry.data.model.StockMovement;
import csekosys.stockregistry.database.DatabaseHelper;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestMain {

    public static void main(String[] args) {
        
        System.out.println("lastStockMovement: " + DatabaseHelper.getLastStockMovement("HJB"));

    }

    private static void initIdentification() {

        SimpleDateFormat identificationDateFormat = new SimpleDateFormat("YYYYMMdd");
        Date dateNow = new Date();
        String identificationDateNow = identificationDateFormat.format(dateNow);

        String newIdentification = "";
        String prefix = "UJB";
        StockMovement lastStockMovement = new StockMovement(1, "UJB-20181122-512", 0, 0, "", "", "", null);

        System.out.println("csekosys.stockregistry.ui.stockmovement.add.StockMovementAddController.initIdentification(): " + lastStockMovement);

        if (lastStockMovement == null) {
            System.out.println("Nincs lastStockMovement object");
        } else {
            String identification = lastStockMovement.getIdentification();
            String[] parts = identification.split("-");
            String identificationPrefix = parts[0];
            String identificationDate = parts[1];
            String identificationCount = parts[2];

            for (int i = 0; i < parts.length; i++) {
                System.out.println("parts " + i + " - " + parts[i]);
            }

            if (identificationDateNow.equals(identificationDate)) {
                System.out.println("Egyezik a két dátum");
                System.out.println("count string: " + identificationCount);

                int identificationCountInt = Integer.parseInt(identificationCount);
                System.out.println("count int: " + identificationCountInt);
                System.out.println("count int lenght: " + identificationCountInt);

                if (identificationCountInt < 10) {
                    newIdentification = prefix + "-" + identificationDate + "-00" + (identificationCountInt + 1);
                } else if (identificationCountInt < 100) {
                    newIdentification = prefix + "-" + identificationDate + "-0" + (identificationCountInt + 1);
                } else {
                    newIdentification = prefix + "-" + identificationDate + "-" + (identificationCountInt + 1);
                }
                System.out.println("newIdentification: " + newIdentification);

            } else {
                System.out.println("Nem egyezik a két dátum");
                newIdentification = newIdentification = prefix + "-" + identificationDateNow + "-" + "001";
                System.out.println("newIdentification: " + newIdentification);
            }
        }
    }

}
