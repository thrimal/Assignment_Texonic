package com.example.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Thrimal Avishka <thrimalavishka99@gmail.com>
 * @since 22-Mar-24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private Long employeeId;
    private String fullName;
    private Date dateOfJoining;
    private Boolean isManager;
}
