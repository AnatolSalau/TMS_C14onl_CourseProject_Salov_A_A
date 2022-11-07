package by.salov.tms.courseproject.entities;

import lombok.Getter;
import org.hibernate.mapping.Array;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class UserDetailsImpl implements UserDetails {

    private String login;
    private String password;
    private List<String> userRoleNames;

    public UserDetailsImpl(String login, String password, String... userRoleNames) {
        this.login = login;
        this.password = password;
        this.userRoleNames = new ArrayList<>();
        Arrays.stream(userRoleNames)
                .forEach(userRoleName -> {
                    this.userRoleNames.add(userRoleName);
                });
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userRoleNames.stream()
                .map(userRoleName -> new SimpleGrantedAuthority(userRoleName))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
