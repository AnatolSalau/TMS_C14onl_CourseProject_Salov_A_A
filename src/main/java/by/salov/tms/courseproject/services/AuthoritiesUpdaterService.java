package by.salov.tms.courseproject.services;

import by.salov.tms.courseproject.dao.UserDBService;
import by.salov.tms.courseproject.entities.User;
import by.salov.tms.courseproject.entities.roles.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthoritiesUpdaterService {

    @Autowired
    private  UserDBService userDBService;


    public void update(User user) {
        User userByLogin = userDBService.findUserByLogin(user.getLogin());
        Set<UserRole> userRoles = userByLogin.getUserRoles();

        List<String> userRoleNames = userRoles.stream()
                .map(UserRole::getRoleName)
                .toList();
        List<SimpleGrantedAuthority> newAuthorities = userRoleNames.stream()
                .map(SimpleGrantedAuthority::new).toList();


        Authentication newAuth = new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword(), newAuthorities);

        SecurityContextHolder.getContext().setAuthentication(newAuth);
    }
}