package by.salov.tms.courseproject.entities.roles;

import by.salov.tms.courseproject.entities.User;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode

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

    @OneToOne(mappedBy = "userRole", cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    private User user;

    public UserRole ( Long id, Role roleName) {
        this.id = id;
        this.roleName = roleName.toString();
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
