package Member;
public abstract class Member {
    protected String name;
    protected int age;
    protected String phone;

    public Member(String name, int age, String phone) {
       setName(name);
       setAge(age);
       setPhone(phone);
    }
    public void DisplayInfo(){
        System.out.println(name + "training in the gym");
    }
    public abstract void work();

    public abstract String getRole();

    public void setName(String name){
        if (name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }

    public void setAge(int age){
        if (age < 0){
            throw new IllegalArgumentException("age can not be less than 0");
        }
        this.age = age;
    }

    public void setPhone(String phone) {
        if (phone == null || phone.trim().isEmpty()){
            throw new IllegalArgumentException("phone can not be empty");
        }
        this.phone = phone;
    }

    public String getName() { return name; }

    public int getAge() { return age; }

    public String getPhone() { return phone; }

    public void attendTraining() {
        System.out.println(name + " is training in the gym.");
    }


    @Override
    public String toString() {
        return "[" + getRole() + "] " + name +
                " (Age: " + age +
                ", Phone: " + phone + ")";
    }

}

