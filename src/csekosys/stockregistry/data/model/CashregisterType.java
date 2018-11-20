
package csekosys.stockregistry.data.model;
;

public class CashregisterType {

    private int id;
    private String licenseNumber;
    private String name;

    public CashregisterType(int id, String licenseNumber, String name) {
        this.id = id;
        this.licenseNumber = licenseNumber;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
