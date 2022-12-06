package Model;

import java.util.ArrayList;
import java.util.Date;

public class Transaction {

    private Date startDate;
    private Date endDate;
    private int transactionID;
    private String shipmentMethod;
    private int reviewProduct;
    private int reviewService;
    private String causeName;
    private int userID;
    private int clothingID;

    private ArrayList<String> ShipmentMethods;


    public Transaction(Date startDate, Date endDate, int transactionID, String shipmentMethod, int reviewProduct, int reviewService, String causeName, int userID, int clothingID) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.transactionID = transactionID;
        this.shipmentMethod = shipmentMethod;
        this.reviewProduct = reviewProduct;
        this.reviewService = reviewService;
        this.causeName = causeName;
        this.userID = userID;
        this.clothingID = clothingID;
        String pickup = "Pick-Up";
        String homedelivery = "Home-Delivery";
        ShipmentMethods.add(pickup);
        ShipmentMethods.add(homedelivery);
    }

    public Owner getOwner(String userIDOwner){

    }

    public Renter getRenter(String userIDRenter){

    }

/*
    //Deze methode annuleert de transactie en voorziet een eventuele terugbetaling.
    public boolean cancelTransaction(){

    }

    //Getter owners
    public ArrayList<User> getOwners() {return owners;}

    //Getter renters
    public ArrayList<User> getRenters() {return renters;}


}
