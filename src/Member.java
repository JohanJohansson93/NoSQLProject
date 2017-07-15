
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
    /*
       This contrsuctor takes several arguments for the member object.
     */
    public Member(int ssn, String occupation, String address) {
        this.SSN = ssn;
        this.Occupation = occupation;
        this.Address = address;
    }
    /*
       This method returns the ssn.
     */
    public int getSSN() {
        return SSN;
    }
    /*
       This method sets the ssn.
     */
    public void setSSN(int SSN) {
        this.SSN = SSN;
    }
    /*
       This method returns the occupation.
     */
    public String getOccupation() {
        return Occupation;
    }
    /*
       This method sets the occupation.
     */
    public void setOccupation(String occupation) {
        Occupation = occupation;
    }
    /*
       This method returns the address.
     */
    public String getAddress() {
        return Address;
    }
    /*
       This method sets the address.
     */
    public void setAddress(String address) {
        Address = address;
    }
}
