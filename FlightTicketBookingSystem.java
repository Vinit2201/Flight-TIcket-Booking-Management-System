import java.util.*;
 class Flight
 {
     private String flightNumber;
     private String destination;
     private int totalSeats;
     private int bookedSeats;
     public Flight(String flightNumber, String destination,int totalSeats)
     {
         this.flightNumber=flightNumber;
         this.destination=destination;
         this.totalSeats=totalSeats;
         this.bookedSeats=0;
     }
     public String getFlightNumber()
     {
         return flightNumber;
     }

     public String getDestination()
     {
         return destination;
     }
     public int getAvailableSeats()
     {
         return totalSeats - bookedSeats;
     }
     public boolean bookSeat()
     {
         if(bookedSeats<totalSeats)
         {
             bookedSeats++;
             return true;
         }
         else
         {
             return false;
         }
     }
     public void displayFlightInfo()
     {
         System.out.println("Flight Number:"+ flightNumber);
         System.out.println("Destination:"+ destination);
         System.out.println("Total Seats:"+ totalSeats);
         System.out.println("Available Seats:"+ getAvailableSeats());
     }
 }
 class Passenger
 {
     private String name;
     private int age;
     private String passportNumber;
     public Passenger(String name, int age, String passportNumber)
     {
         this.name=name;
         this.age=age;
         this.passportNumber=passportNumber;
     }
     public String getName()
     {
         return name;
     }
     public void displayPassengerInfo()
     {
         System.out.println("Passenger Name:"+ name);
         System.out.println("Age:"+ age);
         System.out.println("Passport Number:"+ passportNumber);
     }
 }
class Booking
{
    private static int bookingCounter=1000;
    private String bookingId;
    private Passenger passenger;
    private Flight flight;
    public Booking(Passenger passenger,Flight flight)
    {
        this.passenger=passenger;
        this.flight=flight;
        this.bookingId="BKG" + (++bookingCounter);
    }
    public void confirmBooking()
    {
        if(flight.bookSeat())
        {
            System.out.println("Booking Confirmed!");
            System.out.println("Booking Id: "+ bookingId);
            passenger.displayPassengerInfo();
            System.out.println("Flight Number: "+ flight.getFlightNumber());
            System.out.println("Destination: "+ flight.getDestination());
        }
        else
        {
            System.out.println("Booking Failed! No seats available.");
        }
    }
}
class FlightManagementSystem
{
    private Flight[] flights;
    private int flightCount;
    public FlightManagementSystem(int size)
    {
        flights=new Flight[size];
        flightCount=0;
    }
    public void addFlight(Flight flight)
    {
        if (flightCount<flights.length)
        {
            flights[flightCount]=flight;
            flightCount++;
        }
        else
        {
            System.out.println("Cannot add more flights. Array is full.");
        }
    }
    public Flight searchFlight(String destination)
    {
        for(int i=0;i<flightCount;i++)
        {
            if(flights[i].getDestination().equalsIgnoreCase(destination))
            {
                return flights[i];
            }

        }
        return null;
    }
    public void displayAllFlights()
    {
        for(int i =0;i<flightCount;i++)
        {
            flights[i].displayFlightInfo();
            System.out.println("-------------------------------");
        }
    }
}
//Main class
public class FlightTicketBookingSystem
{
    public static void main(String[] args)
    {
        Scanner sc= new Scanner(System.in);
        FlightManagementSystem fms=new FlightManagementSystem(5);
        fms.addFlight(new Flight("A3280","Delhi",5));
        fms.addFlight(new Flight("A3281","Dubai",2));
        fms.addFlight(new Flight("A3282","Mumbai",3));
        fms.addFlight(new Flight("A3283","Chennai",5));
        fms.addFlight(new Flight("A3284","London",6));
        while(true)
        {
            System.out.println("\n========== Flight Booking System =======");
            System.out.println("1. View All Flights");
            System.out.println("2. Search Flight by Destination");
            System.out.println("3. Book a Flight");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice)
            {
                case 1:
                    fms.displayAllFlights();
                    break;

                case 2:
                    System.out.print("Enter destination: ");
                    String dest = sc.nextLine();
                    Flight found = fms.searchFlight(dest);
                    if (found != null)
                    {
                        found.displayFlightInfo();
                    }
                    else
                    {
                        System.out.println("No flight found for " + dest);
                    }
                    break;

                case 3:
                    System.out.print("Enter destination to book: ");
                    String destination = sc.nextLine();
                    Flight flightToBook = fms.searchFlight(destination);

                    if (flightToBook != null)
                    {
                        System.out.print("Enter Passenger Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter Age: ");
                        int age = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Passport Number: ");
                        String passport = sc.nextLine();

                        Passenger p = new Passenger(name, age, passport);
                        Booking b = new Booking(p, flightToBook);
                        b.confirmBooking();
                    }
                    else
                    {
                        System.out.println("No flight found for " + destination);
                    }
                    break;

                case 4:
                    System.out.println("Thank you for using the system!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}