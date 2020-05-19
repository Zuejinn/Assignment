package au.edu.utas.mekvall.assignment;

public class Ticket {
    private int mTicketID;
    private String name;
    private int number;

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

    public int getRaffle() {
        return raffle;
    }

    public void setRaffle(int raffle) {
        this.raffle = raffle;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    private int raffle;
    private int phone;

}
