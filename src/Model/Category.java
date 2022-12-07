package Model;

import java.util.ArrayList;

public class Category {
    private String categoryName;
    private ArrayList<String> categories;

    public Category(String categoryName) {
        this.categoryName = categoryName;
        categories= new ArrayList<>();
        categories.add("Male Suits");
        categories.add("Male Accessories");
        categories.add("Female Mini-dress");
        categories.add("Female Midi-dress");
        categories.add("Female Maxi-dress");
        categories.add("Female Pants");
        categories.add("Female Tops");
        categories.add("Female accessories");
    }

    public String getCategoryName() {return categoryName;}


    //nog saveCategory voor al die Strings!!
}
