package com.Meshal.PMS.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "Garage")
public class Garage {

    @Id
    @GeneratedValue
    private int garageId;
    private int zipCode;
    private double largeVehicleRate;
    private double smallVehicleRate;
    private double mediumVehicleRate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date createdDate;

    public Garage(int zipCode, double largeVehicleRate, double smallVehicleRate, double mediumVehicleRate, List<Slot> slots) {
        this.zipCode = zipCode;
        this.largeVehicleRate = largeVehicleRate;
        this.smallVehicleRate = smallVehicleRate;
        this.mediumVehicleRate = mediumVehicleRate;
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
}
