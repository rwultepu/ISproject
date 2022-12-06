package Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Renter extends User {

    //private HashMap<String, String> purchaseClothings;
    //private HashMap<String, String> historyRentedClothings;


    public Renter(String firstName, String lastName, int userID, String email, String phoneNumber, Date userBirth, String streetName, int streetNumber, String city, int zipCode) {
        super(firstName, lastName, userID, email, phoneNumber, userBirth, streetName, streetNumber, city, zipCode);
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
