package by.salov.tms.courseproject.repositories;

import by.salov.tms.courseproject.entities.roles.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface UserRoleJpaRepository extends JpaRepository<UserRole,Long> {
    void deleteUserRoleById(Long id);
    UserRole findUserRoleById(Long id);
}
