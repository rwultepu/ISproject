package Model;

import Database.TransactionDAO;

import java.util.ArrayList;
import java.util.Date;

public class Renter extends User {

    TransactionDAO transactionDAO = new TransactionDAO();


    public Renter(String firstName, String lastName, int userID, String email, String phoneNumber, Date userBirth, String streetName, int streetNumber, String city, int zipCode) {
        super(firstName, lastName, userID, email, phoneNumber, userBirth, streetName, streetNumber, city, zipCode);
    }

   public ArrayList<Transaction> getTransactionHistoryOfRenter(User userID){
        TransactionDAO transactionDao= new TransactionDAO();
        ArrayList<Transaction> getTransactionHistoryOfRenter= transactionDao.getAllTransactions();
        for()
            if()
                getTransactionHistoryOfRenter.add();

    }
    public ArrayList<Owner> getTopOwners(){

    }
}



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

}

     */
