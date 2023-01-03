import java.time.Period;
import java.time.LocalDate;

public class Customer {

    private int klantnummer;
    private String firstName;
    private String name;
    private String adres;
    private String birthdate;
    private String phoneNumber;
    private int yearsSubscribed;


    public Customer(int klantnummer, String firstname, String name, String adres, String birthdate, String phoneNumber, int yearsSubscribed) {
        this.klantnummer = klantnummer;
        this.firstName = firstname;
        this.name = name;
        this.adres = adres;
        this.birthdate = birthdate;
        this.phoneNumber = phoneNumber;
        this.yearsSubscribed = yearsSubscribed;
    }

    public int getKlantnummer() {
        return klantnummer;
    }

    public void setKlantnummer(int klantnummer) {
        this.klantnummer = klantnummer;
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

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
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
                "klantnummer=" + klantnummer +
                ", firstName='" + firstName + '\'' +
                ", name='" + name + '\'' +
                ", adres='" + adres + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", yearsSubscribed=" + yearsSubscribed +
                '}';
    }
}
