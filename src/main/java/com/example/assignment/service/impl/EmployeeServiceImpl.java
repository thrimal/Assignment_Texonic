package com.example.assignment.service.impl;

import com.example.assignment.dto.EmployeeDTO;
import com.example.assignment.entity.Employee;
import com.example.assignment.exception.NotFoundException;
import com.example.assignment.exception.ValidationException;
import com.example.assignment.repo.EmployeeRepo;
import com.example.assignment.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Thrimal Avishka <thrimalavishka99@gmail.com>
 * @since 22-Mar-24
 */
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public boolean addEmployee(EmployeeDTO employeeDTO) {
        if(!Objects.isNull(employeeDTO)){
            employeeRepo.save(modelMapper.map(employeeDTO, Employee.class));
            return true;
        }else{
            throw new ValidationException("Invalid inputs...");
        }
    }

    @Override
    public boolean updateEmployee(EmployeeDTO employeeDTO) {
        if(!Objects.isNull(employeeDTO)){
            Optional<Employee> optionalEmployee = employeeRepo.findById(employeeDTO.getEmployeeId());
            if(optionalEmployee.isPresent()){
                Employee employee = optionalEmployee.get();
                employee.setFullName(employeeDTO.getFullName());
                employee.setIsManager(employeeDTO.getIsManager());
                employeeRepo.save(employee);
                return true;
            }else {
                throw new NotFoundException("Employee not found...");
            }
        }else{
            throw new ValidationException("Invalid inputs...");
        }
    }

    @Override
    public boolean deleteEmployee(Long id) {
        if(!id.toString().isEmpty()){
            if(employeeRepo.existsById(id)){
                employeeRepo.deleteById(id);
                return true;
            }else {
                throw new NotFoundException("Employee not found...");
            }
        }else{
            throw new ValidationException("Invalid id...");
        }
    }

    @Override
    public EmployeeDTO findEmployee(String fullName) {
        if(!fullName.isEmpty()){
            Optional<Employee> optionalEmployee = employeeRepo.findByFullName(fullName);
            if(optionalEmployee.isPresent()){
                return modelMapper.map(optionalEmployee.get(), EmployeeDTO.class);
            }else{
                throw new NotFoundException("Employee not found...");
            }
        }else{
            throw new ValidationException("Invalid inputs...");
        }
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> all = employeeRepo.findAll();
        return modelMapper.map(all, new TypeToken<List<EmployeeDTO>>(){}.getType());
    }
}
