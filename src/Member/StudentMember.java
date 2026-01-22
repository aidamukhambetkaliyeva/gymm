package Member;
public class StudentMember extends Member{
    private double discount;

    public StudentMember(String name, int age, String phone, double discount) {
        super(name, age, phone);
         setDiscount(discount);
    }
    @Override
    public void work(){
        System.out.println(name + "is working out as a student member");
    }
    @Override
    public String getRole(){
        return "StudentMember";
    }
    public void train(){
        System.out.println(name + "is training as a student member");
    }
    private void setDiscount(double discount) {
        if(discount < 0 || discount > 100){
            throw new IllegalArgumentException("discount can not be less than 0 and more than 100");
        }
        this.discount = discount;
    }


}
