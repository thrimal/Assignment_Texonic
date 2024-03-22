package com.example.assignment.controller;

import com.example.assignment.dto.EmployeeDTO;
import com.example.assignment.service.EmployeeService;
import com.example.assignment.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Thrimal Avishka <thrimalavishka99@gmail.com>
 * @since 22-Mar-24
 */
@RestController
@RequestMapping("api/v1/employee")
@CrossOrigin
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<StandardResponse> addEmployee(@RequestBody EmployeeDTO employeeDTO){
        boolean b = employeeService.addEmployee(employeeDTO);
        return new ResponseEntity<>(new StandardResponse(201, "Success", b), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<StandardResponse> getAllEmployees(){
        List<EmployeeDTO> allEmployees = employeeService.getAllEmployees();
        return new ResponseEntity<>(new StandardResponse(200, "Success", allEmployees), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<StandardResponse> updateEmployee(@RequestBody EmployeeDTO employeeDTO){
        boolean b = employeeService.updateEmployee(employeeDTO);
        return new ResponseEntity<>(new StandardResponse(204, "Success", b),HttpStatus.OK);
    }

    @GetMapping("/byFullName/{fullName}")
    public ResponseEntity<StandardResponse> findEmployee(@PathVariable String fullName){
        EmployeeDTO employeeDTO = employeeService.findEmployee(fullName);
        return new ResponseEntity<>(new StandardResponse(200, "Success", employeeDTO), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<StandardResponse> deleteEmployee(@RequestParam("id") Long id){
        boolean b = employeeService.deleteEmployee(id);
        return new ResponseEntity<>(new StandardResponse(203, "Success", b), HttpStatus.OK);
    }
}
