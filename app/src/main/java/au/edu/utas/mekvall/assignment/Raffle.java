package au.edu.utas.mekvall.assignment;

public class Raffle {

    private int mRaffleID;
    private String name;
    private String description;
    // List of tickets needed
    
    public int getmRaffleID() {
        return mRaffleID;
    }

    public void setRaffleID(int raffleID) { this.mRaffleID = raffleID; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
