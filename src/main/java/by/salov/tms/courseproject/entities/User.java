package by.salov.tms.courseproject.entities;

import by.salov.tms.courseproject.entities.roles.Role;
import by.salov.tms.courseproject.entities.roles.UserRole;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**Entity for User */
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode()
@ToString

@Entity
@Table(name = "users")
@SequenceGenerator(sequenceName = "users_id_seq",
        name = "users_id_seq", allocationSize = 1)
public class User extends People {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_seq")
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String login;
    @Column(nullable = false)
    private String password;

    @Column(name = "icon")
    @ToString.Exclude
    private byte[] icon;
    @ManyToMany(
            cascade = {
                    CascadeType.ALL
            },
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "users_roles",
            inverseJoinColumns = @JoinColumn(name = "role_id", foreignKey = @ForeignKey(name = "fk_role_id"), referencedColumnName = "id") ,
            joinColumns = @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_user_id"), referencedColumnName = "id")
    )
    private Set<UserRole> userRoles = new HashSet<>();


    @OneToOne(mappedBy = "user", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private Doctor doctor;


    @OneToOne(mappedBy = "user", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private Patient patient;


    @OneToMany(cascade = CascadeType.ALL,
    mappedBy = "author",
    fetch = FetchType.LAZY,
    orphanRemoval = true)
    public Set<SentMessage> sentMessagesSet = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "reader",
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    public Set<ReceivedMessage> receivedMessagesSet = new HashSet<>();


    public User(String firstName, String secondName, String password, String login, HashSet<UserRole> userRoles) {
        super(firstName, secondName);
        this.userRoles = userRoles;
        this.password = password;
        this.login = login;
    }

    public User(String firstName, String secondName, String password, String login, Role role) {
        super(firstName, secondName);
        this.userRoles.add(new UserRole(role));
        this.password = password;
        this.login = login;
    }
    public User(String firstName, String secondName, String password, String login, UserRole userRole) {
        super(firstName, secondName);
        this.userRoles.add(userRole);
        this.password = password;
        this.login = login;
    }
}