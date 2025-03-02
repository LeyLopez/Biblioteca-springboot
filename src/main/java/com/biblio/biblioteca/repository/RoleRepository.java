package com.biblio.biblioteca.repository;

import com.biblio.biblioteca.entity.ERole;
import com.biblio.biblioteca.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
