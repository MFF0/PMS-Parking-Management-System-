package com.Meshal.PMS.Repositories;

import com.Meshal.PMS.domain.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
    Optional<Vehicle> findVehicleByLicensePlate(String licensePlate);
    Optional<Vehicle> findByVehicleId(int vehicleId);
}
