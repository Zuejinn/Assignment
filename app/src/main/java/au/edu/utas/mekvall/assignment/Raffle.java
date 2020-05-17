package au.edu.utas.mekvall.assignment;

public class Raffle {
    private int mRaffleID;
    private String name;
    // List of tickets needed
    
    public int getmRaffleID() {
        return mRaffleID;
    }

    public String getName() {
        return name;
    }

    public void setRaffleID(int raffleID) {
        this.mRaffleID = raffleID;
    }

    public void setName(String name) {
        this.name = name;
    }
}
