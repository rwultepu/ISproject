package Model;

import Database.ClothingDAO;
import Database.OwnerDAO;
import Database.TransactionDAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Owner extends User {

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

    public double getTotalRevenueOfTheMonth(){
        double totalRenvenueOfTheMonth = 0.0;
        for(Transaction transactions1Per1: transactionDAO.getAllTransactions()){
            for(Clothing clothings1Per1: clothingDAO.getAllClothings()){
                if(clothings1Per1.getClothingID() == transactions1Per1.getClothingID()){
                    ownerDAO.getOwner(clothings1Per1.getUserID());
                }
            }
        }
        return totalRenvenueOfTheMonth;
    }

}






