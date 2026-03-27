package customerservicesystem;

public class Customer {

    public int queueID;
    public String name;
    public String idNumber;
    public String address;
    public String issue;
    public boolean solved;

    public int getQueueID() {
        return queueID;
    }

    public void setQueueID(int queueID) {
        this.queueID = queueID;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public boolean isSolved() {
        return solved;
    }

    public void setisSolved(boolean solved) {
        this.solved = solved;
    }

//     ===== toString =====
    @Override
    public String toString() {
        return "Queue ID: " + queueID
                + "\nName: " + name
                + "\nID Number: " + idNumber
                + "\nAddress: " + address
                + "\nIssue: " + issue
                + "\nSolved? " + (solved ? "Yes" : "No");
    }
}
