public class VIPMember extends Member {
    private String specialization;

    public VIPMember(String name, int age, String phone, String specialization) {
        super(name, age, phone);
    }

    public VIPMember(String name, int age, String number) {
        super(name, age, number);
    }

    public void work() {
        System.out.println(name + " is training as a VIP member");
    }

    public String getRole() {
        return "VIPMember";
    }

    public void attendSpecialClass(String className) {
        System.out.println(name + " is attending a special VIP class: " + className);
    }

    public boolean isElite() {
        return age >= 25;
    }

}



