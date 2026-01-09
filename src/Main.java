import java.util.ArrayList;
import java.util.Scanner;
public class Main {

    private static ArrayList<Member> allMembers = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        allMembers.add(new Member("Марина", 20, "87770001111"));
        allMembers.add(new VIPMember("Алия", 26, "87770002222"));
        allMembers.add(new VIPMember("Игорь", 30, "87770003333", "Йога"));

        System.out.println("=== Polymorphism demonstration for Members ===");
        for (Member m : allMembers) {
            m.attendTraining();
        }

        addVIPMember();

        viewAllMembers();
    }

    private static void addVIPMember() {
        System.out.println("\n--- ADD VIP MEMBER ---");

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter phone: ");
        String phone = scanner.nextLine();

        VIPMember vip = new VIPMember(name, age, phone);
        allMembers.add(vip);

        System.out.println("\nVIP Member added successfully!");
    }

    private static void viewAllMembers() {
        System.out.println("\n========================================");
        System.out.println(" ALL MEMBERS (POLYMORPHIC LIST)");
        System.out.println("========================================");

        if (allMembers.isEmpty()) {
            System.out.println("No members found.");
            return;
        }

        System.out.println("Total members: " + allMembers.size());
        System.out.println();

        for (int i = 0; i < allMembers.size(); i++) {
            Member m = allMembers.get(i);
            System.out.println((i + 1) + ". " + m);

            if (m instanceof VIPMember) {
                VIPMember vip = (VIPMember) m;
                vip.attendSpecialClass("Йога VIP");
                if (vip.isElite()) {
                    System.out.println("This VIP member is elite!");
                }
            }
            System.out.println();
        }
    }
}
