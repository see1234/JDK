public class JobHuman {
    private String tableId;
    private String phoneNumber;
    private String name;
    private int exp;

    public JobHuman(String tableId, String phoneNumber, String name, int exp) {
        this.tableId = tableId;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.exp = exp;
    }


    public String getEmployeeId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }
}
