package Model;

import Database.OwnerDAO;
import Database.RenterDAO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class User {

    private String firstName;
    private String lastName;
    private int userID;
    private String email;
    private String phoneNumber;
    private LocalDate userBirth;
    private String streetName;
    private int streetNumber;
    private String city;
    private int zipCode;
    private int counter = 1;

    public User(String firstName, String lastName, String email, String phoneNumber,
                LocalDate userBirth, String streetName, int streetNumber, String city, int zipCode){
        this.firstName = firstName;
        this.lastName = lastName;

        this.userID = counter;
        counter++;

        this.email = email;
        this.phoneNumber = phoneNumber;
        this.userBirth = userBirth;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.city = city;
        this.zipCode = zipCode;
    }

    //Getter userID
    public int getUserID() {return userID;}

    //Getter email
    public String getEmail() {return email;}

    //Getter phoneNumber
    public String getPhoneNumber() {return phoneNumber;}

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getUserBirth() {
        return userBirth;
    }

    public String getStreetName() {
        return streetName;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public String getCity() {
        return city;
    }

    public int getZipCode() {
        return zipCode;
    }

    //Deze methode retourneert de volledige naam van de gebruiker.
    //Opmerking: deze methode moet eigelijk geen argumenten meekrijgen.

    /*
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

    public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant().atZone(hgg.systemDefault()).toLocalDate();
    }

    public LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }
     */

    /*Wachten op de mail van Gailly

    public int getAge(){
        int ageUser = 0;

        LocalDate geboorteDatum = convertToLocalDateViaSqlDate(Date userBirth);
        int dag = userBirth.getDayOfMonth();

        int maand = Integer.parseInt(userBirth.substring(3, 4));
        int jaar = Integer.parseInt(userBirth.substring(6,9));

        LocalDate geboorteDatum = LocalDate.of(jaar, maand, dag);

        Date huidigeDatum = Date.no
        LocalDate huidigeDatum = LocalDate.now();

        if(huidigeDatum.isBefore(geboorteDatum))
            ageUser = jaar;
        else if ((huidigeDatum.isAfter(geboorteDatum)) || huidigeDatum.isEqual(geboorteDatum))
            ageUser = jaar + 1;

        return ageUser;
    }
    */



    //public ArrayList<Owner> getAllOwners()

    public boolean isValidEmail(String email){
        boolean validEmail = false;
        OwnerDAO ownerDao = new OwnerDAO();

        ArrayList<Owner> ownerList = ownerDao.getAllOwners();

        for(Owner owner : ownerList)
            if((owner.getEmail().equals(email)) && email != null)
                validEmail = true;

        return validEmail;
    }


    /*
    //Deze methode retourneert of de gebruiker ouder of gelijk aan 16 jaar is.
    public boolean isValidAge(Date userbirth){
        boolean validAge = false;

        if((getAge() >= 16) && (getAge() <= 100))
            validAge = true;

        return validAge;
    }
     */

    /*
    String firstName, String lastName, int userID, String email, String phoneNumber,
    Date userBirth, String streetName, int streetNumber, String city, int zipCode,
    String causeName, double selectedPercentageToCauseOfOwner
    */

    public void addUser(String firstName, String lastName, int userID, String email, String phoneNumber,
                        Date userBirth, String streetName, int streetNumber, String city, int zipCode,
                        String causeName, double selectedPercentageToCauseOfOwner){
        Owner owner = new Owner();
        Renter renter = new Renter();
        OwnerDAO.saveOwner();
        RenterDAO.saveRenter();
    }

    public void deleteUser(){
        OwnerDAO.deleteOwner();
        RenterDAO.deleteRenter();
    }

}
