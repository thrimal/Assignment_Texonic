package com.example.assignment.controller;

import com.example.assignment.dto.DesignationDTO;
import com.example.assignment.service.DesignationService;
import com.example.assignment.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Thrimal Avishka <thrimalavishka99@gmail.com>
 * @since 22-Mar-24
 */
@RestController
@RequestMapping("api/v1/designation")
@CrossOrigin
public class DesignationController {

    @Autowired
    private DesignationService designationService;

    @PostMapping
    public ResponseEntity<StandardResponse> saveDesignation(@RequestBody DesignationDTO designationDTO){
        boolean b = designationService.addDesignation(designationDTO);
        return new ResponseEntity<>(new StandardResponse(201, "Success", b), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<StandardResponse> updateDesignation(@RequestBody DesignationDTO designationDTO){
        boolean b = designationService.updateDesignation(designationDTO);
        return new ResponseEntity<>(new StandardResponse(204, "Success", b),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<StandardResponse> getAllDesignations(){
        List<DesignationDTO> allDesignations = designationService.getAllDesignations();
        return new ResponseEntity<>(new StandardResponse(200, "Success", allDesignations),HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<StandardResponse> deleteDesignation(@RequestParam("id") Long id){
        boolean b = designationService.deleteDesignation(id);
        return new ResponseEntity<>(new StandardResponse(203, "Success", b), HttpStatus.OK);
    }

    @GetMapping(path = "/byName/{name}")
    public ResponseEntity<StandardResponse> findDesignation(@PathVariable String name){
        DesignationDTO designationDTO = designationService.findDesignation(name);
        return new ResponseEntity<>(new StandardResponse(200, "Success", designationDTO),HttpStatus.OK);
    }

}
