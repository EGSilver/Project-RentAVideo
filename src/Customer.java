import java.util.ArrayList;

public class Customer {
    private String name;
    private String adres;
    private String birthdate;
    private String phoneNumber;
    private int yearsSubscribed = 0;


    public Customer(String name, String adres, String birthdate, String phoneNumber, int yearsSubscribed) {
        this.name = name;
        this.adres = adres;
        this.birthdate = birthdate;
        this.phoneNumber = phoneNumber;
        this.yearsSubscribed = yearsSubscribed;
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

    public void rent() {

    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", adres='" + adres + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", yearsSubscribed=" + yearsSubscribed +
                '}';
    }
}
