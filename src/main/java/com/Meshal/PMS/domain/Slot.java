package com.Meshal.PMS.domain;

import com.Meshal.PMS.dto.SlotDto;
import com.Meshal.PMS.enums.VehicleType;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "garage_id")
    private Garage garage;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "slots")
    private Set<Reservation>  reservations = new HashSet<>();

    public Slot(boolean occupied, VehicleType vehicleType, Garage garage) {
        this.isOccupied = occupied;
        this.vehicleType = vehicleType;
        this.garage = garage;
    }

    @JsonIgnore
    public SlotDto getSlotDto() {
        SlotDto slotDto = new SlotDto();
        slotDto.setVehicleType(this.vehicleType);
        slotDto.setGarage(this.garage);
        slotDto.setReservations(this.reservations);
        slotDto.setOccupied(this.isOccupied);
        return slotDto;
    }
}
