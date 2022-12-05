package Model;

public class Cause {
    private String causeName;

    public enum Causes {MakeAWish,WWF,GreenPeace,ThinkPink,Unicef}

    Causes name = Causes.Unicef;
    public String getCauseName(){
        switch(name){
            case MakeAWish:{
                causeName = "MakeAWish";
                break;
            }
            case WWF:{
                causeName = "WWF";
                break;
            }
            case GreenPeace:{
                causeName = "GreenPeace";
                break;
            }
            case ThinkPink:{
                causeName = "ThinkPink";
                break;
            }
            case Unicef:{
                causeName = "Unicef";
                break;
            }
            default:{
                System.out.println("error");
                break;
            }
        }
        return causeName;
    }

}
