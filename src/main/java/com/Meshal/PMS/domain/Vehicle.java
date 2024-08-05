package com.Meshal.PMS.domain;

import com.Meshal.PMS.enums.VehicleType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Data
@Entity(name = "Vehicle")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vehicle_id_seq")
    private int vehicleId;

    @SequenceGenerator(name = "vehicle_id_seq", sequenceName = "VEHICLE_ID_SEQ", allocationSize = 1) // Configure sequence details
    private static final String VEHICLE_ID_SEQ_NAME = "VEHICLE_ID_SEQ";
    private String manufacturer;
    private String model;

    @Enumerated
    private VehicleType vehicleType;

    @Column(
            unique = true
    )
    @NonNull
    private String licensePlate;

    @Setter(AccessLevel.NONE)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date createdDate;
    @PrePersist
    protected void onCreate() {
        createdDate = new Date();
    }
    private Date modifiedDate;


    @JsonBackReference
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_reservation")
    private Reservation reservation;

}
