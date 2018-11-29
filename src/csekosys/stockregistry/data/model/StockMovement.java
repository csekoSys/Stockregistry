
package csekosys.stockregistry.data.model;

import java.sql.Date;

public class StockMovement {
    private int id;
    private String identification;
    private int partnerId;
    private int stockMovementTypeId;
    private String transfering;
    private String recipient;
    private String comment;
    private String date;

    public StockMovement(int id, String identification, int partnerId, int stockMovementTypeId, String transfering, String recipient, String comment, String date) {
        this.id = id;
        this.identification = identification;
        this.partnerId = partnerId;
        this.stockMovementTypeId = stockMovementTypeId;
        this.transfering = transfering;
        this.recipient = recipient;
        this.comment = comment;
        this.date = date;
    }

    public StockMovement(String identification, int partnerId, int stockMovementTypeId, String transfering, String recipient, String comment, String date) {
        this.identification = identification;
        this.partnerId = partnerId;
        this.stockMovementTypeId = stockMovementTypeId;
        this.transfering = transfering;
        this.recipient = recipient;
        this.comment = comment;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public int getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(int partnerId) {
        this.partnerId = partnerId;
    }

    public int getStockMovementTypeId() {
        return stockMovementTypeId;
    }

    public void setStockMovementTypeId(int stockMovementTypeId) {
        this.stockMovementTypeId = stockMovementTypeId;
    }

    public String getTransfering() {
        return transfering;
    }

    public void setTransfering(String transfering) {
        this.transfering = transfering;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    
    
}
