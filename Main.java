import java.util.*;
import java.io.*;

// Part B: Student class
class Student implements Serializable {
    int studentID;
    String name;
    char grade;

    Student(int id, String n, char g) {
        studentID = id;
        name = n;
        grade = g;
    }
}

// Part C: Employee class
class Employee implements Serializable {
    int id;
    String name;
    String designation;
    double salary;

    Employee(int i, String n, String d, double s) {
        id = i;
        name = n;
        designation = d;
        salary = s;
    }

    public String toString() {
        return id + ", " + name + ", " + designation + ", " + salary;
    }
}

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        while(true) {
            System.out.println("\nChoose task:");
            System.out.println("1 - Sum of Integers (Autoboxing/Unboxing)");
            System.out.println("2 - Student Serialization/Deserialization");
            System.out.println("3 - Employee Management System");
            System.out.println("4 - Exit");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch(choice) {
                case 1:
                    sumIntegers();
                    break;
                case 2:
                    studentSerialization();
                    break;
                case 3:
                    employeeManagement();
                    break;
                case 4:
                    System.out.println("Exiting program...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    // Part A
    public static void sumIntegers() {
        System.out.println("Enter numbers separated by space:");
        String[] tokens = sc.nextLine().split(" ");
        ArrayList<Integer> numbers = new ArrayList<>();

        for(String t : tokens) {
            Integer num = Integer.parseInt(t); // Autoboxing
            numbers.add(num);
        }

        int sum = 0;
        for(Integer n : numbers) {
            sum += n; // Unboxing
        }
        System.out.println("Total sum: " + sum);
    }

    // Part B
    public static void studentSerialization() throws IOException, ClassNotFoundException {
        System.out.println("Creating student object...");
        Student s1 = new Student(101, "Khushi", 'A');

        // Serialize
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("student.dat"));
        oos.writeObject(s1);
        oos.close();

        // Deserialize
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("student.dat"));
        Student s2 = (Student) ois.readObject();
        ois.close();

        System.out.println("Student details after deserialization:");
        System.out.println("ID: " + s2.studentID + ", Name: " + s2.name + ", Grade: " + s2.grade);
    }

    // Part C
    public static void employeeManagement() throws IOException, ClassNotFoundException {
        String filename = "employees.dat";
        ArrayList<Employee> employees = new ArrayList<>();

        // Load existing employees if file exists
        File f = new File(filename);
        if(f.exists()) {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
            employees = (ArrayList<Employee>) ois.readObject();
            ois.close();
        }

        while(true) {
            System.out.println("\nEmployee Menu:");
            System.out.println("1 - Add Employee");
            System.out.println("2 - Display Employees");
            System.out.println("3 - Back to Main Menu");
            int choice = sc.nextInt();
            sc.nextLine();

            switch(choice) {
                case 1:
                    System.out.println("Enter ID:");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter Name:");
                    String name = sc.nextLine();
                    System.out.println("Enter Designation:");
                    String des = sc.nextLine();
                    System.out.println("Enter Salary:");
                    double salary = sc.nextDouble();
                    sc.nextLine();

                    employees.add(new Employee(id, name, des, salary));

                    // Save to file
                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
                    oos.writeObject(employees);
                    oos.close();
                    break;

                case 2:
                    System.out.println("Employee List:");
                    for(Employee e : employees) {
                        System.out.println(e);
                    }
                    break;

                case 3:
                    return;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
