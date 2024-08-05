package com.Meshal.PMS.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
    @PrePersist
    protected void onCreate() {
        createdDate = new Date();
    }
    private LocalDateTime modifiedDate;
    @JsonManagedReference
    @OneToMany(mappedBy = "garage")
    private List<Slot> slots;
}
