package com.dinnerdash.backend.repositories;

import org.springframework.stereotype.Repository;

import java.util.Optional;
import com.dinnerdash.backend.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

//We dont even need to implement this interface as JPA creates automatic queries
// using the method names. Lmao. However, we can easily define custom queries
// Too right here using the @Query antonation.
@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
	Optional<Users> findByUsername(String username);
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);
	Users findById(Integer id);
}
