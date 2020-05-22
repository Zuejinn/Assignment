package au.edu.utas.mekvall.assignment;

public class Raffle {
    private int mRaffleID;
    private String name;
    private String description;
    private int winners;

    public int getWinners() {
        return winners;
    }

    public void setWinners(int winners) {
        this.winners = winners;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
