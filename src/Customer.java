import java.time.Period;
import java.time.LocalDate;

public class Customer {

    private int clientNumber;
    private String firstName;
    private String name;
    private String address;
    private String birthdate;
    private String phoneNumber;
    private int yearsSubscribed = 0;


    public Customer(int clientNumber, String firstname, String name, String address, String birthdate, String phoneNumber, int yearsSubscribed) {
        this.clientNumber = clientNumber;
        this.firstName = firstname;
        this.name = name;
        this.address = address;
        this.birthdate = birthdate;
        this.phoneNumber = phoneNumber;
        this.yearsSubscribed = yearsSubscribed;
    }

    public int getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(int clientNumber) {
        this.clientNumber = clientNumber;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getYearsSubscribed() {
        return yearsSubscribed;
    }


    public void setYearsSubscribed(int yearsSubscribed) {
        this.yearsSubscribed = yearsSubscribed;
    }
    public boolean checkUnderaged() {
        LocalDate dateOfBirth = LocalDate.parse(birthdate);
        Period age = Period.between(dateOfBirth, LocalDate.now());
        if (age.getYears() < 18) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Customer{" +
                "clientNumber=" + clientNumber +
                ", firstName='" + firstName + '\'' +
                ", name='" + name + '\'' +
                ", adres='" + address + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", yearsSubscribed=" + yearsSubscribed +
                '}';
    }
}
