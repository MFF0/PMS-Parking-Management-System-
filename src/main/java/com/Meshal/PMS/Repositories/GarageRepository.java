package com.Meshal.PMS.Repositories;

import com.Meshal.PMS.domain.Garage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GarageRepository extends JpaRepository<Garage, Integer> {
}
