package Model;

import Database.OwnerDAO;
import Database.RenterDAO;
import Database.TransactionDAO;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static java.time.temporal.ChronoUnit.DAYS;

public class Transaction {

    private String startDate;
    private String endDate;
    private int transactionID;
    private String shipmentMethod;
    private int reviewProduct;
    private int reviewService;
    private String causeName;
    private int userID;
    private int clothingID;
    private String dateOfTransaction;

    private ArrayList<String> ShipmentMethods;
    OwnerDAO ownerDAO = new OwnerDAO();


    public Transaction(String startDate, String endDate, int transactionID, String shipmentMethod, int reviewProduct, int reviewService, String causeName, int userID, int clothingID, String dateOfTransaction) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.transactionID = transactionID;
        this.shipmentMethod = shipmentMethod;
        this.reviewProduct = reviewProduct;
        this.reviewService = reviewService;
        this.causeName = causeName;
        this.userID = userID;
        this.clothingID = clothingID;
        this.dateOfTransaction = dateOfTransaction;
        String pickup = "Pick-Up";
        String homedelivery = "Home-Delivery";
        ShipmentMethods.add(pickup);
        ShipmentMethods.add(homedelivery);
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }


    public int getTransactionID() {
        return transactionID;
    }

    public String getShipmentMethod() {
        return shipmentMethod;
    }

    public int getReviewProduct() {
        return reviewProduct;
    }

    public int getReviewService() {
        return reviewService;
    }

    public String getCauseName() {
        return causeName;
    }

    public int getUserID() {
        return userID;
    }

    public int getClothingID() {
        return clothingID;
    }

    public String getDateOfTransaction() {
        return dateOfTransaction;
    }
    /*
    public double getTotalPrice(Clothing c){
        // extra berekening tussen 2 data
        double totalPrice = 0;
        long dateBeforeInMs = startDate.getTime();
        long dateAfterInMs = endDate.getTime();

        long timeDiff = Math.abs(dateAfterInMs - dateBeforeInMs);
        int numberOfDaysOfRentPeriod = (int) TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
        if(c.getClothingID() == clothingID){
            totalPrice = c.getPrice()*numberOfDaysOfRentPeriod;
        }
        return totalPrice;
    }

     */
    public String getCauseOfOwner(int userID){
        String causeOfOwner = null;
        for(Owner o : ownerDAO.getAllOwners())
            if(o.getUserID() == userID)
                causeOfOwner = o.getCauseName();
        return causeOfOwner;
    }
    /*
    public double getPercentageToCauseAtTimeOfTransaction(){

    }

     */
    public void addTransaction(Transaction transactionInput){
        TransactionDAO.save(transactionInput);
    }

}

/*
    //Deze methode annuleert de transactie en voorziet een eventuele terugbetaling.
    public boolean cancelTransaction(){

    }

    //Getter owners
    public ArrayList<User> getOwners() {return owners;}

    //Getter renters
    public ArrayList<User> getRenters() {return renters;}


} */
