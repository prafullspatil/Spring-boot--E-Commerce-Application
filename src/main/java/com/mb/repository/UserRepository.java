package com.mb.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mb.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
	User findByEmail(String email);

	boolean existsByEmail(String email);

	// User details API
	User findUserById(long id);

	Optional<User> findById(Long id);
}
