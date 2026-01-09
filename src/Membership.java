public class Membership {
    protected int membershipId;
    protected String type;
    protected double price;

    public Membership(int membershipId, String type, double price ) {
        this.membershipId = membershipId;
        this.type = type;
        this.price = price;
    }

    public int getMembershipId() { return membershipId; }
    public void setMembershipId(int membershipId) { this.membershipId = membershipId; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }


}
