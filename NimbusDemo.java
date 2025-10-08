import java.util.*;
import java.util.stream.*;
import java.util.Comparator;

// Part A: Employee class
class Employee {
    String name;
    int age;
    double salary;

    Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return name + " - Age: " + age + ", Salary: " + salary;
    }
}

// Part B: Student class
class Student {
    String name;
    double marks;

    Student(String name, double marks) {
        this.name = name;
        this.marks = marks;
    }

    @Override
    public String toString() {
        return name + " - Marks: " + marks;
    }
}

// Part C: Product class
class Product {
    String name;
    double price;
    String category;

    Product(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    @Override
    public String toString() {
        return name + " (" + category + ") - Price: " + price;
    }
}

public class NimbusDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.println("\n--- Nimbus Java Demo ---");
            System.out.println("1. Employee Sorting");
            System.out.println("2. Student Filtering & Sorting");
            System.out.println("3. Product Stream Operations");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();

            switch(choice) {
                case 1:
                    runEmployeeDemo();
                    break;
                case 2:
                    runStudentDemo();
                    break;
                case 3:
                    runProductDemo();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid option! Try again.");
            }
        }
    }

    // Part A: Employee Demo
    public static void runEmployeeDemo() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Khushi", 22, 45000));
        employees.add(new Employee("Aman", 25, 60000));
        employees.add(new Employee("Riya", 21, 40000));

        System.out.println("\nOriginal Employee List:");
        employees.forEach(System.out::println);

        // Sort by Name
        employees.sort((e1, e2) -> e1.name.compareTo(e2.name));
        System.out.println("\nSorted by Name:");
        employees.forEach(System.out::println);

        // Sort by Age
        employees.sort((e1, e2) -> Integer.compare(e1.age, e2.age));
        System.out.println("\nSorted by Age:");
        employees.forEach(System.out::println);

        // Sort by Salary Descending
        employees.sort((e1, e2) -> Double.compare(e2.salary, e1.salary));
        System.out.println("\nSorted by Salary (Descending):");
        employees.forEach(System.out::println);
    }

    // Part B: Student Demo
    public static void runStudentDemo() {
        List<Student> students = Arrays.asList(
            new Student("Khushi", 82),
            new Student("Aman", 65),
            new Student("Riya", 90),
            new Student("Raj", 70),
            new Student("Simran", 95)
        );

        System.out.println("\nStudents scoring above 75% (sorted by marks):");
        students.stream()
                .filter(s -> s.marks > 75)
                .sorted(Comparator.comparingDouble(s -> s.marks))
                .map(s -> s.name)
                .forEach(System.out::println);
    }

    // Part C: Product Demo
    public static void runProductDemo() {
        List<Product> products = Arrays.asList(
            new Product("Laptop", 75000, "Electronics"),
            new Product("Phone", 50000, "Electronics"),
            new Product("Shirt", 1500, "Clothing"),
            new Product("Jeans", 2000, "Clothing"),
            new Product("Fridge", 40000, "Appliances"),
            new Product("Washing Machine", 35000, "Appliances")
        );

        // Group by Category
        Map<String, List<Product>> grouped = products.stream()
                .collect(Collectors.groupingBy(p -> p.category));
        System.out.println("\nProducts Grouped by Category:");
        grouped.forEach((cat, list) -> System.out.println(cat + ": " + list));

        // Most Expensive Product in Each Category
        Map<String, Optional<Product>> maxByCategory = products.stream()
                .collect(Collectors.groupingBy(
                        p -> p.category,
                        Collectors.maxBy(Comparator.comparingDouble(p -> p.price))
                ));
        System.out.println("\nMost Expensive Product in Each Category:");
        maxByCategory.forEach((cat, p) -> System.out.println(cat + ": " + p.orElse(null)));

        // Average Price
        double avgPrice = products.stream()
                .collect(Collectors.averagingDouble(p -> p.price));
        System.out.println("\nAverage Price of All Products: " + avgPrice);
    }
}
