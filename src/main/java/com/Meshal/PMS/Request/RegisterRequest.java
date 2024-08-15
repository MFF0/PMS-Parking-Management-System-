package com.Meshal.PMS.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class RegisterRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String dob;

//    public boolean isOlderThan18() {
//
//            String dobString = this.getDob();
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//            LocalDate birthDate = LocalDate.parse(dobString, formatter);
//            LocalDate currentDate = LocalDate.now();
//            int age = Period.between(birthDate, currentDate).getYears();
//            return age >= 18;
//
//    }
}
