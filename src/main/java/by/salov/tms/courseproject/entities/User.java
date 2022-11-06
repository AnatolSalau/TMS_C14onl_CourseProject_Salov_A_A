package by.salov.tms.courseproject.entities;

import by.salov.tms.courseproject.entities.roles.UserRole;
import lombok.*;

import javax.persistence.*;
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor

@Entity
@Table(name = "users")
@SequenceGenerator(sequenceName = "users_id_seq",
        name = "users_id_seq", allocationSize = 1)
public  class User extends People{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_seq")
    @Column(nullable = false)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_role_id", foreignKey = @ForeignKey(name = "fk_user_role_id"))
    private UserRole userRole;

    public User(String firstName, String secondName, Long id, UserRole userRole) {
        super(firstName, secondName);
        this.id = id;
        this.userRole = userRole;
    }
}