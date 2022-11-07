package by.salov.tms.courseproject.services;

import by.salov.tms.courseproject.dao.UserJpaRepository;
import by.salov.tms.courseproject.entities.User;
import by.salov.tms.courseproject.entities.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserJpaRepository userJpaRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userJpaRepository.findUserByLogin(username).orElseThrow(
                () -> new UsernameNotFoundException(username)
        );
        String roleName = user.getUserRole().getRoleName();
        return new UserDetailsImpl(
               user.getLogin(),
               user.getPassword(),
               roleName
        );
    }
}
