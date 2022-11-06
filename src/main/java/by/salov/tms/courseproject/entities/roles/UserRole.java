package by.salov.tms.courseproject.entities.roles;

import by.salov.tms.courseproject.entities.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Column(nullable = false)
    private  String role;

    @OneToOne(mappedBy = "userRole", cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    private User user;

    public UserRole ( Long id, Role role) {
        this.id = id;
        this.role = role.toString();
    }
}
