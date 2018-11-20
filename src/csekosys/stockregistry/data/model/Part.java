package csekosys.stockregistry.data.model;

public class Part {

    private int id;
    private PartCategory partCategory;
    private String name;
    private String barcode;
    private String place;
    private String comment;
    private boolean active;

    public Part(PartCategory partCategory, String name, String barcode, String place, String comment, boolean active) {
        this.partCategory = partCategory;
        this.name = name;
        this.barcode = barcode;
        this.place = place;
        this.comment = comment;
        this.active = active;
    }

    public Part(int id, PartCategory partCategory, String name, String barcode, String place, String comment, boolean active) {
        this.id = id;
        this.partCategory = partCategory;
        this.name = name;
        this.barcode = barcode;
        this.place = place;
        this.comment = comment;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PartCategory getPartCategory() {
        return partCategory;
    }

    public void setPartCategory(PartCategory partCategory) {
        this.partCategory = partCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    
}
