package Model;

import Database.TransactionDAO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Renter extends User {
    //hashmaps niet nodig
    //private HashMap<String, String> purchaseClothings;
    //private HashMap<String, String> historyRentedClothings;


    public Renter(String firstName, String lastName, int userID, String email, String phoneNumber, LocalDate userBirth, String streetName, int streetNumber, String city, int zipCode) {
        super(firstName, lastName, userID, email, phoneNumber, userBirth, streetName, streetNumber, city, zipCode);
    }

    //dit is van de dao: public ArrayList<Transaction> getAllTransactions()  {
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
public ArrayList<Owner> getRecommendedOwners(){
    }
     */



    /*

    //Deze methode is ook niet nodig denk ik
    public void purchaseClothing(){

    }
//deze methode is niet nodig, is voor de GUI
    public void writeReview(Review review, String userID) throws Exception{
        if(userID == null)
            throw new Exception();

        else {
            for()

        }
    }

}
