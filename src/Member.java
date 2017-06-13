/**
 * Created by Johan on 2017-05-23.
 */
public class Member {

    private int SSN;
    private String Occupation, Address;

    public Member(int ssn, String occupation, String address){
            this.SSN = ssn;
            this.Occupation = occupation;
            this.Address = address;
    };

    public int getSSN() {
        return SSN;
    }

    public void setSSN(int SSN) {
        this.SSN = SSN;
    }

    public String getOccupation() {
        return Occupation;
    }

    public void setOccupation(String occupation) {
        Occupation = occupation;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
