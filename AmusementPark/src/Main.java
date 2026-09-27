import com.example.amusementpark.app.ConsoleMenu;
import com.example.amusementpark.model.attraction.*;
import com.example.amusementpark.model.park.Park;
import com.example.amusementpark.model.person.Guard;
import com.example.amusementpark.service.*;

public class Main
{
  public static void main(String[] args)
  {
    Park park = new Park("Весёлые горки");
    park.addAttraction(new RollerCoaster(1000, 20, 9, 3, 140, 50, 10, 22, 800, 40, 120, 2));
    park.addAttraction(new FerrisWheel(1500, 40, 2, 8, 100, 20, 10, 23, 20, 4, 60));
    park.addAttraction(new Carousel(500, 30, 1, 5, 80, 15, 9, 22, 24, "Вальс"));

    TicketOffice office = new TicketOffice(10000);
    Guard seller = new Guard("Пётр", 40, 80, 180, "EMP-001", 50000, "day", 1);
    park.addEmployee(seller);

    ParkService parkService = new ParkService(park, office);
    SimulationService simulation = new SimulationService(parkService, seller);

    new ConsoleMenu(park, simulation).run();
  }
}
