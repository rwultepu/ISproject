package Model;

import Database.ClothingDAO;
import Database.OwnerDAO;
import Database.TransactionDAO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class Owner extends User {

    Owner owner;

    private String causeName;
    private double selectedPercentageToCauseOfOwner;

    TransactionDAO transactionDAO = new TransactionDAO();
    ClothingDAO clothingDAO = new ClothingDAO();
    OwnerDAO ownerDAO = new OwnerDAO();

    public Owner(String firstName, String lastName, int userID, String email, String phoneNumber,
                 Date userBirth, String streetName, int streetNumber, String city, int zipCode,
                 String causeName, double selectedPercentageToCauseOfOwner){
        super(firstName, lastName, userID, email, phoneNumber, userBirth, streetName,
                streetNumber, city, zipCode);

        this.causeName = causeName;
        this.selectedPercentageToCauseOfOwner = selectedPercentageToCauseOfOwner;
    }

    public String getCauseName() {
        return causeName;
    }

    public double getSelectedPercentageToCauseOfOwner() {
        return selectedPercentageToCauseOfOwner;
    }


    public ArrayList<Clothing> getAllClothesOfOwner(){
        ArrayList<Clothing> clothingsOfOwner = new ArrayList<>();
        for(Clothing clothings1Per1: clothingDAO.getAllClothings()){
            if(clothings1Per1.getUserID() == owner.getUserID()){
                clothingsOfOwner.add(clothings1Per1);
            }
        }
        return clothingsOfOwner;
    }
/*
    public double getTotalRevenueOfTheMonth(){
        double totalRenvenueOfTheMonth = 0.0;
        for(Transaction transactions1Per1: transactionDAO.getAllTransactions()){
            if(transactions1Per1.getDateOfTransaction().)
                //Klopt mijn meegegeven parameter?
                // Hoe moet ik kijken of de maand van de dateOfTransaction gelijk is aan de meegegeven month
                Calendar.getInstance().getTime().getMonth();
            //waaerom welken deze methodes niet?
        }
        return totalRenvenueOfTheMonth;
    }

    public void updatePercentageToCauseOfOwner(double newPercentage){
        if(newPercentage>0){
            selectedPercentageToCauseOfOwner = newPercentage;
        }
        else {
            System.out.println("Het te doneren percentage moet groter zijn dan 0%!");
        }
    }

    public ArrayList<Transaction> getTransactionHistoryOfOwner(){
        ArrayList<Transaction> transactionHistoryOfOwner = new ArrayList<>();
        for(Transaction transactions1Per1: transactionDAO.getAllTransactions()){
            for(int clothingIDsOfOwner1Per1: owner.getAllClothesOfOwner().)
        }
    }
*/

    public static void main(String[] args) {
        OwnerDAO ownerDAO1 = new OwnerDAO();
        System.out.println(ownerDAO1.getAllOwners());
    }

}






