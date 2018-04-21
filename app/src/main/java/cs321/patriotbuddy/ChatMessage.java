package cs321.patriotbuddy;

public class ChatMessage {

    public String message;
    public String user;
    public long time;

    public ChatMessage(String message, String user, long time){
        this.message = message;
        this.user = user;
        this.time = time;
    }
}
