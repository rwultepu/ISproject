package Model;

import Database.ClothingDAO;
import Database.OwnerDAO;
import Database.RenterDAO;
import Database.TransactionDAO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Clothing {

    private int clothingID;
    private double price;
    //userID van Owner:
    private int userID;
    private String categoryName;
    private String description;
    // counter die ClothingID bepaalt, telt op als een nieuw clothing object wordt weggeschreven
    private int counter = 1;
    TransactionDAO transactionDAO = new TransactionDAO();
    ClothingDAO clothingDAO = new ClothingDAO();

    public Clothing(int clothingID, double price, int userID, String categoryName, String description){
        this.clothingID = clothingID;
        this.price = price;
        this.userID = userID;
        this.categoryName = categoryName;
        this.description = description;
    }

    public int getClothingID() {return clothingID;}
    public double getPrice() {return price;}
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
    public boolean isAvailable(LocalDate wantedStartDate, LocalDate wantedEndDate, int clothingID){
        boolean flag = true;
        for(Transaction t : getTransactionsOfClothing(clothingID))
            if (!wantedStartDate.isAfter(stringToLocalDate(t.getEndDate())) || !wantedEndDate.isBefore(stringToLocalDate(t.getStartDate()))){
                flag = false;
                break;
            }
        return flag;
    }

    public boolean isValidDate(String date){
        boolean flag = false;

        int dayString = Integer.parseInt(date.substring(0,1));
        int monthString = Integer.parseInt(date.substring(3,4));
        int yearString = Integer.parseInt(date.substring(6,9));

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

        int day = Integer.parseInt(date.substring(0, 1));
        int month = Integer.parseInt(date.substring(3, 4));
        int year = Integer.parseInt(date.substring(6,9));

        if(isValidDate(date))
            stringToLocalDate = LocalDate.of(day, month, year);

        return stringToLocalDate;
    }

    public ArrayList<Clothing> getAvailableClothing(LocalDate wantedStartDate, LocalDate wantedEndDate){
        ArrayList <Clothing> availableClothing = new ArrayList<>();
        for(Clothing c : clothingDAO.getAllClothing())
            if(c.isAvailable(wantedStartDate, wantedEndDate,c.getClothingID()))
                availableClothing.add(c);
        return availableClothing;
    }

    public ArrayList<Clothing> getClothingWithSpecificParameters(String wantedStartdate, String wantedEndDate, Category category){
        ArrayList<Clothing> clothingWithSpecificParameter = new ArrayList<>();

        LocalDate wantedStartDateLD = stringToLocalDate(wantedStartdate);
        LocalDate wantedEndDateLD = stringToLocalDate(wantedEndDate);

        for(Clothing clothing : getAvailableClothing(wantedStartDateLD, wantedEndDateLD))
            if(clothing.getCategoryName().equals(category.getCategoryName()))
                clothingWithSpecificParameter.add(clothing);

        return clothingWithSpecificParameter;
    }

    public void addClothing(Clothing clothingInput){
        clothingDAO.saveClothing(clothingInput);
        counter++;
    }

    public void deleteClothing(Clothing clothingInput){
        clothingDAO.deleteClothing(clothingInput);
    }
}
