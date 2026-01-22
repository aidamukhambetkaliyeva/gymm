package Member;
public class VIPMember extends Member {
    private String specialization;

    public VIPMember(String name, int age, String phone, String specialization) {
        super(name, age, phone);
        setSpecialization(specialization);
    }

    @Override
    public void work(){
        System.out.println(name + "is training as a VIP member");
    }
    @Override
    public String getRole(){
        return "VIPMember";
    }

    public void attendSpecialClass(String classname){
        System.out.println(name + "is attending a special VIP classe: "+ classname);
    }

    public void setSpecialization(String specialization){
        if(specialization == null || specialization.trim().isEmpty()){
            throw new IllegalArgumentException("specialization can not be empty");
        }
        this.specialization = specialization;
    }



    }







