package Model;

import Database.OwnerDAO;
import Database.TransactionDAO;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

public class  Renter extends User {

    TransactionDAO transactionDAO = new TransactionDAO();
    OwnerDAO ownerDAO = new OwnerDAO();


    public Renter(String firstName, String lastName, String email, String phoneNumber, Date userBirth, String streetName, int streetNumber, String city, int zipCode) {
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

/*
    public ArrayList<Owner> getTopOwners(){
        ArrayList<Owner> topOwners = new ArrayList<>();
        for(Owner o: ownerDAO.getAllOwners())
            if(o.getAverageProductReviewScore>=3 && o.getAverageServiceReviewScore>=3)
                topOwners.add(o);
        return topOwners;
    }

    public ArrayList<ArrayList<Clothing>> getAllRecommendedClothing(int userID){
        ArrayList<ArrayList<Clothing>> recommendedClothing=new ArrayList<>();
        for(Owner o: getTopOwners())
            if(getTopOwners().contains(o))
                recommendedClothing.add(o.getAllClothesOfOwner());
        return recommendedClothing;
    }

    public ArrayList<Clothing> getClothingWithSpecificParameters(){

    }
}
*/


    /*

    //Deze methode
    public void purchaseClothings(){

    }

    public ArrayList<Owner> getRecommendedOwners(){

    }

    public void wrightReview(Review review, String userID) throws Exception{
        if(userID == null)
            throw new Exception();

        else {
            for()

        }



    }
*/
}


