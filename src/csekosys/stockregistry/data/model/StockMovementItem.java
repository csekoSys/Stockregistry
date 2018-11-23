
package csekosys.stockregistry.data.model;

public class StockMovementItem {
    private int id;
    private int strockMovementId;
    private int partId;
    private int newItem;
    private int goodItem;
    private int quantity;

    public StockMovementItem(int id, int strockMovementId, int partId, int newItem, int goodItem, int quantity) {
        this.id = id;
        this.strockMovementId = strockMovementId;
        this.partId = partId;
        this.newItem = newItem;
        this.goodItem = goodItem;
        this.quantity = quantity;
    }

    public StockMovementItem(int strockMovementId, int partId, int newItem, int goodItem, int quantity) {
        this.strockMovementId = strockMovementId;
        this.partId = partId;
        this.newItem = newItem;
        this.goodItem = goodItem;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStrockMovementId() {
        return strockMovementId;
    }

    public void setStrockMovementId(int strockMovementId) {
        this.strockMovementId = strockMovementId;
    }

    public int getPartId() {
        return partId;
    }

    public void setPartId(int partId) {
        this.partId = partId;
    }

    public int getNewItem() {
        return newItem;
    }

    public void setNewItem(int newItem) {
        this.newItem = newItem;
    }

    public int getGoodItem() {
        return goodItem;
    }

    public void setGoodItem(int goodItem) {
        this.goodItem = goodItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
    
}
