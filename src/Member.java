public class Member {
    protected String name;
    protected int age;
    protected String phone;

    public Member(String name, int age, String phone) {
        this.name = name;
        this.age = age;
        this.phone = phone;

    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) {
        if (age >= 0) this.age = age; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public void attendTraining() {
        System.out.println(name + " is training in the gym.");
    }

    public String getRole() {
        return "Gym Member";
    }

    @Override
    public String toString() {
        return "[" + getRole() + "] " + name +
                " (Age: " + age +
                ", Phone: " + phone + ")";
    }


}

