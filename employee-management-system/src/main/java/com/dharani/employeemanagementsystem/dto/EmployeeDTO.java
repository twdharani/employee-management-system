package com.dharani.employeemanagementsystem.dto;


import com.dharani.employeemanagementsystem.validation.LocalDatePattern;
import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    @NotNull(message = "Id should not be null")
    private Integer id;

    @NotNull(message = "Name should not be null")
    @Pattern(regexp = "[A-Za-z]+( [A-Za-z]+)*", message = "Name should be alphabets only")
    private String name;

    @Column(unique = true)
    @NotNull(message = "Please provide Aadhar Number")
    @Pattern(regexp = "^[2-9]\\d{3}\\s\\d{4}\\s\\d{4}$", message = "Invalid Aadhaar number")
    private String aadhar;

    @NotNull(message = "Date of Birth should not be null")
    @PastOrPresent(message = "Date of Birth should not be of future")
    @LocalDatePattern(message = "Invalid date format. Use dd/MM/yyyy")
    private LocalDate dateOfBirth;

    private Integer age;

    @NotNull(message = "Department should not be null")
    private String dept;

    @NotNull(message = "City should not be null")
    private String city;

    @Override
    public String toString() {
        return "EmployeeDTO [employeeID=" + id + "employeeName=" + name + ", aadhar" + aadhar + ", age=" + age + ", department=" + dept + ", city=" + city + ", dateOfBirth=" + dateOfBirth + "]";
    }
}
