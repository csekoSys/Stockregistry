package csekosys.stockregistry.data.model;

public class PartCategory {

    private int id;
    private String name;
    private String comment;
    private boolean active;

    public PartCategory() {
    }

    public PartCategory(int id, String name, String comment, boolean active) {
        this.id = id;
        this.name = name;
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
    
    

    @Override
    public String toString() {
        return name;
    }
    
    
}
