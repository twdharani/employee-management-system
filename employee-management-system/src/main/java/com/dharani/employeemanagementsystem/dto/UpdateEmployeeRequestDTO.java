package com.dharani.employeemanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEmployeeRequestDTO {

    @Column(unique = true)
    @NotNull(message = "Please provide Aadhar Number")
    @Pattern(regexp = "^[2-9]\\d{3}\\s\\d{4}\\s\\d{4}$", message = "Invalid Aadhaar number")
    private String aadhar;

    @NotNull(message = "Department should not be null")
    private String dept;
}
