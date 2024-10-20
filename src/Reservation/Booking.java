package Reservation;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Booking{
    Scanner sc =new Scanner(System.in);
    HashMap<Long, UserDetails> hm = new HashMap<>();
    HashMap<Long, Ticket> ticketDetails = new HashMap<>();
    Random random = new Random();
    TrainSchedule avail;
    boolean trainFound=false;

    public void log_transactions(String transaction){
        try{
            FileWriter writer = new FileWriter("Reservation_Transaction.txt",true);
            writer.write(new Date()+" - "+transaction+"\n");
            writer.close();
        }
        catch(IOException io){
            System.out.println("An error occurred while writing to the file.");
        }

    }

    public Booking(TrainSchedule avail){
        this.avail=avail;
    }

    public void userRegistration(){
        System.out.print("Enter your Name : ");
        String userName = sc.nextLine();
        System.out.print("Enter your phone number : ");
        long userNumber = sc.nextLong();
        sc.nextLine();
        System.out.print("Enter your email address : ");
        String userMail = sc.nextLine();
        System.out.print("Registration Successful. Please Login to continue. ");
        int userPassword = random.nextInt(100000,999999);
        System.out.println("Your Password is : "+userPassword);
        UserDetails tr = new UserDetails(userName,userMail,userPassword);
        hm.put(userNumber,tr);

        log_transactions("Registered User : "+userName+", Phone Number : "+userNumber+", Email : "+userMail);
    }
    public void userLogin(){
        System.out.print("Enter your phone number : ");
        long userNumber = sc.nextLong();
        if(hm.containsKey(userNumber)){
            UserDetails tr =hm.get(userNumber);
            System.out.print("Enter your password : ");
            int userPassword = sc.nextInt();
            sc.nextLine();
            if(userPassword==tr.userPassword()){
                UserDetails userDetails = hm.get(userNumber);

                log_transactions("Logged User : "+userDetails.userName()+", Phone Number : "+userNumber);

                System.out.println("Hi, "+ userDetails.userName());
                boolean bool = true;
                while(bool){
                    System.out.println("1. Book Ticket(s)\n2. Check PNR Status\n3. Cancel Ticket(s)\n4. Go Back");
                    int opt = sc.nextInt();
                    sc.nextLine();
                    switch(opt){
                        case 1 :
                            userDetails();
                            if(trainFound){
                                userBooking();
                            }
                            break;
                        case 2:
                            checkPNRStatus();
                            break;
                        case 3:
                            cancelTickets();
                            break;
                        case 4:
                            bool=false;
                            break;
                        default:
                            System.out.println("Invalid Choice.");
                    }
                }

            }
            else{
                System.out.println("Incorrect Password. Login Again.");
            }
        }
        else{
            System.out.println("Invalid Phone Number.");
        }

    }
    public void userDetails(){
        System.out.print("Date of Journey : ");
        String date = sc.nextLine();
        System.out.print("Origin Station : ");
        String origin = sc.nextLine();
        System.out.print("Destination Station : ");
        String destination = sc.nextLine();
        System.out.print("Class Type : ");
        String classType = sc.nextLine();
        for(Map.Entry<Integer, TrainDetails> entry : avail.tr.entrySet()){
            TrainDetails td = avail.tr.get(entry.getKey());
            JourneyDetails journeyDetails = td.getDetails();
            if(journeyDetails.origin().equals(origin) && Objects.equals(journeyDetails.destination(), destination) && Objects.equals(journeyDetails.date(), date) && Objects.equals(journeyDetails.classType(), classType)){
                System.out.println("Train Number : "+entry.getKey()+", Train Name : "+td.getTrainName()+", Available Seats : "+td.getAvailableSeats());
                trainFound = true;
            }
            else{
                trainFound=false;
            }
        }
        if(!trainFound){
            System.out.println("Trains not Available.");
        }
    }
    public void userBooking(){

        System.out.print("Enter the train Number : ");
        int trainNumber = sc.nextInt();
        sc.nextLine();
        if(!avail.tr.containsKey(trainNumber)){
            System.out.println("Invalid Train Number.");
            return;
        }
        TrainDetails td = avail.tr.get(trainNumber);

        System.out.print("Number of Passengers : ");
        int passengerCount=sc.nextInt();
        sc.nextLine();
        List<Passenger>passengers=new ArrayList<>();
        System.out.println("Passenger JourneyDetails");

        for(int i=1;i<=passengerCount;i++){
            System.out.printf("Name of Passenger %d : ",i);
            String passengerName = sc.nextLine();
            System.out.print("Age : ");
            int passengerAge = sc.nextInt();
            sc.nextLine();
            System.out.print("Gender : ");
            String passengerGender = sc.nextLine();
            passengers.add(new Passenger(passengerName,passengerAge,passengerGender));
        }
        Ticket ticket = new Ticket(trainNumber,passengers,passengerCount);
        JourneyDetails journeyDetails = td.getDetails();

        if(td.getAvailableSeats()>=passengerCount){
            ticket.setPnrStatus(PNRStatus.CONFIRMED);
            long pnrNumber = 1000000000L + random.nextInt(999999999);
            ticketDetails.put(pnrNumber, ticket);
            System.out.println("Booking Successful! Your PNR Number :  "+pnrNumber);
            for(int i=1;i<=passengerCount;i++){
                td.lessAvailableSeats();
            }
            System.out.println("Remaining Seats : "+td.getAvailableSeats());

            log_transactions("Booked Ticket PNR : "+pnrNumber+", No. of Passengers : "+passengerCount+", Origin : "+journeyDetails.origin()+", Destination : "+journeyDetails.destination()+", Class : "+journeyDetails.classType()+", Date : "+journeyDetails.date());
        }
        else{
            System.out.print("Seats unavailable. Would you like to proceed with RAC booking? (y/n): ");
            String userResponse = sc.nextLine();
            if(!userResponse.equals("n")){
                long pnrNumber = 1000000000L + random.nextInt(999999999);
                System.out.println("RAC Booking Successful! Your PNR Number :  "+pnrNumber);
                ticket.setPnrStatus(PNRStatus.RAC);
                ticketDetails.put(pnrNumber, ticket);

                log_transactions("Booked RAC Ticket PNR : "+pnrNumber+", No. of Passengers : "+passengerCount+", Origin : "+journeyDetails.origin()+", Destination : "+journeyDetails.destination()+", Class : "+journeyDetails.classType()+", Date : "+journeyDetails.date());
            }
        }
    }
    public void checkPNRStatus(){
        System.out.print("Enter your PNR Number : ");
        long pnrNumber = sc.nextLong();
        sc.nextLine();
        if(ticketDetails.containsKey(pnrNumber)){
            Ticket ticket = ticketDetails.get(pnrNumber);
            TrainDetails td = avail.tr.get(ticket.getTrainNumber());
            System.out.println("PNR Status : "+ticket.getPnrStatus());
            if(td!=null){
                JourneyDetails journeyDetails = td.getDetails();
                if(ticket.getPnrStatus()== PNRStatus.CONFIRMED||ticket.getPnrStatus()== PNRStatus.RAC){
                    System.out.println("Train Number : "+ ticket.getTrainNumber());
                    System.out.println("Train Name : "+td.getTrainName());
                    System.out.println("Date of Journey : "+ journeyDetails.date());
                    System.out.println("Passenger Details : ");
                    List<Passenger> passengers = ticket.getPassengers();
                    for(Passenger passenger : passengers){
                        System.out.println("Name : "+passenger.passengerName()+", Age : "+passenger.passengerAge()+", Gender : "+passenger.passengerGender());
                    }
                }
            }
            else{
                System.out.println("UserDetails JourneyDetails not found.");
            }
        }
        else{
            System.out.println("Invalid PNR.");
        }
    }

    public void cancelTickets(){
        System.out.print("Enter your PNR Number : ");
        long pnrNumber = sc.nextLong();
        System.out.print("Verify to cancel ticket");
        System.out.print("Enter your phone number : ");
        long userNumber = sc.nextLong();
        System.out.print("Enter your password : ");
        int userPassword = sc.nextInt();
        sc.nextLine();

        if(hm.containsKey(userNumber)){
            UserDetails tr = hm.get(userNumber);
            if(userPassword==tr.userPassword()){
                Ticket ticket = ticketDetails.get(pnrNumber);
                List<Passenger>passengers=ticket.getPassengers();
                passengers.clear();
                TrainDetails td = avail.tr.get(ticket.getTrainNumber());
                int count = ticket.getPassengerCount();
                for(int i=1;i<=count;i++){
                    td.addAvailableSeats();
                }
                ticket.setPnrStatus(PNRStatus.CANCELLED);
                System.out.println("Tickets Cancelled Successfully. Seats are available now for booking.");

                log_transactions("Cancelled Ticket PNR : "+pnrNumber);

                for(Map.Entry<Long, Ticket> entry : ticketDetails.entrySet()){
                    Ticket racTicket = entry.getValue();
                    if(racTicket.getPnrStatus()==PNRStatus.RAC && td.getAvailableSeats() >= racTicket.getPassengerCount()){
                        racTicket.setPnrStatus(PNRStatus.CONFIRMED);
                        log_transactions("RAC Ticket upgraded. PNR Number : "+entry.getKey()+", PNR Status : "+racTicket.getPnrStatus());
                        for(int i=1;i<=racTicket.getPassengerCount();i++){
                            td.lessAvailableSeats();
                        }
                    }
                }
            }
            else{
                System.out.println("Incorrect Password.");
            }
        }
        else{
            System.out.println("Invalid Phone Number.");
        }

    }
}