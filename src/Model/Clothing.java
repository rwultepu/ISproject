package Model;

public class Clothing {

    private String clothingID;
    private double price;

    private int userID;
    private String categoryName;
    private String description;

    public Clothing(String clothingID, double price, int userID, String categoryName, String description){
        this.clothingID = clothingID;
        this.price = price;
        this.userID = userID;
        this.categoryName = categoryName;
        this.description = description;
    }

    //Getter clothingID
    public String getClothingID() {return clothingID;}

    //Getter price
    public double getPrice() {return price;}

    //Getter categoryName
    public String getCategoryName() {
        return categoryName;
    }

    public String getDescription() {
        return description;
    }

    public int getUserID() {
        return userID;
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
