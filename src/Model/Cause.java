package Model;

import java.util.ArrayList;

public class Cause {
    private String causeName;

    private ArrayList<String> causes;

    public Cause() {
        causes = new ArrayList<>();
        causes.add("WWF");
        causes.add("ThinkPink");
        causes.add("Unicef");
        causes.add("MakeAWish");
        causes.add("GreenPeace");
    }

    public String getCauseName() {
        return causeName;
    }
}
