package Model;

import Database.OwnerDAO;
import Database.RenterDAO;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class User {

    private String firstName;
    private String lastName;
    private int userID;
    private String email;
    private String phoneNumber;
    private String userBirth;
    private String streetName;
    private int streetNumber;
    private String city;
    private int zipCode;
    //Deze counter bepaald de userID, deze doet ++ telkens wanneer er een nieuwe user wordt aangemaakt
    private int counter = 1;
    private String causeName;
    private double selectedPercentageToCauseOfOwner;

    // Constructor voor een owner
    public User(String firstName, String lastName, String email, String phoneNumber,
                String userBirth, String streetName, int streetNumber, String city, int zipCode, String causeName, double selectedPercentageToCauseOfOwner){
        this.firstName = firstName;
        this.lastName = lastName;
        this.userID = counter;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.userBirth = userBirth;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.city = city;
        this.zipCode = zipCode;
        this.causeName = causeName;
        this.selectedPercentageToCauseOfOwner = selectedPercentageToCauseOfOwner;
    }

    // Constructor voor een renter
    public User(String firstName, String lastName, String email, String phoneNumber, String userBirth, String streetName, int streetNumber, String city, int zipCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userID = counter;
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
    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public String getUserBirth() {return userBirth;}
    public String getStreetName() {return streetName;}
    public int getStreetNumber() {return streetNumber;}
    public String getCity() {return city;}
    public int getZipCode() {return zipCode;}
    public String getCauseName() {return causeName;}
    public double getSelectedPercentageToCauseOfOwner() {return selectedPercentageToCauseOfOwner;}
    public void setSelectedPercentageToCauseOfOwner(double selectedPercentageToCauseOfOwner) {this.selectedPercentageToCauseOfOwner = selectedPercentageToCauseOfOwner;}

    public boolean isValidDate(String date){
        boolean flag = false;

        int dayString = Integer.parseInt(date.substring(0,2));
        int monthString = Integer.parseInt(date.substring(3,5));
        int yearString = Integer.parseInt(date.substring(6,10));

        boolean leapYear = (yearString%4 == 0);

        if(leapYear){
            if((yearString%100 == 0) && (yearString%400 !=0))
                leapYear = false;}

        if((monthString>= 1) && (monthString<= 12))
            if ((monthString == 9)||(monthString == 4)||(monthString == 11 )||(monthString == 6) ) {
                if ((dayString >= 1) && (dayString <= 30))
                    flag = true;
                else
                    flag = false;
            }

            else if ((monthString == 2)) {
                if ((leapYear))
                    if ((dayString <= 29) && (dayString >= 1))
                        flag = true;
                    else
                        flag = false;

                else if ((dayString <= 28) && (dayString >= 1))
                    flag = true;
                else
                    flag = false;
            }

            else
            if ((dayString>=1)&& (dayString<=31))
                flag = true;
            else
                flag = false;

        else
            flag = false;

        return flag;
    }

    public LocalDate stringToLocalDate(String date){
        LocalDate stringToLocalDate = null;

        int day = Integer.parseInt(date.substring(0,2));
        int month = Integer.parseInt(date.substring(3,5));
        int year = Integer.parseInt(date.substring(6,10));

        if(isValidDate(date))
            stringToLocalDate = LocalDate.of(day, month, year);

        return stringToLocalDate;
    }

    public boolean isValidEmail(String email){
        boolean validEmail = false;
        OwnerDAO ownerDao = new OwnerDAO();

        ArrayList<Owner> ownerList = ownerDao.getAllOwners();

        for(Owner owner : ownerList)
            if((owner.getEmail().equals(email)) && email != null)
                validEmail = true;

        return validEmail;
    }

    public boolean isValidAge(){
        boolean flag = false;

        LocalDate dateNow = LocalDate.now();
        LocalDate userBirth = stringToLocalDate(this.userBirth);

        long yearsBetweenUserBirthAndDateNow = ChronoUnit.YEARS.between(dateNow, userBirth);

        if((yearsBetweenUserBirthAndDateNow >= 16))
            return flag = true;

        return flag;
    }

    // Wegschrijven van UserObject naar database
    public void addUser(){
        Owner owner = new Owner(firstName, lastName, email, phoneNumber, userBirth, streetName,
                streetNumber, city, zipCode, causeName, selectedPercentageToCauseOfOwner);
        Renter renter = new Renter(firstName, lastName, email, phoneNumber, userBirth, streetName,
                streetNumber, city, zipCode);
        OwnerDAO.saveOwner(owner);
        RenterDAO.saveRenter(renter);
        counter++;
    }

    //Opmerking: Rune en Hendrieke: Klopt het dat we bij deze
    public void deleteUser(User userInput){
        Owner owner = new Owner(userInput.firstName,userInput.lastName, userInput.email, userInput.phoneNumber, userInput.userBirth, userInput.streetName,
                userInput.streetNumber, userInput.city, userInput.zipCode, userInput.causeName, userInput.selectedPercentageToCauseOfOwner);
        Renter renter = new Renter(userInput.firstName, userInput.lastName, userInput.email, userInput.phoneNumber, userInput.userBirth, userInput.streetName,
                userInput.streetNumber, userInput.city, userInput.zipCode);
        OwnerDAO.deleteOwner(owner);
        RenterDAO.deleteRenter(renter);
    }
}