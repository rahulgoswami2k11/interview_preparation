package interview;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.*;

enum Variant {
    Electric,
    Gas
}

enum VehicleType{
    BIKE,
    SCOOTER
}

enum VehicleSize {
    SMALL,
    MID,
    LARGE
}

@Data
@AllArgsConstructor
@Builder
class Vehicle {
    VehicleSize vehicleSize;
    VehicleType vehicleType;
    Variant variant;
    String registrationNumber;
    double rentPerHour;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle vehicle)) return false;
        return Double.compare(getRentPerHour(), vehicle.getRentPerHour()) == 0 && getVehicleSize() == vehicle.getVehicleSize() && getVehicleType() == vehicle.getVehicleType() && Objects.equals(getRegistrationNumber(), vehicle.getRegistrationNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVehicleSize(), getVehicleType(), getRegistrationNumber(), getRentPerHour());
    }
}

@Data
class VehicleInventory {
    Map<VehicleType, Map<VehicleSize, Set<Vehicle>>> vehiclesIntentoryMap;

    public VehicleInventory() {
        this.vehiclesIntentoryMap = new HashMap<>();
    }

    public void addVehicle(Vehicle vehicle) {
        VehicleType type = vehicle.getVehicleType();
        VehicleSize size = vehicle.getVehicleSize();
        Map<VehicleSize, Set<Vehicle>> vehicleSizeSetMap = this.vehiclesIntentoryMap.get(type);
        if(vehicleSizeSetMap == null) {
            vehicleSizeSetMap = new HashMap<>();
        }
        Set<Vehicle> vehicles = vehicleSizeSetMap.get(size);

        if(vehicles == null) {
            vehicles = new HashSet<>();
        }
        vehicles.add(vehicle);
        vehicleSizeSetMap.put(size, vehicles);
        vehiclesIntentoryMap.put(type, vehicleSizeSetMap);
    }

    public void removeVehicle(Vehicle vehicle) {
        VehicleType type = vehicle.getVehicleType();
        VehicleSize size = vehicle.getVehicleSize();
        Map<VehicleSize, Set<Vehicle>> vehicleSizeSetMap = this.vehiclesIntentoryMap.get(type);
        Set<Vehicle> vehicles = vehicleSizeSetMap.get(size);
        vehicles.remove(vehicle);
        vehicleSizeSetMap.put(size, vehicles);
        vehiclesIntentoryMap.put(type, vehicleSizeSetMap);
    }

    public Vehicle search(VehicleType vehicleType, VehicleSize vehicleSize) {
        Map<VehicleSize, Set<Vehicle>> vehicleSizeSetMap = this.vehiclesIntentoryMap.get(vehicleType);
        if(vehicleSizeSetMap == null) {
            return null;
        }
        Set<Vehicle> vehicles = vehicleSizeSetMap.get(vehicleSize);
        if(vehicles == null || vehicles.isEmpty()) {
            return null;
        }
        return vehicles.stream().findFirst().get();
    }
}

@Data
class VehicleRentRepository {

    VehicleInventory vehicleInventory;

    Map<Vehicle, Date> rentedVehicleDataMap;

    public VehicleRentRepository(VehicleInventory vehicleInventory) {
        this.rentedVehicleDataMap = new HashMap<>();
        this.vehicleInventory = vehicleInventory;
    }

    public RentedVehicle book(Vehicle vehicle) {
        if(rentedVehicleDataMap.containsKey(vehicle)) {
            throw new RuntimeException("Vehicle already rented by other user");
        }

        Date date = new Date();
        rentedVehicleDataMap.put(vehicle, date);
        vehicleInventory.removeVehicle(vehicle);

        return new RentedVehicle(vehicle, date);
    }

    public ReturnedVehicle returnVehicle(Vehicle vehicle) {
        if(!rentedVehicleDataMap.containsKey(vehicle)) {
            throw new RuntimeException("Vehicle was not rented");
        }

        Date bookingDate = rentedVehicleDataMap.get(vehicle);
        rentedVehicleDataMap.remove(vehicle);
        vehicleInventory.addVehicle(vehicle);
        Date currentDate = new Date();

        long time = (currentDate.getTime() - bookingDate.getTime()) / (1000 * 60 * 60);

        return new ReturnedVehicle(vehicle, time * vehicle.rentPerHour);
    }
}

@Data
@AllArgsConstructor
class RentedVehicle {
    Vehicle vehicle;
    Date bookingDate;
}

@Data
@AllArgsConstructor
class ReturnedVehicle {
    Vehicle vehicle;
    double fee;
}


public class GoldCastInterview {
    public static void main(String[] args) {
        VehicleInventory vehicleInventory = new VehicleInventory();
        VehicleRentRepository vehicleRentRepository = new VehicleRentRepository(vehicleInventory);

        Vehicle vehicle = Vehicle.builder()
                .registrationNumber("KA017896")
                .vehicleSize(VehicleSize.SMALL)
                .vehicleType(VehicleType.BIKE)
                .rentPerHour(100.0)
                .build();


        vehicleInventory.addVehicle(vehicle);

        Vehicle searchedVehicle = vehicleInventory.search(VehicleType.BIKE, VehicleSize.SMALL);
        System.out.println(searchedVehicle);

        RentedVehicle rentedVehicle = vehicleRentRepository.book(vehicle);

        searchedVehicle = vehicleInventory.search(VehicleType.BIKE, VehicleSize.SMALL);
        System.out.println(searchedVehicle);

        ReturnedVehicle returnedVehicle = vehicleRentRepository.returnVehicle(vehicle);

        searchedVehicle = vehicleInventory.search(VehicleType.BIKE, VehicleSize.SMALL);
        System.out.println(searchedVehicle);

        System.out.println(returnedVehicle.getFee());

    }
}
