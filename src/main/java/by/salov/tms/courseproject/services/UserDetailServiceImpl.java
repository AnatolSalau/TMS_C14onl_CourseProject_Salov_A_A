package by.salov.tms.courseproject.services;

import by.salov.tms.courseproject.entities.roles.UserRole;
import by.salov.tms.courseproject.repositories.UserJpaRepository;
import by.salov.tms.courseproject.entities.User;
import by.salov.tms.courseproject.entities.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserJpaRepository userJpaRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userJpaRepository.findUserByLogin(username).orElseThrow(
                () -> new UsernameNotFoundException(username)
        );
        System.out.println(user);

        Set<UserRole> userRoles = user.getUserRoles();
        System.out.println(userRoles);

        List<String> userRoleNames = userRoles.stream()
                .map(UserRole::getRoleName)
                .toList();
        System.out.println(userRoleNames);

        UserDetailsImpl userDetails = new UserDetailsImpl(
                user.getLogin(),
                user.getPassword(),
                userRoleNames
        );
        System.out.println(userDetails);
        return userDetails;
    }


}
