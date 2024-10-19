import java.util.*;
import Reservation.*;

public class Main {
    public static void main(String[] args) {
        TrainSchedule avail = new TrainSchedule();
        avail.setTrain();
        Booking bk = new Booking(avail);
        Scanner sc = new Scanner(System.in);
        boolean bool = true;
        while(bool){
            System.out.println("1.New User\n2.Login\n3.Exit");
            int opt = sc.nextInt();
            sc.nextLine();
            switch (opt){
                case 1 :
                    bk.userRegistration();
                    break;
                case 2:
                    bk.userLogin();
                    break;
                case 3:
                    System.out.print("Do you want to exit? (y/n): ");
                    String userResponse = sc.nextLine();
                    if(!userResponse.equals("n")){
                        bool = false;
                        break;
                    }
            }
        }
    }
}