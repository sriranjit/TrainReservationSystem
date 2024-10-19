package Reservation;

import java.util.List;

public class Ticket{
    final private int trainNumber;
    final private List<Passenger> passengers;
    final private int passengerCount;
    private PNRStatus pnrStatus;

    public Ticket(int trainNumber, List<Passenger> passengers, int passengerCount){
        this.trainNumber=trainNumber;
        this.passengers=passengers;
        this.passengerCount=passengerCount;
    }

    public int getTrainNumber() {
        return trainNumber;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public int getPassengerCount() {
        return passengerCount;
    }

    public void setPnrStatus(PNRStatus pnrStatus){
        this.pnrStatus=pnrStatus;
    }

    public PNRStatus getPnrStatus() {
        return pnrStatus;
    }
}
