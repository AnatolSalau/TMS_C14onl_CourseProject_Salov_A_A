package by.salov.tms.courseproject.repositories;

import by.salov.tms.courseproject.entities.roles.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole,Long> {

}
