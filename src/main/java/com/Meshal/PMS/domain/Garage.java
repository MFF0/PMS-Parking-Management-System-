package com.Meshal.PMS.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Garage")
public class Garage {

    @Id
    @GeneratedValue
    private int garageId;
    private int zipCode;
    private double largeVehicleRate;
    private double smallVehicleRate;
    private double mediumVehicleRate;
    private int capacity;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date createdDate;

    public Garage(int zipCode, double largeVehicleRate, double smallVehicleRate, double mediumVehicleRate, int capacity, List<Slot> slots) {
        this.zipCode = zipCode;
        this.largeVehicleRate = largeVehicleRate;
        this.smallVehicleRate = smallVehicleRate;
        this.mediumVehicleRate = mediumVehicleRate;
        this.capacity = capacity;
        this.slots = slots;
    }

    @PrePersist
    protected void onCreate() {
        createdDate = new Date();
    }
    private LocalDateTime modifiedDate;

    @JsonManagedReference
    @OneToMany(mappedBy = "garage")
    private List<Slot> slots;

    @Override
    public String toString() {
        return "Garage{" +
                "id=" + garageId +
                ", zipcode='" + zipCode + '\'' +
                ", largeVehicleRate='" + largeVehicleRate + '\'' +
                ", mediumVehicleRate='" + mediumVehicleRate + '\'' +
                ", smallVehicleRate='" + smallVehicleRate + '\'' +
                ", capacity=" + capacity +
                ", numberOfSlotsAdded=" + slots.size() +
                '}';
    }
}
