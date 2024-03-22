package com.example.assignment.service;

import com.example.assignment.dto.DesignationDTO;

import java.util.List;

/**
 * @author Thrimal Avishka <thrimalavishka99@gmail.com>
 * @since 22-Mar-24
 */
public interface DesignationService {
    boolean addDesignation(DesignationDTO designationDTO);
    boolean updateDesignation(DesignationDTO designationDTO);
    boolean deleteDesignation(Long id);
    DesignationDTO findDesignation(String name);
    List<DesignationDTO> getAllDesignations();
}
