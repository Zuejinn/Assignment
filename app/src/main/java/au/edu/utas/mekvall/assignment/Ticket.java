package au.edu.utas.mekvall.assignment;

public class Ticket {
    private int mTicketID;
    private String name;
    private int number;
    private int raffle_id;
    private int phone;

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
