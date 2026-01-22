package Menu;
import Member.StudentMember;
import Member.VIPMember;
import Member.Member;

import java.util.ArrayList;
import java.util.Scanner;
public class GymMenu implements Menu{
    private ArrayList<Member>members;
    private Scanner scanner;
    public GymMenu(){
        this.members = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        members.add(new StudentMember("Aruzhan", 17, "87716664253", 10));
        members.add(new VIPMember("Dana", 26, "87007654321", "Boxing"));
    }

    @Override
    public void displayMenu() {
        System.out.println("\n========================================");
        System.out.println("         FITNESS CLUB MANAGEMENT        ");
        System.out.println("========================================");
        System.out.println("1. Add Student Member");
        System.out.println("2. Add VIP Member");
        System.out.println("3. View All Members");
        System.out.println("4. Demonstrate Training");
        System.out.println("0. Exit");
        System.out.println("========================================");
    }

    @Override
    public void run() {
        boolean running = true;

        while (running) {
            displayMenu();
            System.out.print("Enter choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> addStudentMember();
                    case 2 -> addVIPMember();
                    case 3 -> showAllMembers();
                    case 4 -> demonstrateTraining();
                    case 0 -> running = false;
                    default -> System.out.println("Invalid option!");
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    private void showAllMembers() {
        if (members.isEmpty()) {
            System.out.println("No members yet!");
            return;
        }

        System.out.println("\n--- All Fitness Club Members ---");
        for (Member m : members) {
            System.out.println(m);
        }
    }
    private void addStudentMember() {
        try{
            System.out.println("Enter name: ");
            String name = scanner.nextLine();
            System.out.println("Enter age: ");
            int age = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter phone");
            String phone = scanner.nextLine();
            System.out.println("Ented discount");
            Double discount = scanner.nextDouble();
            scanner.nextLine();
            StudentMember student = new StudentMember(name, age, phone, discount);
            members.add(student);
            System.out.println("New student member is added");
        } catch (IllegalArgumentException e){
            System.out.println("Error" + e.getMessage());
        }
    }
    private void addVIPMember() {
        try {
            System.out.print("Enter Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Age: ");
            int age = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter Phone: ");
            String phone = scanner.nextLine();

            System.out.print("Enter Specialization: ");
            String spec = scanner.nextLine();

            VIPMember vip = new VIPMember(name, age, phone, spec);
            members.add(vip);

            System.out.println("VIP Member added!");

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void demonstrateTraining() {
        if (members.isEmpty()) {
            System.out.println("No members to train!");
            return;
        }

        System.out.println("\n--- MEMBERS TRAINING ---");
        for (Member m : members) {
            m.work();
        }
    }
}










