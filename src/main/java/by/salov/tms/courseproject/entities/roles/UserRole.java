package by.salov.tms.courseproject.entities.roles;

import by.salov.tms.courseproject.entities.User;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString

@Entity
@Table(name = "user_role")
@SequenceGenerator(sequenceName = "user_role_id_seq",
        name = "user_role_id_seq", allocationSize = 1)
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_role_id_seq")
    @Column(nullable = false)
    private  Long id;

    @Column(name = "role",nullable = false)
    private  String roleName;

    @ToString.Exclude
    @OneToMany(mappedBy = "userRole", cascade = {CascadeType.PERSIST,
    CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    private List<User> users;

    public UserRole ( Long id, Role roleName) {
        this.id = id;
        this.roleName = roleName.toString();
    }
}
