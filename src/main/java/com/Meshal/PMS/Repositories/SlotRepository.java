package com.Meshal.PMS.Repositories;

import com.Meshal.PMS.domain.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SlotRepository extends JpaRepository<Slot, Integer> {
}
