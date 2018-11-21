package csekosys.stockregistry.data.model;

public class Part {

    private int id;
    private int partCategoryId;
    private String partCategoryName;
    private String name;
    private String barcode;
    private String place;
    private String comment;
    private boolean active;

    public Part(int id, int partCategoryId, String name, String barcode, String place, String comment, boolean active) {
        this.id = id;
        this.partCategoryId = partCategoryId;
        this.name = name;
        this.barcode = barcode;
        this.place = place;
        this.comment = comment;
        this.active = active;
    }

    public Part(int partCategoryId, String name, String barcode, String place, String comment, boolean active) {
        this.partCategoryId = partCategoryId;
        this.name = name;
        this.barcode = barcode;
        this.place = place;
        this.comment = comment;
        this.active = active;
    }

    public Part(int id, String partCategoryName, String name, String barcode, String place, String comment, boolean active) {
        this.id = id;
        this.partCategoryName = partCategoryName;
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

    public int getPartCategoryId() {
        return partCategoryId;
    }

    public void setPartCategoryId(int partCategoryId) {
        this.partCategoryId = partCategoryId;
    }

    public String getPartCategoryName() {
        return partCategoryName;
    }

    public void setPartCategoryName(String partCategoryName) {
        this.partCategoryName = partCategoryName;
    }
    
    

}
