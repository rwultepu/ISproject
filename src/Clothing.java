import java.util.ArrayList;

public class Clothing {

    private String clothingID;
    private double price;
    private Category category;

    public Clothing(String clothingID, double price, Category category){
        this.clothingID = clothingID;
        this.price = price;
        this.category = category;
    }

    //Getter clothingID
    public String getClothingID() {return clothingID;}

    //Getter price
    public double getPrice() {return price;}

    //Getter category
    public Category getCategory() {return category;}

    //Deze methode retourneert aan welk gender het kledingstuk gelinkt is.
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
}
