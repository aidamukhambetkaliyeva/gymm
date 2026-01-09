public class StudentMember extends Member{
    private double discount;

    public StudentMember(String name, int age, String phone, double discount) {
        super(name, age, phone);
        this.discount = discount;
    }

    public void train() {
        System.out.println(name + " is training as a Student member.");
    }

    @Override
    public String getRole() {
        return "Student Member";
    }

    public double getDiscount() {
        if (age < 18) {
            return discount + 5; // дополнительная скидка 5%
        } else {
            return discount;
        }
    }
}
