package com.Meshal.PMS.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "Reservation")
public class Reservation {

    @Id
    @GeneratedValue
    private int reservationId;
    private Date reservationDate;
    private long reservationDuration;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date createdDate;
    @PrePersist
    protected void onCreate() {
        createdDate = new Date();
    }
    private LocalDateTime modifiedDate;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "reservation")
    private Vehicle vehicle;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "reservation_slot", joinColumns = {@JoinColumn(name = "reservation_fk")},
    inverseJoinColumns = {@JoinColumn(name = "slot_fk")})
    private Set<Slot> slots = new HashSet<>();
}
