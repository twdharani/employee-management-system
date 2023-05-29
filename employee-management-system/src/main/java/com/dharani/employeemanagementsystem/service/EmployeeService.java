package com.dharani.employeemanagementsystem.service;


import com.dharani.employeemanagementsystem.dto.EmployeeDTO;
import com.dharani.employeemanagementsystem.dto.UpdateEmployeeRequestDTO;
import com.dharani.employeemanagementsystem.entity.Employee;
import com.dharani.employeemanagementsystem.exception.ApplicationException;
import com.dharani.employeemanagementsystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

@Service(value = "employeeService")
@Transactional
public class EmployeeService {

    public static final String EMPLOYEE_DOES_NOT_EXIST = "Employee does not exist";
    public static final String EMPLOYEE_WITH_THIS_AADHAR_DOES_NOT_EXIST = "Employee with this aadhar doesn't exist!";
    public static final String EMPLOYEE_WITH_THIS_ID_DOES_NOT_EXIST = "Employee with this Id doesn't exist!";
    public static final String EMPLOYEE_WITH_THIS_AADHAR_ALREADY_EXIST = "Employee with this aadhar already exist!";

    @Autowired

    private EmployeeRepository employeeRepository;


    public Integer addEmployee(EmployeeDTO employeeDTO) throws ApplicationException {

        if (!employeeRepository.existsByAadhar(employeeDTO.getAadhar())) {
            Employee employee = Employee.builder()
                    .id(employeeDTO.getId())
                    .name(employeeDTO.getName())
                    .aadhar(employeeDTO.getAadhar())
                    .age(calculateAgeFrom(employeeDTO.getDateOfBirth()))
                    .dateOfBirth(employeeDTO.getDateOfBirth())
                    .dept(employeeDTO.getDept())
                    .city(employeeDTO.getCity())
                    .build();

            employeeRepository.save(employee);
            return employee.getId();

        }

        throw new ApplicationException(EMPLOYEE_WITH_THIS_AADHAR_ALREADY_EXIST);
    }

    private Integer calculateAgeFrom(LocalDate dateOfBirth) {
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(dateOfBirth, currentDate);
        return period.getYears();
        // return period.getYears() + " years " + period.getMonths() + " months " + period.getDays() + " days.";
    }

    public EmployeeDTO getEmployee(Integer employeeId) throws ApplicationException {
        Optional<Employee> optional = employeeRepository.findById(employeeId);
        Employee employee = optional.orElseThrow(() -> new ApplicationException(EMPLOYEE_DOES_NOT_EXIST));

        EmployeeDTO employeeDTO = EmployeeDTO.builder()
                .id(employee.getId())
                .name(employee.getName())
                .aadhar(employee.getAadhar())
                .age(employee.getAge())
                .dateOfBirth(employee.getDateOfBirth())
                .dept(employee.getDept())
                .city(employee.getCity())
                .build();

        return employeeDTO;
    }

    public List<EmployeeDTO> getAllEmployees() throws ApplicationException {

        List<Employee> employees = employeeRepository.findAll();

        if (employees.isEmpty()) {
            throw new ApplicationException(EMPLOYEE_DOES_NOT_EXIST);
        }

        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        employees.forEach(employee -> {
            EmployeeDTO employeeDTO = EmployeeDTO.builder()
                    .id(employee.getId())
                    .name(employee.getName())
                    .aadhar(employee.getAadhar())
                    .age(employee.getAge())
                    .dateOfBirth(employee.getDateOfBirth())
                    .dept(employee.getDept())
                    .city(employee.getCity()).build();

            employeeDTOS.add(employeeDTO);
        });

        return employeeDTOS;
    }

    public void updateEmployeeDepartment(UpdateEmployeeRequestDTO updateEmployeeRequestDTO) throws ApplicationException {

        Optional<Employee> employee = employeeRepository.findByAadhar(updateEmployeeRequestDTO.getAadhar());
        Employee employeeToUpdate = employee.orElseThrow(() -> new ApplicationException(EMPLOYEE_WITH_THIS_AADHAR_DOES_NOT_EXIST));
        employeeToUpdate.setDept(updateEmployeeRequestDTO.getDept());

        employeeRepository.save(employeeToUpdate);

    }

    public void deleteEmployee(Integer employeeId) throws ApplicationException {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        employee.orElseThrow(() -> new ApplicationException(EMPLOYEE_WITH_THIS_ID_DOES_NOT_EXIST));

        employeeRepository.deleteById(employeeId);
    }
}

