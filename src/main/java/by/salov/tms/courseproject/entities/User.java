package by.salov.tms.courseproject.entities;

import by.salov.tms.courseproject.entities.roles.UserRole;
import lombok.*;

import javax.persistence.*;
import java.util.List;

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

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST,
            CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "user_role_id", foreignKey = @ForeignKey(name = "fk_user_role_id"))
    private UserRole userRole;

    @ToString.Exclude
    @OneToOne(mappedBy = "user", cascade = {CascadeType.ALL})
    private Doctor doctor;

    @ToString.Exclude
    @OneToOne(mappedBy = "user", cascade = {CascadeType.ALL})
    private Patient patient;

    public User(String firstName, String secondName, String password, UserRole userRole) {
        super(firstName, secondName);
        this.userRole = userRole;
        this.password = password;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
