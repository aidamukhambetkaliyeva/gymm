import Database.MemberDAO;
import Member.Member;

public class TestInsert {
    public static void main(String[] args) {
        Member member = new Member("Aruzhan", 23, "87719997766") {
            @Override
            public void work() {

            }

            @Override
            public String getRole() {
                return "Gym Member";
            }
        };
        MemberDAO dao = new MemberDAO();


    }
}


