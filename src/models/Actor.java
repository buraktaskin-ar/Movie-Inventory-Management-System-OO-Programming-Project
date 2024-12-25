package models;

public class Actor {       
    private String nameSurname;
    private String gender;
    private String country;

    public Actor(String nameSurname, String gender, String country) {        // Actor sınıfı için yapıcı metot.
        this.nameSurname = nameSurname.trim();
        this.gender = gender.trim();
        this.country = country.trim();
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Adı Soyadı: " + nameSurname + ", Cinsiyet: " + gender + ", Ülke: " + country;
    }
}
