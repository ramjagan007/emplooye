package EMS;

import java.util.*;

public class EmployeeSystem {
     int id;
     String name;
     String department;
     double salary;

    // In-memory list to store all employees
     List<EmployeeSystem> employees = new ArrayList<>();

    // Constructors
    public EmployeeSystem() {}
    public EmployeeSystem(int id, String name, String dept, double salary) {
        this.id = id;
        this.name = name;
        this.department = dept;
        this.salary = salary;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    // -------------------- CORE CRUD METHODS --------------------

    // Add Employee
    public void addEmployee(EmployeeSystem emp) {
        employees.add(emp);
    }

    // View Employees (Table Format)
    public void viewEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }

        System.out.println("-------------------------------------------------------------");
        System.out.printf("| %-5s | %-15s | %-15s | %-12s |\n", "ID", "Name", "Department", "Salary (₹)");
        System.out.println("-------------------------------------------------------------");

        for (EmployeeSystem e : employees) {
            System.out.printf("| %-5d | %-15s | %-15s | %-12.2f |\n",
                    e.id, e.name, e.department, e.salary);
        }
        System.out.println("-------------------------------------------------------------");
    }

    // Find by ID
    public EmployeeSystem findEmployeeById(int id) {
        for (EmployeeSystem e : employees) {
            if (e.getId() == id)
                return e;
        }
        return null;
    }

    // Update Employee
    public void updateEmployee(EmployeeSystem e) {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId() == e.getId()) {
                employees.set(i, e);
                return;
            }
        }
    }

    // Delete Employee
    public void deleteEmployee(EmployeeSystem e) {
        employees.remove(e);
    }

    // -------------------- EXTRA FEATURES --------------------

    // Search by Name
    public void searchByName(String name) {
        boolean found = false;
        System.out.println("\nSearch Results for \"" + name + "\":");
        System.out.println("-------------------------------------------------------------");
        System.out.printf("| %-5s | %-15s | %-15s | %-12s |\n", "ID", "Name", "Department", "Salary (₹)");
        System.out.println("-------------------------------------------------------------");
        for (EmployeeSystem e : employees) {
            if (e.getName().equalsIgnoreCase(name)) {
                System.out.printf("| %-5d | %-15s | %-15s | %-12.2f |\n",
                        e.id, e.name, e.department, e.salary);
                found = true;
            }
        }
        if (!found)
            System.out.println("| No employee found with that name.                       |");
        System.out.println("-------------------------------------------------------------");
    }

    // Sort by Salary
    public void sortBySalary() {
        employees.sort(Comparator.comparingDouble(EmployeeSystem::getSalary).reversed());
        System.out.println("\nEmployees Sorted by Salary (High → Low):");
        viewEmployees();
    }

    // Sort by Name
    public void sortByName() {
        employees.sort(Comparator.comparing(EmployeeSystem::getName));
        System.out.println("\nEmployees Sorted by Name (A → Z):");
        viewEmployees();
    }

    // -------------------- MAIN MENU --------------------

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EmployeeSystem service = new EmployeeSystem();
        int choice;

        do {
            System.out.println("\n========= Employee Management System =========");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Search Employee by Name");
            System.out.println("6. Sort Employees by Salary");
            System.out.println("7. Sorttttttt Employees by Name");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
            
                case 1:
                    System.out.print("ID: "); int id = sc.nextInt(); sc.nextLine();
                    System.out.print("Name: "); String name = sc.nextLine();
                    System.out.print("Department: "); String dept = sc.nextLine();
                    System.out.print("Salary: "); double salary = sc.nextDouble(); sc.nextLine();
                    service.addEmployee(new EmployeeSystem(id, name, dept, salary));
                    System.out.println("✅ Employee added successfully!");
                    break;

                case 2:
                    service.viewEmployees();
                    break;

                case 3:
                    System.out.print("Enter ID to update: "); int updateId = sc.nextInt(); sc.nextLine();
                    EmployeeSystem e = service.findEmployeeById(updateId);
                    if (e != null) {
                        System.out.print("New Name: "); e.setName(sc.nextLine());
                        System.out.print("New Department: "); e.setDepartment(sc.nextLine());
                        System.out.print("New Salary: "); e.setSalary(sc.nextDouble()); sc.nextLine();
                        service.updateEmployee(e);
                        System.out.println("✅ Employee updated successfully!");
                    } 
                    else {
                        System.out.println("❌ Employee not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter ID to delete: "); int deleteId = sc.nextInt(); sc.nextLine();
                    EmployeeSystem del = service.findEmployeeById(deleteId);
                    if (del != null) {
                        service.deleteEmployee(del);
                        System.out.println("✅ Employee deleted successfully!");
                    } 
                    else {
                        System.out.println("❌ Employee not found.");
                    }
                    break;

                case 5:
                    System.out.print("Enter Name to search: ");
                    String searchName = sc.nextLine();
                    service.searchByName(searchName);
                    break;

                case 6:
                    service.sortBySalary();
                    break;

                case 7:
                    service.sortByName();
                    break;

                case 8:
                    System.out.println("👋 Exiting program...");
                    break;

                default:
                    System.out.println("⚠️ Invalid choice! Try again.");
            }
        } while (choice != 8);

        sc.close();
    }
}
