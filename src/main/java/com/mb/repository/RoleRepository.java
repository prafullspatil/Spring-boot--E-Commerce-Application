package com.mb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mb.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>
{
	Role findByName(String name);
}
