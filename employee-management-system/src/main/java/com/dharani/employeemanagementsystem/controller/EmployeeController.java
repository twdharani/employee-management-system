package com.dharani.employeemanagementsystem.controller;

import com.dharani.employeemanagementsystem.dto.EmployeeDTO;
import com.dharani.employeemanagementsystem.dto.UpdateEmployeeRequestDTO;
import com.dharani.employeemanagementsystem.exception.ApplicationException;
import com.dharani.employeemanagementsystem.service.EmployeeService;
import com.dharani.employeemanagementsystem.utility.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping(value = "/employees")
@Validated
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping()
    public ResponseEntity<Object> addEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) throws ApplicationException {
        Integer employeeId = employeeService.addEmployee(employeeDTO);
        String successMessage = "Inserted successfully with employeeId  " + employeeId;
        return ResponseHandler.generateSuccessResponse(successMessage,HttpStatus.CREATED );
    }

    @GetMapping(value = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable @Min(value = 1, message = "{user userId invalid}") @Max(value = 100, message = "{user userId invalid}") Integer employeeId) throws ApplicationException {
        EmployeeDTO employee = employeeService.getEmployee(employeeId);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() throws ApplicationException {
        List<EmployeeDTO> employeeDTOList = employeeService.getAllEmployees();
        return new ResponseEntity<>(employeeDTOList, HttpStatus.OK);
    }

    @PutMapping(value = "/{employeeId}")
    public ResponseEntity<Object> updateEmployeeDepartment(@PathVariable Integer employeeId, @Valid @RequestBody UpdateEmployeeRequestDTO updateEmployeeRequestDTO)
            throws ApplicationException {

        employeeService.updateEmployeeDepartment(updateEmployeeRequestDTO);

        String successMessage =  "Updated successfully";
        return ResponseHandler.generateSuccessResponse(successMessage, HttpStatus.OK);
    }


    @DeleteMapping(value = "/{employeeId}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable Integer employeeId) throws ApplicationException {
        employeeService.deleteEmployee(employeeId);

        String successMessage =  "Deleted successfully";
        return ResponseHandler.generateSuccessResponse(successMessage, HttpStatus.OK);
    }


}
