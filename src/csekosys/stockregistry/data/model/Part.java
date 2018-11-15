package csekosys.stockregistry.data.model;

public class Part {

    private int id;
    private int partCategoryId;
    private String name;
    private String barcode;
    private String place;
    private String comment;
    
    public Part(int partCategoryId, String name, String barcode, String place, String comment) {
        this.partCategoryId = partCategoryId;
        this.name = name;
        this.barcode = barcode;
        this.place = place;
        this.comment = comment;
    }
    
    public Part(int id, int partCategoryId, String name, String barcode, String place, String comment) {
        this.id = id;
        this.partCategoryId = partCategoryId;
        this.name = name;
        this.barcode = barcode;
        this.place = place;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPartCategoryId() {
        return partCategoryId;
    }

    public void setPartCategoryId(int partCategoryId) {
        this.partCategoryId = partCategoryId;
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
    
    

}
