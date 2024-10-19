package Reservation;

public class TrainDetails{
    final private String trainName;
    private int availableSeats;
    private final JourneyDetails journeyDetails;

    public TrainDetails(String trainName, int availableSeats, JourneyDetails journeyDetails){
        this.trainName=trainName;
        this.availableSeats=availableSeats;
        this.journeyDetails = journeyDetails;
    }
    public String getTrainName(){
        return trainName;
    }
    public int getAvailableSeats(){
        return availableSeats;
    }
    public void lessAvailableSeats(){
        availableSeats--;
    }
    public void addAvailableSeats(){
        availableSeats++;
    }

    public JourneyDetails getDetails() {
        return journeyDetails;
    }
}