package Menu;

import Database.MemberDAO;
import Member.StudentMember;
import Member.VIPMember;
import Member.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GymMenu implements Menu {

    private ArrayList<Member> members; // оставляем для добавления новых
    private Scanner scanner;
    private MemberDAO dao;

    public GymMenu() {
        this.members = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.dao = new MemberDAO();

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
        System.out.println("5. Update Information");
        System.out.println("6. Delete Member");
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
                    case 5 -> updateMember();
                    case 6 -> deleteMember();
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
        System.out.println("\n--- All Fitness Club Members ---");
        for (Member m : members) {
            System.out.println(m);
        }
    }

    private void addStudentMember() {
        try {
            System.out.print("Enter Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Age: ");
            int age = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter Phone: ");
            String phone = scanner.nextLine();

            System.out.print("Enter Discount (%): ");
            double discount = scanner.nextDouble();
            scanner.nextLine();

            StudentMember student = new StudentMember(name, age, phone, discount);
            members.add(student);

            System.out.println("Student Member added!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
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
            members.add(vip); // всё ещё добавляем в ArrayList

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

    private void updateMember() {
        System.out.print("Enter Member ID to update: ");
        int memberId = scanner.nextInt();
        scanner.nextLine();

        Member member = dao.getMemberById(memberId);
        if (member == null) {
            System.out.println("No member found with ID: " + memberId);
            return;
        }

        System.out.println("Member found:");
        System.out.println(member);

        System.out.print("New Name [" + member.getName() + "]: ");
        String newName = scanner.nextLine();
        if (!newName.trim().isEmpty()) member.setName(newName);

        System.out.print("New Phone [" + member.getPhone() + "]: ");
        String newPhone = scanner.nextLine();
        if (!newPhone.trim().isEmpty()) member.setPhone(newPhone);

        dao.updateMember(member, memberId);
    }

    private void deleteMember() {
        System.out.print("Enter Member ID to delete: ");
        int memberId = scanner.nextInt();
        scanner.nextLine();

        Member member = dao.getMemberById(memberId);
        if (member == null) {
            System.out.println("No member found with ID: " + memberId);
            return;
        }

        System.out.println("Member to delete:");
        System.out.println(member);

        System.out.print("Are you sure? (yes/no): ");
        String confirmation = scanner.nextLine();
        if (confirmation.equalsIgnoreCase("yes")) {
            dao.deleteMember(memberId);
        } else {
            System.out.println("Deletion cancelled.");
        }
    }
}
