package by.salov.tms.courseproject.entities;

import by.salov.tms.courseproject.entities.roles.Role;
import by.salov.tms.courseproject.entities.roles.UserRole;
import by.salov.tms.courseproject.exceptions.UserException;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode()
@ToString

@Entity
@Table(name = "users")
@SequenceGenerator(sequenceName = "users_id_seq",
        name = "users_id_seq", allocationSize = 1)
public  class User extends People{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_seq")
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String login;
    @Column(nullable = false)
    private String password;

    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.ALL},
            mappedBy = "users"
    )
    private Set<UserRole> userRoles = new HashSet<>();

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToOne(mappedBy = "user", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private Doctor doctor;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToOne(mappedBy = "user", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private Patient patient;

    public User(String firstName, String secondName, String password, HashSet<UserRole> userRoles) {
        super(firstName, secondName);
        this.userRoles = userRoles;
        this.password = password;
    }
}
