
/**
 * Created by Johan on 2017-05-23.
 * Class Member
 * Creates an member, containing ssn number, occupation and address
 */
public class Member {
    /*
        Decalre instance variables.
    */
    private int SSN;
    private String Occupation, Address;


    /**
     * Creates a member object, representing a member
     * @param ssn personal id
     * @param occupation work title
     * @param address
     */
    public Member(int ssn, String occupation, String address) {
        this.SSN = ssn;
        this.Occupation = occupation;
        this.Address = address;
    }


    /**
     * getSSN
     * @return a personal id number
     */
    public int getSSN() {
        return SSN;
    }


    /**
     * setSSN
     * @param SSN sets a SSN number
     */
    public void setSSN(int SSN) {
        this.SSN = SSN;
    }


    /**
     * getOccupation
     * @return an occupation for a member
     */
    public String getOccupation() {
        return Occupation;
    }


    /**
     * setOccupation
     * @param occupation sets an occupation
     */
    public void setOccupation(String occupation) {
        Occupation = occupation;
    }


    /**
     * getAddress
     * @return an address for a member
     */
    public String getAddress() {
        return Address;
    }


    /**
     * setAddress
     * @param address sets an address
     */
    public void setAddress(String address) {
        Address = address;
    }
}
