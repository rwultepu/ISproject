package Model;

import java.util.ArrayList;

public class Review {
    private ArrayList<Integer> productReview;
    private ArrayList<Integer> serviceReview;

    public Review() {
        productReview = new ArrayList<>();
        serviceReview = new ArrayList<>();
        productReview.add(1);
        productReview.add(2);
        productReview.add(3);
        productReview.add(4);
        productReview.add(5);
        serviceReview.add(1);
        serviceReview.add(2);
        serviceReview.add(3);
        serviceReview.add(4);
        serviceReview.add(5);
    }
}
