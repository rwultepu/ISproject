package Model;

import Database.OwnerDAO;
import Database.TransactionDAO;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class  Renter extends User {

    OwnerDAO ownerDAO = new OwnerDAO();


    public Renter(String firstName, String lastName, String email, String phoneNumber, String userBirth, String streetName, int streetNumber, String city, int zipCode) {
        super(firstName, lastName, email, phoneNumber, userBirth, streetName, streetNumber, city, zipCode);
    }

    public ArrayList<Transaction> getTransactionHistoryOfRenter(int userID){
        ArrayList<Transaction> transactionHistoryOfRenter = new ArrayList<>();

        TransactionDAO transactionDao= new TransactionDAO();
        ArrayList<Transaction> getTransactionHistoryOfRenter= transactionDao.getAllTransactions();

        for(Transaction transaction : getTransactionHistoryOfRenter)
            if(userID == transaction.getUserID())
                transactionHistoryOfRenter.add(transaction);

        return transactionHistoryOfRenter;
    }

    // Geeft een ArrayList terug met alle owners die goed (>=4) scoren op de reviews
    public ArrayList<Owner> getTopOwners(){
        ArrayList<Owner> topOwners = new ArrayList<>();
        for(Owner o: ownerDAO.getAllOwners())
            if(o.getAverageProductReviewScore()>= 4 && o.getAverageServiceReviewScore()>= 4)
                topOwners.add(o);
        return topOwners;
    }

    //Geeft Arraylist met clothing van de topowners terug voor de recommendedFeed
    public ArrayList<Clothing> getAllRecommendedClothing(){
        ArrayList<Clothing> allRecommendedClothing = new ArrayList<>();
        for(Owner o: getTopOwners()){
            for(Clothing clothingsPerOwner1Per1: o.getAllClothesOfOwner()){
                allRecommendedClothing.add(clothingsPerOwner1Per1);
            }
        }
        return allRecommendedClothing;
    }

    //DEZE METHODES MOETEN NOG GEMAAKT WORDEN
    public boolean setProductReview(Transaction transaction, Review review){
        //niet zeker of dit klopt
        return true;
    }
    public boolean setServiceReview(Transaction transaction, Review review){
        return true;
    }
    public boolean setShipmentMethod (Transaction transaction, Review review){
        return true;
    }

}

