package com.Meshal.PMS.domain;

import com.Meshal.PMS.enums.VehicleType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "Slot")
public class Slot {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "slot_id_seq")
    private int slotId;
    @SequenceGenerator(name = "slot_id_seq", sequenceName = "SLOT_ID_SEQ", allocationSize = 1)
    private static final String SLOT_ID_SEQ_NAME = "SLOT_ID_SEQ";
    private boolean isOccupied = false;
    private VehicleType vehicleType;
    private int floorNumber;
    private int slotNumber;
    @JsonBackReference
    @ManyToOne()
    @JoinColumn(name = "garage_id")
    private Garage garage;
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "item")
    private Set<Reservation>  reservations = new HashSet<>();

}
