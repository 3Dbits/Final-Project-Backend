package com.brights.bookcrewproject3.security.repository;

import com.brights.bookcrewproject3.security.model.Role;
import com.brights.bookcrewproject3.security.model.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(UserRoles name);
}
