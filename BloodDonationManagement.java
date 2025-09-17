import java.util.*;

class Donor {
    int id;
    String name;
    int age;
    String bloodGroup;
    String contact;
    ArrayList<String> donationDates = new ArrayList<>();

    Donor(int id, String name, int age, String bloodGroup, String contact) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.bloodGroup = bloodGroup;
        this.contact = contact;
    }

    void donate(String date) {
        donationDates.add(date);
    }

    void display() {
        System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age + 
                           ", Blood Group: " + bloodGroup + ", Contact: " + contact);
        if (!donationDates.isEmpty()) {
            System.out.println("Donation History: " + donationDates);
        }
    }
}

public class BloodDonationManagement {
    static Scanner sc = new Scanner(System.in);
    static HashMap<Integer, Donor> donors = new HashMap<>();
    static int donorCounter = 1;

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n=== Blood Donation Management System ===");
            System.out.println("1. Register Donor");
            System.out.println("2. View All Donors");
            System.out.println("3. Search Donor by Blood Group");
            System.out.println("4. Record Blood Donation");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> registerDonor();
                case 2 -> viewDonors();
                case 3 -> searchByBloodGroup();
                case 4 -> recordDonation();
                case 5 -> System.out.println("Exiting... Thank you!");
                default -> System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 5);
    }

    static void registerDonor() {
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Age: ");
        int age = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Blood Group: ");
        String bloodGroup = sc.nextLine();
        System.out.print("Enter Contact: ");
        String contact = sc.nextLine();

        Donor d = new Donor(donorCounter, name, age, bloodGroup, contact);
        donors.put(donorCounter, d);
        System.out.println("Donor registered successfully! Donor ID: " + donorCounter);
        donorCounter++;
    }

    static void viewDonors() {
        if (donors.isEmpty()) {
            System.out.println("No donors registered.");
        } else {
            for (Donor d : donors.values()) {
                d.display();
            }
        }
    }

    static void searchByBloodGroup() {
        System.out.print("Enter Blood Group to search: ");
        String bg = sc.nextLine();
        boolean found = false;
        for (Donor d : donors.values()) {
            if (d.bloodGroup.equalsIgnoreCase(bg)) {
                d.display();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No donors found with blood group " + bg);
        }
    }

    static void recordDonation() {
        System.out.print("Enter Donor ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        if (donors.containsKey(id)) {
            System.out.print("Enter Donation Date (dd-mm-yyyy): ");
            String date = sc.nextLine();
            donors.get(id).donate(date);
            System.out.println("Donation recorded successfully!");
        } else {
            System.out.println("Donor ID not found!");
        }
    }
}
