package com.natrumax.repository;

<<<<<<< HEAD
import com.natrumax.models.Enum.ERole;
=======
>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6
import com.natrumax.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
<<<<<<< HEAD
    Optional<Role> findByName(ERole name);


=======
    Optional<Role> findByName(String name);
>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6
}
