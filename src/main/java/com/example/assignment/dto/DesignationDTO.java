package com.example.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Thrimal Avishka <thrimalavishka99@gmail.com>
 * @since 22-Mar-24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DesignationDTO {
    private Long designationId;
    private String name;
    private String remark;
}
