package Reservation;

import java.util.HashMap;

public class TrainSchedule {
    HashMap<Integer, TrainDetails> tr = new HashMap<>();
    public void setTrain(){
        JourneyDetails train1 = new JourneyDetails("coimbatore","chennai","sleeper","20-10-2024");
        TrainDetails td1 = new TrainDetails("Express",2,train1);
        tr.put(101,td1);
        JourneyDetails train2 = new JourneyDetails("coimbatore","chennai","sleeper","20-10-2024");
        TrainDetails td2 = new TrainDetails("Super Fast",50,train2);
        tr.put(102,td2);
    }

}