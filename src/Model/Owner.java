package Model;

import Database.ClothingDAO;
import Database.OwnerDAO;
import Database.TransactionDAO;

import java.util.ArrayList;

public class Owner extends User {
    TransactionDAO transactionDAO = new TransactionDAO();
    ClothingDAO clothingDAO = new ClothingDAO();
    OwnerDAO ownerDAO = new OwnerDAO();

    public Owner(String firstName, String lastName, String email, String phoneNumber,
                 String userBirth, String streetName, int streetNumber, String city, int zipCode,
                 String causeName, double selectedPercentageToCauseOfOwner){
        super(firstName, lastName, email, phoneNumber, userBirth, streetName,
                streetNumber, city, zipCode, causeName, selectedPercentageToCauseOfOwner);

    }
    public ArrayList<Clothing> getAllClothesOfOwner(Owner owner){
        ArrayList<Clothing> clothingsOfOwner = new ArrayList<>();
        for(Clothing clothings1Per1: clothingDAO.getAllClothings()){
            if(clothings1Per1.getUserID() == owner.getUserID()){
                clothingsOfOwner.add(clothings1Per1);
            }
        }
        return clothingsOfOwner;
    }
    public boolean updatePercentageToCauseOfOwner(double newPercentage){
        boolean isGelukt = false;
        if(newPercentage>0){
            setSelectedPercentageToCauseOfOwner(newPercentage);
            isGelukt = true;
        }
        return isGelukt;
    }

    //Retourneert een ArrayList van alle transactions waarin kledingstukken van de Owner gehuurd werden
    public ArrayList<Transaction> getTransactionHistoryOfOwner(Owner owner){
        ArrayList<Transaction> transactionHistoryOfOwner = new ArrayList<>();
        for(Transaction transactions1Per1: transactionDAO.getAllTransactions()){
            for(Clothing clothes1Per1VanOwner: getAllClothesOfOwner(owner)){
                if(transactions1Per1.getClothingID() == clothes1Per1VanOwner.getClothingID()){
                    transactionHistoryOfOwner.add(transactions1Per1);
                }
            }
        }return transactionHistoryOfOwner;
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

     */


    public int getAverageProductReviewScore(Owner owner){
        int averageProductReviewScore = 0;
        for(Transaction transactionVanOwner1Per1: getTransactionHistoryOfOwner(owner)){
            averageProductReviewScore += transactionVanOwner1Per1.getReviewProduct();
        }
        averageProductReviewScore = averageProductReviewScore/(getTransactionHistoryOfOwner(owner).size());
        return averageProductReviewScore;
    }


}






