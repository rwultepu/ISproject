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
    public ArrayList<Clothing> getAllClothesOfOwner(){
        ArrayList<Clothing> clothesOfOwner = new ArrayList<>();
        for(Clothing clothings1Per1: clothingDAO.getAllClothing()){
            if(clothings1Per1.getUserID() == getUserID()){
                clothesOfOwner.add(clothings1Per1);
            }
        }
        return clothesOfOwner;
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
    public ArrayList<Transaction> getTransactionHistoryOfOwner(){
        ArrayList<Transaction> transactionHistoryOfOwner = new ArrayList<>();
        for(Transaction transactions1Per1: transactionDAO.getAllTransactions()){
            for(Clothing clothes1Per1VanOwner: getAllClothesOfOwner()){
                if(transactions1Per1.getClothingID() == clothes1Per1VanOwner.getClothingID()){
                    transactionHistoryOfOwner.add(transactions1Per1);
                }
            }
        }return transactionHistoryOfOwner;
    }

    public int getAverageProductReviewScore(){
        int averageProductReviewScore = 0;
        for(Transaction transactionVanOwner1Per1: getTransactionHistoryOfOwner()){
            averageProductReviewScore += transactionVanOwner1Per1.getReviewProduct();
        }
        averageProductReviewScore = averageProductReviewScore/(getTransactionHistoryOfOwner().size());
        return averageProductReviewScore;
    }

    public int getAverageServiceReviewScore(){
        int averageServiceReviewScore = 0;
        for(Transaction transactionVanOwner1Per1: getTransactionHistoryOfOwner()){
            averageServiceReviewScore += transactionVanOwner1Per1.getReviewService();
        }
        averageServiceReviewScore = averageServiceReviewScore/(getTransactionHistoryOfOwner().size());
        return averageServiceReviewScore;
    }

    public double getTotalRevenueOfTheMonth(String month){
        double totalRenvenueOfTheMonth = 0.0;
        for(Transaction transactionsOfOwner1Per1: getTransactionHistoryOfOwner()){
            if(transactionsOfOwner1Per1.getDateOfTransaction().substring(3,5).equals(month)){
                totalRenvenueOfTheMonth += transactionsOfOwner1Per1.getTotalPrice();
                //Deze methode moet nog gemaakt worden in Transaction
                //Is dit OK of moet de amount to cause hier nog uitgehaald worden?
            }
        }
        return totalRenvenueOfTheMonth;
    }

    public double getTotalAmountToCause(String month){
        double totalAmountToCause = 0.0;

    }

}






