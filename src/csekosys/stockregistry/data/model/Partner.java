
package csekosys.stockregistry.data.model;

public class Partner {
    private int id;
    private String name;
    private String address;
    private boolean active;

    public Partner(int id, String name, String address, boolean active) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.active = active;
    }

    public Partner(String name, String address, boolean active) {
        this.name = name;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
