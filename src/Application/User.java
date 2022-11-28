package Application;

import java.time.LocalDate;

public class User {

    private String firstName;
    private String lastName;
    private String userID;
    private String email;
    private String phoneNumber;
    private String userBirth;
    private String streetName;
    private int streetNumber;
    private String city;
    private int zipCode;

    public User(String firstName, String lastName, String userID, String email, String phoneNumber,
                String userBirth, String streetName, int streetNumber, String city, int zipCode){
        this.firstName = firstName;
        this.lastName = lastName;
        this.userID = userID;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.userBirth = userBirth;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.city = city;
        this.zipCode = zipCode;
    }

    //Getter userID
    public String getUserID() {return userID;}

    //Getter email
    public String getEmail() {return email;}

    //Getter phoneNumber
    public String getPhoneNumber() {return phoneNumber;}

    //Deze methode retourneert de volledige naam van de gebruiker.
    //Opmerking: deze methode moet eigelijk geen argumenten meekrijgen.
    public String getName(){
        return this.firstName + " " + this.lastName;
    }

    //Deze methode retourneert het volledig adres van de gebruiker.
    //Opmerking: deze methode moet eigelijk geen argumenten meekrijgen.
    public String getAddress(){
        return this.streetName + " " + this.streetNumber + "\n"
                + this.zipCode + " " + this.city;
    }

    //Deze methode retourneert hoe oud de gebruiker is.
    //Opmerking: deze methode moet eigelijk geen argumenten meekrijgen.
    public int getAge(){
        int ageUser = 0;

        int dag = Integer.parseInt(userBirth.substring(0,1));
        int maand = Integer.parseInt(userBirth.substring(3, 4));
        int jaar = Integer.parseInt(userBirth.substring(6,9));

        LocalDate geboorteDatum = LocalDate.of(jaar, maand, dag);
        LocalDate huidigeDatum = LocalDate.now();

        if(huidigeDatum.isBefore(geboorteDatum))
            ageUser = jaar;
        else if ((huidigeDatum.isAfter(geboorteDatum)) || huidigeDatum.isEqual(geboorteDatum))
            ageUser = jaar + 1;

        return ageUser;
    }

    //Deze methode retourneert of de gebruiker ouder of gelijk aan 16 jaar is.
    public boolean isValid(){
        boolean validUser = false;

        if(getAge() >= 16)
            validUser = true;

        return validUser;
    }
}
