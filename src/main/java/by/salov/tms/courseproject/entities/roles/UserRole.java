package by.salov.tms.courseproject.entities.roles;

import by.salov.tms.courseproject.entities.User;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "roleName")
@ToString

@Entity
@Table(name = "roles")
@SequenceGenerator(sequenceName = "roles_id_seq",
        name = "roles_id_seq", allocationSize = 1)
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roles_id_seq")
    @Column(nullable = false)
    private  Long id;

    @Column(name = "role",nullable = false)
    private  String roleName;

    @ToString.Exclude
    @ManyToMany(
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REMOVE
            },
            fetch = FetchType.EAGER
/*            fetch = FetchType.EAGER,
            mappedBy = "userRoles"*/
    )
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "role_id", foreignKey = @ForeignKey(name = "fk_role_id"), referencedColumnName = "id") ,
            inverseJoinColumns = @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_user_id"), referencedColumnName = "id")
    )
    private Set<User> users = new HashSet<>();

    public UserRole (Role role) {
        this.roleName = role.toString();
    }
}
