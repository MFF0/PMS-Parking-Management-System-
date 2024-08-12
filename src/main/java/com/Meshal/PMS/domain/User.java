package com.Meshal.PMS.domain;

import com.Meshal.PMS.enums.Role;
import com.Meshal.PMS.enums.UserStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ToString
@Entity(name = "User")
public class User  {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    private int userId;

    @SequenceGenerator(name = "user_id_seq", sequenceName = "USER_ID_SEQ", allocationSize = 1)
    private static final String USER_ID_SEQ_NAME = "USER_ID_SEQ";
    private String firstName;
    private String lastName;
    @Column(
            unique = true
    )
    private String email;
    private String password;
    private String dateOfBirth;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date createdDate;
    @PrePersist
    protected void onCreate() {
        createdDate = new Date();
    }

    @Enumerated(EnumType.STRING)
    private Role role;
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus = UserStatus.ACTIVE;

    @LastModifiedDate
    private Date modifiedDate;

    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private List<Vehicle> vehicles;

    public User(String firstName, String lastName,
                String email, String password, String dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dob;

    }

}
