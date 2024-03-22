package com.example.assignment.service;

import com.example.assignment.dto.EmployeeDTO;

import java.util.List;

/**
 * @author Thrimal Avishka <thrimalavishka99@gmail.com>
 * @since 22-Mar-24
 */
public interface EmployeeService {
    boolean addEmployee(EmployeeDTO employeeDTO);
    boolean updateEmployee(EmployeeDTO employeeDTO);
    boolean deleteEmployee(Long id);
    EmployeeDTO findEmployee(String fullName);
    List<EmployeeDTO> getAllEmployees();
}
