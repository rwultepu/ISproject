package Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Owner extends User {


    //private ArrayList<String> reviews;
    private String causeName;
    private double selectedPercentageToCauseOfOwner;

    //Opmerking: is het niet logisch als de cause en pledge variabele meegegeven wordt in de
    //constructor zodat deze geïnitialiseerd kan worden?
    public Owner(String firstName, String lastName, String userID, String email, String phoneNumber,
                 Date userBirth, String streetName, int streetNumber, String city, int zipCode,
                 String causeName, double selectedPercentageToCauseOfOwner){
        super(firstName, lastName, userID, email, phoneNumber, userBirth, streetName,
                streetNumber, city, zipCode);

        reviews = new ArrayList<>();
        this.causeName = causeName;
        this.selectedPercentageToCauseOfOwner = selectedPercentageToCauseOfOwner;
    }

    //Getter reviews
    //public ArrayList<String> getReviews() {return reviews;}

    //Getter causeName
    public String getCauseName() {
        return causeName;
    }

    //Deze methode retourneert de gemiddelde review score van de gebruiker.
    //Opmerking: deze methode moet eigelijk geen argumenten meekrijgen.
    /*
    public double averageReviewScore(){
        double averageReviewScore = 0.0;

        double totalReviewScore = 0.0;
        double numberOfReviews = reviews.size();

        for(String string : reviews){
            if(string.equals(Review.X))
                totalReviewScore += 1;
            else if (string.equals(Review.XX))
                totalReviewScore += 2;
            else if (string.equals(Review.XXX))
                totalReviewScore += 3;
            else if(string.equals(Review.XXXX))
                totalReviewScore += 4;
            else if(string.equals(Review.XXXXX))
                totalReviewScore += 5;}

        averageReviewScore = totalReviewScore/numberOfReviews;

            return averageReviewScore;
        }

        //Opmerking: ik denk dat het argument ownerID een string is en geen Application.Owner object.
        //Opmerking: ik denk dat deze methode gelinkt moet worden aan de database.
        public void addClothing(String ownerID, String clothingID, double price){

        }

        //Opmerking: ik denk dat we beter gebruik kunnen maken van de variabele clothingID
        //in de plaats van een clothing object om zo onrechtstreeks het object op te zoeken.
        //Opmerking: ik denk dat deze methode gelinkt moet worden aan de database.
        public void deleteClothing(String ownerID, String clothingID){

        }

        //Opmerking: ik denk dat deze methode gelinkt moet worden aan de database
        //vandaar dat ik de ArrayList even initialiseerd als null.
        public ArrayList<Clothing> getAllClothingsOfOwner(String ownerID){
            ArrayList<Clothing> allClothingsOfOwner = null;

            return allClothingsOfOwner;
        }

        //Deze methode verandert het goede doel.
        //Opmerking: indien we de klasse Application.Cause in een enumeratie willen veranderen
        //zullen er nog wijzigingen nodig zijn aan deze methode, eens reflecteren.
        public void changeCause(Cause cause) throws Exception{
            if(cause == null)
                throw new Exception();
            else
                this.causeName = cause;
        }

        //Deze methode verandert het percentage dat de eigenaar doneert aan
        //het goede doel.
        public void tunePledge(double newPledge) throws Exception{
            if(newPledge <= 0.0)
                throw new Exception();

            else
                this.selectedPercentageToCauseOfOwner = newPledge;
        }

        public HashMap< ArrayList<Clothing>, Double> bundledClothing(ArrayList<Clothing> bundledClothings,
                                                                     double price) {
            HashMap<ArrayList<Clothing>, Double> bundleClothingsAndPrice = new HashMap<>();
            bundleClothingsAndPrice.put(bundledClothings, price);

            return bundleClothingsAndPrice;
        }


        public double setPricesBasedOnMarket(){
            double recommendedPrice = 0.0;

            if()

        }

        //gettersToevoegenVoorJDBC


    public double getSelectedPercentageToCauseOfOwner() {
        return selectedPercentageToCauseOfOwner;
    }
}






