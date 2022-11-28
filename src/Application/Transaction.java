package Application;

import java.time.LocalDate;
import java.util.ArrayList;

public class Transaction {

    private LocalDate startDate;
    private LocalDate endDate;
    private ArrayList<User> owners;
    private ArrayList<User> renters;
    private String userIDOwner;
    private String userIDRenter;
    private Owner owner;
    private Renter renter;

    private ArrayList<Clothing> clothingsForTransaction;


    public Transaction(String userIDOwner, String userIDRenter, LocalDate endDate){

        this.owner = getOwner(userIDOwner);
        this.renter = getRenter(userIDRenter);


        this.startDate = startDate.now();
        this.endDate = endDate;
    }

    public Owner getOwner(String userIDOwner){

    }

    public Renter getRenter(String userIDRenter){

    }


    //Deze methode annuleert de transactie en voorziet een eventuele terugbetaling.
    public boolean cancelTransaction(){

    }

    //Getter owners
    public ArrayList<User> getOwners() {return owners;}

    //Getter renters
    public ArrayList<User> getRenters() {return renters;}


}
