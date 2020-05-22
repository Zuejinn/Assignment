package au.edu.utas.mekvall.assignment;

public class Ticket {
    private int mTicketID;
    private String name;
    private int number;
    private int raffle_id;
    private int phone;
    private int winner_place;

    public int getWinner_place() {
        return winner_place;
    }

    public void setWinner_place(int winner_place) {
        this.winner_place = winner_place;
    }
    public int getmTicketID() {
        return mTicketID;
    }

    public void setmTicketID(int mTicketID) {
        this.mTicketID = mTicketID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getRaffle_id() {
        return raffle_id;
    }

    public void setRaffle_id(int raffle_id) {
        this.raffle_id = raffle_id;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }


}
