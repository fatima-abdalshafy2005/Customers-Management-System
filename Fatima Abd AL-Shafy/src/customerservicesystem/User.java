package customerservicesystem;

public class User {

public int queueID;

    public void setQueueID(int queueID) {
        this.queueID = queueID;
    }

    public int getQueueID() {
        return queueID;
    }

    String username;
     String password;
     boolean Admin;//هل هو مسؤول ولا لا
  boolean Active;//هل ممكن يخدم او لا
     Stack<Customer> servedCustomers=new  Stack<>();

     User(String username, String password, boolean Admin, boolean Active) {
        this.username = username;
        this.password = password;
        this.Admin = Admin;
        this.Active = Active;
        this.servedCustomers = new Stack<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean Admin() {
        return Admin;
    }

    public void setAdmin(boolean Admin) {
        this.Admin = Admin;
    }

    public boolean Active() {
        return Active;
    }

    public void setActive(boolean active) {
        this.Active = active;
    }
    public Stack<Customer> getServedCustomers() {
        return servedCustomers;
    }

    public void setServedCustomers(Stack<Customer> servedCustomers) {
        this.servedCustomers = servedCustomers;
    }
   public String toString() {
        return username + (Admin ? " (Admin)" : " (Employee)");
    }

   
}
