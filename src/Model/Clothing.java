package Model;

import Database.TransactionDAO;

import java.util.ArrayList;
import java.util.Date;

public class Clothing {

    private int clothingID;
    private double price;

    private int userID;
    private String categoryName;
    private String description;
    TransactionDAO transactionDAO = new TransactionDAO();

    public Clothing(int clothingID, double price, int userID, String categoryName, String description){
        this.clothingID = clothingID;
        this.price = price;
        this.userID = userID;
        this.categoryName = categoryName;
        this.description = description;
    }

    //Getter clothingID
    public int getClothingID() {return clothingID;}

    //Getter price
    public double getPrice() {return price;}

    //Getter categoryName
    public String getCategoryName() {return categoryName;}

    public String getDescription() {return description;}

    public int getUserID() {return userID;}


    public ArrayList<Transaction> getTransactionsOfClothing(int clothingID){
        ArrayList<Transaction> transactionsOfCLothing = new ArrayList<>();
        for (Transaction t : transactionDAO.getAllTransactions())
            if(clothingID == t.getClothingID())
                transactionsOfCLothing.add(t);
        return transactionsOfCLothing;
    }

    //Methode die checkt of kledij nog beschikbaar is tussen meegegeven startdate en enddate:
    public boolean isAvailable(Date wantedStartDate, Date wantedEndDate, int clothingID){
        boolean flag = true;
        for(Transaction t : getTransactionsOfClothing(clothingID))
            if (!wantedStartDate.after(t.getEndDate()) || !wantedEndDate.before(t.getStartDate())) {
                flag = false;
                break;
            }
        return flag;
    }

    //Deze methode retourneert aan welk gender het kledingstuk gelinkt is.
    /*
    public String getGender(){
        String gender = "male";
        String genderRetreval = getCategory().getCategoryName().substring(0,0);
        if(genderRetreval.equals(Category.Gender.F))
            return gender = "female";

        return gender;
    }

    public ArrayList<Transaction> getPreviousTransactions();
        ArrayList<Transaction> previousTransactions;

        return previousTransactions;

     */
}
