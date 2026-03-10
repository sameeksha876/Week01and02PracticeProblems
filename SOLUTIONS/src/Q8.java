import java.util.*;


class ParkingSpot {
    String licensePlate;
    long entryTime;
    boolean occupied;


    ParkingSpot() {
        occupied = false;
    }
}


public class Q8 {


    private ParkingSpot[] table;
    private int capacity = 500;


    public Q8() {
        table = new ParkingSpot[capacity];
        for (int i = 0; i < capacity; i++) {
            table[i] = new ParkingSpot();
        }
    }


    // hash function
    private int hash(String licensePlate) {
        return Math.abs(licensePlate.hashCode()) % capacity;
    }


    public void parkVehicle(String plate) {


        int index = hash(plate);
        int probes = 0;


        while (table[index].occupied) {
            index = (index + 1) % capacity;
            probes++;
        }


        table[index].licensePlate = plate;
        table[index].entryTime = System.currentTimeMillis();
        table[index].occupied = true;


        System.out.println("Assigned spot #" + index + " (" + probes + " probes)");
    }


    public void exitVehicle(String plate) {


        int index = hash(plate);


        while (table[index].occupied) {


            if (table[index].licensePlate.equals(plate)) {


                long duration = System.currentTimeMillis() - table[index].entryTime;


                table[index].occupied = false;


                double hours = duration / (1000.0 * 60 * 60);
                double fee = hours * 5;


                System.out.println("Spot #" + index + " freed. Fee: $" + fee);
                return;
            }


            index = (index + 1) % capacity;
        }


        System.out.println("Vehicle not found");
    }


    public static void main(String[] args) {


        Q8 lot = new Q8();


        lot.parkVehicle("ABC1234");
        lot.parkVehicle("ABC1235");
        lot.parkVehicle("XYZ9999");


        lot.exitVehicle("ABC1234");
    }
}
