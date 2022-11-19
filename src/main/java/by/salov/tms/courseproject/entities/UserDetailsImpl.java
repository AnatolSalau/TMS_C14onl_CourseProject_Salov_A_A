package by.salov.tms.courseproject.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
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
@Setter
@EqualsAndHashCode
@ToString
public class UserDetailsImpl implements UserDetails {

    private final String login;
    private final String password;
    private final List<String> userRoleNames;

    public UserDetailsImpl(String login, String password, List<String> userRoleNames) {
        this.login = login;
        this.password = password;
        this.userRoleNames = userRoleNames;
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
