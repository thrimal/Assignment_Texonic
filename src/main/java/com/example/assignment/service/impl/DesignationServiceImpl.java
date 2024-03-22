package com.example.assignment.service.impl;

import com.example.assignment.dto.DesignationDTO;
import com.example.assignment.entity.Designation;
import com.example.assignment.exception.NotFoundException;
import com.example.assignment.exception.ValidationException;
import com.example.assignment.repo.DesignationRepo;
import com.example.assignment.service.DesignationService;
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
public class DesignationServiceImpl implements DesignationService {

    @Autowired
    private DesignationRepo designationRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public boolean addDesignation(DesignationDTO designationDTO) {
        if(!Objects.isNull(designationDTO)){
            designationRepo.save(modelMapper.map(designationDTO, Designation.class));
            return true;
        }else{
            throw new ValidationException("Invalid inputs...");
        }
    }

    @Override
    public boolean updateDesignation(DesignationDTO designationDTO) {
        if(!Objects.isNull(designationDTO) && !designationDTO.getDesignationId().toString().isEmpty()){
            Optional<Designation> optionalDesignation = designationRepo.findById(designationDTO.getDesignationId());
            if(optionalDesignation.isPresent()){
                Designation designation = optionalDesignation.get();
                designation.setName(designationDTO.getName());
                designation.setRemark(designationDTO.getRemark());
                designationRepo.save(designation);
                return true;
            }else{
                throw new NotFoundException("Designation not found...");
            }
        }else{
            throw new ValidationException("Invalid inputs...");
        }
    }

    @Override
    public boolean deleteDesignation(Long id) {
        if(!id.toString().isEmpty()){
            if(designationRepo.existsById(id)){
                designationRepo.deleteById(id);
                return true;
            }else {
                throw new NotFoundException("Designation not found...");
            }
        }else {
            throw new ValidationException("Invalid id...");
        }
    }

    @Override
    public DesignationDTO findDesignation(String name) {
        if(!name.isEmpty()){
            Optional<Designation> optionalDesignation = designationRepo.findByName(name);
            if(optionalDesignation.isPresent()){
                return modelMapper.map(optionalDesignation.get(), DesignationDTO.class);
            }else {
                throw new NotFoundException("Designation not found...");
            }
        }else {
            throw new ValidationException("Invalid inputs...");
        }
    }

    @Override
    public List<DesignationDTO> getAllDesignations() {
        List<Designation> all = designationRepo.findAll();
        return modelMapper.map(all, new TypeToken<List<DesignationDTO>>(){}.getType());
    }
}
