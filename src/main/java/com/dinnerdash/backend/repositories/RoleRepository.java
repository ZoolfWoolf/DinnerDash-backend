package com.dinnerdash.backend.repositories;

import java.util.Optional;
import com.dinnerdash.backend.models.ERoles;
import com.dinnerdash.backend.models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Long> {
	Optional<Roles> findByName(ERoles name);
}
