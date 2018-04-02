package cs321.patriotbuddy;

/**
 * Created by mr.banskota on 3/29/18.
 */

public class Group implements Messagable {

    String gName="";




    public String getName()
    {
        return this.gName;

    }

    public void setName(String nm)
    {
        this.gName=nm;
    }


    public boolean isPrivate()
    {

        return false;
    }


    @Override
    public void sendMessage(String message) {

    }

    @Override
    public void receiveMessage(String message) {

    }
}
