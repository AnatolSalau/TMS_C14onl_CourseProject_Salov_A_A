package by.salov.tms.courseproject.dao;

import by.salov.tms.courseproject.entities.roles.UserRole;
import by.salov.tms.courseproject.repositories.UserRoleJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleDBService {

    @Autowired
    private UserRoleJpaRepository userRoleJpaRepository;

    public UserRole saveUserRole(UserRole userRole) {
        UserRole savedUserRole = userRoleJpaRepository.save(userRole);
        return savedUserRole;
    }
}
