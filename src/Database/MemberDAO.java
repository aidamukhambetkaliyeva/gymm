package Database;

import Member.Member;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {

    public boolean updateMember(Member member, int memberId) {
        String sql = "UPDATE member SET name = ?, age = ?, phone = ?, role = ? WHERE member_id = ?";
        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, member.getName());
            statement.setInt(2, member.getAge());
            statement.setString(3, member.getPhone());
            // Пропущенный set для role
            statement.setString(4, member.getRole());
            statement.setInt(5, memberId);

            int rowsUpdated = statement.executeUpdate();
            statement.close();

            if (rowsUpdated > 0) {
                System.out.println("Member updated: " + member.getName());
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Update failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return false;
    }

    public Member getMemberById(int memberId) {
        String sql = "SELECT * FROM member WHERE member_id = ?";
        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return null;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, memberId);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String phone = rs.getString("phone");

                return new Member(name, age, phone) {
                    @Override
                    public void work() {
                        System.out.println(name + " is training");
                    }

                    @Override
                    public String getRole() {
                        return "Member";
                    }
                };
            }

            rs.close();
            statement.close();

        } catch (SQLException e) {
            System.out.println("Failed to get member by ID");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return null;
    }

    public boolean deleteMember(int memberId) {
        String sql = "DELETE FROM member WHERE member_id = ?";
        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, memberId);
            int rowsDeleted = statement.executeUpdate();
            statement.close();

            if (rowsDeleted > 0) {
                System.out.println("Member deleted (ID: " + memberId + ")");
                return true;
            } else {
                System.out.println("No member found with ID: " + memberId);
            }

        } catch (SQLException e) {
            System.out.println("Delete failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return false;
    }

    public List<Member> searchByName(String name) {
        List<Member> memberList = new ArrayList<>();
        String sql = "SELECT * FROM member WHERE name ILIKE ? ORDER BY name";
        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return memberList;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + name + "%"); // Add wildcards!
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Member member = extractMemberFromResultSet(resultSet);
                if (member != null) {
                    memberList.add(member);
                }
            }

            resultSet.close();
            statement.close();
            System.out.println("Found " + memberList.size() + " member(s)");

        } catch (SQLException e) {
            System.out.println("Search failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return memberList;
    }

    private Member extractMemberFromResultSet(ResultSet rs) {
        try {
            int id = rs.getInt("member_id");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            String phone = rs.getString("phone");
            String role = rs.getString("role");

            return new Member(name, age, phone) {
                @Override
                public void work() {
                    System.out.println(name + " is training");
                }

                @Override
                public String getRole() {
                    return role;
                }
            };

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Member> searchByAgeRange(int minAge, int maxAge) {
        List<Member> memberList = new ArrayList<>();
        String sql = "SELECT * FROM member WHERE age BETWEEN ? AND ? ORDER BY age DESC";
        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return memberList;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, minAge);
            statement.setInt(2, maxAge);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Member member = extractMemberFromResultSet(resultSet);
                if (member != null) {
                    memberList.add(member);
                }
            }

            resultSet.close();
            statement.close();
            System.out.println("Found " + memberList.size() + " member(s)");

        } catch (SQLException e) {
            System.out.println("Search failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return memberList;
    }

    public List<Member> searchByMinAge(int minAge) {
        List<Member> memberList = new ArrayList<>();
        String sql = "SELECT * FROM member WHERE age >= ? ORDER BY age DESC";
        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return memberList;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, minAge);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Member member = extractMemberFromResultSet(resultSet);
                if (member != null) {
                    memberList.add(member);
                }
            }

            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return memberList;
    }
}

