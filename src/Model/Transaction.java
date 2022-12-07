package Model;

import Database.ClothingDAO;
import Database.OwnerDAO;
import Database.TransactionDAO;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;

public class Transaction {

    private String startDate;
    private String endDate;
    private int transactionID;
    private String shipmentMethod;
    private int reviewProduct;
    private int reviewService;
    private String causeName;
    // userID van de renter:
    private int userID;
    private int clothingID;
    private String dateOfTransaction;

    private ArrayList<String> shipmentMethods;
    private double amountToCause;
    OwnerDAO ownerDAO = new OwnerDAO();
    ClothingDAO clothingDAO = new ClothingDAO();


    public Transaction(String startDate, String endDate, int transactionID, String shipmentMethod, int reviewProduct, int reviewService, String causeName, int userID, int clothingID, String dateOfTransaction, double amountToCause) {
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
        shipmentMethods.add(pickup);
        shipmentMethods.add(homedelivery);
        this.amountToCause = amountToCause;
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

    public double getAmountToCause() {
        return amountToCause;
    }

    public double getTotalPrice(){
        LocalDate startDateLD = stringToLocalDate(startDate);
        LocalDate endDateLD = stringToLocalDate(endDate);
        int numberOfDaysOfRentPeriod = (int)Duration.between(startDateLD, endDateLD).toDays();
        double totalPrice = 0.0;
        for(Clothing c : clothingDAO.getAllClothing())
            if(c.getClothingID() == clothingID)
                totalPrice = c.getPrice()*numberOfDaysOfRentPeriod;
        return totalPrice;
    }

    // is deze wel nodig?:
    public String getCauseOfOwner(){
        String causeOfOwner = null;
        boolean flag = false;
        for(Owner o : ownerDAO.getAllOwners()){
            for(Clothing c : o.getAllClothesOfOwner())
                if (c.getClothingID() == clothingID) {
                    flag = true;
                    break;
                }
            causeOfOwner = o.getCauseName();
        }
    return causeOfOwner;
    }

    public double getPercentageToCauseAtTimeOfTransaction(){

    }
    public boolean addTransaction(Transaction transactionInput){
        TransactionDAO.save(transactionInput);
        return true;
    }


    public boolean isValidDate(String date){
        boolean flag = false;

        int dayString = Integer.parseInt(date.substring(0,2));
        int monthString = Integer.parseInt(date.substring(3,5));
        int yearString = Integer.parseInt(date.substring(6,10));

        boolean leapYear = (yearString%4 == 0);

        if(leapYear){
            if((yearString%100 == 0) && (yearString%400 !=0))
                leapYear = false;}

        if((monthString>= 1) && (monthString<= 12))
            if ((monthString == 9)||(monthString == 4)||(monthString == 11 )||(monthString == 6) ) {
                if ((dayString >= 1) && (dayString <= 30))
                    flag = true;
                else
                    flag = false;
            }

            else if ((monthString == 2)) {
                if ((leapYear))
                    if ((dayString <= 29) && (dayString >= 1))
                        flag = true;
                    else
                        flag = false;

                else if ((dayString <= 28) && (dayString >= 1))
                    flag = true;
                else
                    flag = false;
            }

            else
            if ((dayString>=1)&& (dayString<=31))
                flag = true;
            else
                flag = false;

        else
            flag = false;

        return flag;
    }

    public LocalDate stringToLocalDate(String date){
        LocalDate stringToLocalDate = null;

        int day = Integer.parseInt(date.substring(0,2));
        int month = Integer.parseInt(date.substring(3,5));
        int year = Integer.parseInt(date.substring(6,10));

        if(isValidDate(date))
            stringToLocalDate = LocalDate.of(day, month, year);

        return stringToLocalDate;
    }

    //DEZE METHODES MOETEN NOG GEMAAKT WORDEN
    public boolean setProductReview(Review review){
        reviewProduct = getReviewProduct();
        return true;
    }
    public boolean setServiceReview(Review review){
        reviewService = getReviewService();
        return true;
    }
    public boolean setShipmentMethod (String shipmentMethod){
        this.shipmentMethod = shipmentMethod;
        return true;
    }
}
