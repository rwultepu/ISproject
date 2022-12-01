package Application;

public class Category {

    //Opmerking: kunnen we niet beter gebruik maken van enumeraties? Zo kunnen ze makkelijk gehaald
    //worden uit andere klassen en moeten er geen ingewikkelde if-else statements en
    //equalsIgnoreCase()-methodes gebruikt worden binnen de constructor.
    private String categoryName;
    private Items item;

    public enum Gender{F,M}
    public enum Size{XS, S, M, L, XL}

    public Category(Gender gender,Items item, Size size){
        categoryName = gender + "-" + item + "-" + size;
    }

    //Getter categoryName
    public String getCategoryName() {return categoryName;}
}
