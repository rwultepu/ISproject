package Model;

public class Review {

    private int reviewService;
    private int reviewProduct;


    public Review(int reviewService, int reviewProduct){
        this.reviewService = reviewService;
        this.reviewProduct = reviewProduct;
    }

    public int getReviewService() {return reviewService;}

    public int getReviewProduct() {return reviewProduct;}

}
