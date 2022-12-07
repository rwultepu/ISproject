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


    public ArrayList<Owner> getTopOwners(){
        ArrayList<Owner> topOwners = new ArrayList<>();
        for(Owner o: ownerDAO.getAllOwners())
            if(o.getAverageProductReviewScore()>=3 && o.getAverageServiceReviewScore()>=3)
                topOwners.add(o);
        return topOwners;
    }

    public ArrayList<Clothing> getAllRecommendedClothing(int userID){
        ArrayList<Clothing> recommendedClothing=new ArrayList<>();
        for(Owner o: getTopOwners())
            if(getTopOwners().contains(o))
                recommendedClothing.add(o.getAllClothesOfOwner());
        return recommendedClothing;
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

