
package csekosys.stockregistry.data.model;

public class StockMovementType {
    private int id;
    private boolean newPart;
    private boolean goodPart;
    private String name;
    private boolean increase;
    private String prefix;
    private boolean active;

    public StockMovementType(boolean newPart, boolean goodPart, String name, boolean increase, String prefix, boolean active) {
        this.newPart = newPart;
        this.goodPart = goodPart;
        this.name = name;
        this.increase = increase;
        this.prefix = prefix;
        this.active = active;
    }

    public StockMovementType(int id, boolean newPart, boolean goodPart, String name, boolean increase, String prefix, boolean active) {
        this.id = id;
        this.newPart = newPart;
        this.goodPart = goodPart;
        this.name = name;
        this.increase = increase;
        this.prefix = prefix;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isNewPart() {
        return newPart;
    }

    public void setNewPart(boolean newPart) {
        this.newPart = newPart;
    }

    public boolean isGoodPart() {
        return goodPart;
    }

    public void setGoodPart(boolean goodPart) {
        this.goodPart = goodPart;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIncrease() {
        return increase;
    }

    public void setIncrease(boolean increase) {
        this.increase = increase;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return getName();
    }
    
    
    
}
