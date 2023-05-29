package com.dharani.employeemanagementsystem.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "employeetable")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {

    @Id
    private  Integer id;

    private String name;

    private String aadhar;

    private LocalDate dateOfBirth;

    private Integer age;

    private String dept;

    private String city;

}
