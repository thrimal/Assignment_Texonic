package com.example.assignment.repo;

import com.example.assignment.entity.Designation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Thrimal Avishka <thrimalavishka99@gmail.com>
 * @since 22-Mar-24
 */
@Repository
public interface DesignationRepo extends JpaRepository<Designation, Long> {
    Optional<Designation> findByName(String name);
}
