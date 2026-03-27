
package customerservicesystem;

import java.util.Scanner;

/**
 *
 * @author fatimaabdalshafy 2320231082
 */
public class CustomerServiceSystem {

    static Scanner sc = new Scanner(System.in);

    public static ArrayBag<User> users;
    public static Queue<Customer> waitingQueue;

    static int queueID = 0;

    // Static Block
    static {
        users = new ArrayBag<>(10);
        users.Add(new User("hasan", "123", false, true));
        users.Add(new User("ali", "234", false, true));
        users.Add(new User("kamal", "123", true, true));

        waitingQueue = new Queue<>();
    }

    public static void main(String[] args) {

        while (true) {
            System.out.println("\nWelcome to CompanyName");
            System.out.println("Press Enter to get your waiting ID");
            System.out.println("or enter username:");

            String input = sc.nextLine();

            //Customer 
            if (input.isEmpty()) {
                Customer c = new Customer();

                c.setQueueID(++queueID);
                waitingQueue.enqueue(c);

                System.out.println("Your waiting ID is: " + String.format("%03d", c.getQueueID()));

            } // ===== Employee / Admin =====
            else {
                User user = login(input);
                if (user != null) {
                    if (user.Admin()) {
                        adminMenu(user);
                    } else {
                        employeeMenu(user);
                    }
                }
            }
        }
    }

    // ===== LOGIN =====
    static User login(String username) {
        System.out.print("Enter password: ");
        String pass = sc.nextLine();

        for (int i = 0; i < users.size(); i++) {
            User u = users.get(i);
            if (u.getUsername().equalsIgnoreCase(username)
                   
                    && u.getPassword().equals(pass)
                    && u.Active()) {
                return u;
            }
        }
        System.out.println("Invalid login or user disabled");
        return null;
    }

    // ===== EMPLOYEE MENU =====
    static void employeeMenu(User emp) {
        while (true) {
            System.out.println("\nWelcome " + emp.getUsername());
            System.out.println("1- Serve next customer");
            System.out.println("2- Check last served customer");
            System.out.println("3- View all served customers");
            System.out.println("4- Logout");

            int ch = Integer.parseInt(sc.nextLine());

            switch (ch) {

                case 1:
                    Customer c = waitingQueue.dequeue();
                    if (c == null) {
                        System.out.println("No customers waiting");
                    } else {
                        fillCustomerInfo(c);
                        emp.getServedCustomers().push(c);
                        System.out.println("Customer served successfully");
                    }
                    break;

                case 2:
                    if (!emp.getServedCustomers().isempty()) {
                        System.out.println(emp.getServedCustomers().peek());
                    } else {
                        System.out.println("No customers served yet");
                    }
                    break;

                case 3:
                    if (emp.getServedCustomers().isempty()) {
                        System.out.println("No customers served");
                    } else {
                        // Create a temporary stack to avoid modifying the original
                        Stack<Customer> temp = new Stack<>();
                        Stack<Customer> original = emp.getServedCustomers();
                        while (!original.isempty()) {
                            Customer cust = original.pop();
                            System.out.println(cust);
                            temp.push(cust);
                        }
                        // Restore the original stack
                        while (!temp.isempty()) {
                            original.push(temp.pop());
                        }
                    }
                    break;

                case 4:
                    return;
            }
        }
    }

    // ===== ADMIN MENU =====
    static void adminMenu(User admin) {
        while (true) {
            System.out.println("\nWelcome Admin " + admin.getUsername());
            System.out.println("1- View customers queue");
            System.out.println("2- Check last served customer and by whom he was served ");

            System.out.println("3- View all customers issues");
            System.out.println("4- Manage employees ");
            System.out.println("5- View Customer Service Tree");

            int ch = Integer.parseInt(sc.nextLine());

            switch (ch) {

                case 1:
                    if (waitingQueue.isEmpty()) {
                        System.out.println("No customers in queue");

                    } else {
                        System.out.println("Customers waiting: " + waitingQueue.size());

                    }
                    break;

                case 2:
                    // Find the last served customer across all employees
                    Customer lastServed = null;
                    User servedBy = null;
                    for (int i = 0; i < users.size(); i++) {
                        User u = users.get(i);
                        if (!u.Admin() && !u.getServedCustomers().isempty()) {
                            Customer candidate = u.getServedCustomers().peek();
                            if (lastServed == null || candidate.getQueueID() > lastServed.getQueueID()) {
                                lastServed = candidate;
                                servedBy = u;
                            }
                        }
                    }
                    if (lastServed != null) {
                        System.out.println("Last served customer: " + lastServed);
                        System.out.println("Served by: " + servedBy.getUsername());
                    } else {
                        System.out.println("No customers served yet");
                    }
                    break;

                case 3:
                    int totalServed = 0;
                    for (int i = 0; i < users.size(); i++) {
                        User u = users.get(i);
                        if (!u.Admin()) {
                            totalServed += u.getServedCustomers().size();
                        }
                    }
                    System.out.println("We served " + totalServed + " customers today:");
                    int count = 1;
                    for (int i = 0; i < users.size(); i++) {
                        User u = users.get(i);
                        if (!u.Admin()) {
                            Stack<Customer> s = u.getServedCustomers();
                            // Use temp to preserve
                            Stack<Customer> temp = new Stack<>();
                            while (!s.isempty()) {
                                Customer cust = s.pop();
                                System.out.println(count + "-");
                                System.out.println(cust.getName());
                                System.out.println("ID: " + cust.getIdNumber());
                                System.out.println("Address: " + cust.getAddress());
                                System.out.println("Issue: " + cust.getIssue());
                                System.out.println("Solved? " + (cust.isSolved() ? "Yes" : "No"));
                                System.out.println("Served By: " + u.getUsername());
                                temp.push(cust);
                                count++;
                            }
                            while (!temp.isempty()) {
                                s.push(temp.pop());
                            }
                        }
                    }
                    break;
                case 4:
                    // List employees directly without "Employees:" header
                    int empCount = 1;
                    for (int i = 0; i < users.size(); i++) {
                        User u = users.get(i);
                        if (!u.Admin()) {
                            System.out.println(empCount + "- " + u.getUsername());
                            empCount++;
                        }
                    }
                    int sel = Integer.parseInt(sc.nextLine());
                    User selectedEmp = null;
                    empCount = 1;
                    for (int i = 0; i < users.size(); i++) {
                        User u = users.get(i);
                        if (!u.Admin()) {
                            if (empCount == sel) {
                                selectedEmp = u;
                                break;
                            }
                            empCount++;
                        }
                    }
                    if (selectedEmp != null) {
                        manageEmployee(selectedEmp);
                    } else {
                        System.out.println("Invalid selection");
                    }
                    break;

                case 5:
                    displayServiceTree();
                    break;

                case 6:
                    return;
            }
        }
    }

    // ===== Manage Employee =====
    static void manageEmployee(User emp) {
        while (true) {
            System.out.println("\nWhat do you want to do with this Employee?");
            System.out.println("1- Disable/Enable user");
            System.out.println("2- Change Name");
            System.out.println("3- Back");

            int ch = Integer.parseInt(sc.nextLine());

            switch (ch) {
                case 1:
                    emp.setActive(!emp.Active());
                    System.out.println("User " + (emp.Active() ? "enabled" : "disabled"));
                    break;
                case 2:
                    System.out.print("Enter new name: ");
                    String newName = sc.nextLine();
                    emp.setUsername(newName);
                    System.out.println("Name changed");
                    break;
                case 3:
                    return;
            }
        }
    }

    static void displayServiceTree() {

  
        for (int i = 0; i < users.size(); i++) {
            User admin = users.get(i);

            if (admin.Admin()) {
                System.out.println(admin.getUsername());

              
                for (int j = 0; j < users.size(); j++) {
                    User emp = users.get(j);

                    if (!emp.Admin()) {
                        System.out.println(" " + emp.getUsername());

                   
                        Stack<Customer> original = emp.getServedCustomers();
                        Stack<Customer> temp = new Stack<>();

                       
                        while (!original.isempty()) {
                            temp.push(original.pop());
                        }

                        
                        while (!temp.isempty()) {
                            Customer c = temp.pop();
                            System.out.println("  "
                                    + c.getName() + ", "
                                    + c.getIdNumber() + ", "
                                    + c.getAddress() + ", "
                                    + (c.isSolved() ? "Solved" : "Not Solved"));
                            original.push(c);
                        }
                    }
                }
            }
        }
    }

  

    
    static void fillCustomerInfo(Customer c) {
        System.out.print("Enter customer name: ");
        c.setName(sc.nextLine());

        System.out.print("Enter customer ID: ");
        c.setIdNumber(sc.nextLine());

        System.out.print("Enter customer address: ");
        c.setAddress(sc.nextLine());

        System.out.print("Issue description: ");
        c.setIssue(sc.nextLine());

        System.out.print("Is it solved? (1:yes, 0:no): ");
        c.setisSolved(sc.nextLine().equals("1"));
    }
}
