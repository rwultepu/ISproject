package Model;

import java.util.ArrayList;

public class Review {
    private ArrayList<Integer> productReview;
    private ArrayList<Integer> serviceReview;

    private int serviceReviewValue;
    private int productReviewValue;


    public Review() {
        productReview = new ArrayList<>(5);
        serviceReview = new ArrayList<>(5);
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

    public int getServiceReviewValue() {
        return serviceReviewValue;
    }

    public int getProductReviewValue() {
        return productReviewValue;
    }

}
