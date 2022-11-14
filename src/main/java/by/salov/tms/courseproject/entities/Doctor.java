package by.salov.tms.courseproject.entities;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode()
@ToString

@Entity
@Table(name = "doctors")
@SequenceGenerator(sequenceName = "doctors_id_seq",
        name = "doctors_id_seq", allocationSize = 1)
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doctors_id_seq")
    @Column(nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {
            CascadeType.DETACH,
            CascadeType.REFRESH,
            CascadeType.MERGE,
            CascadeType.PERSIST
    })
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_user_id"))
    private User user;

    @ManyToMany
    @JoinTable(
            name = "doctors_patients",
            joinColumns = {@JoinColumn(name = "doctor_id")},
            inverseJoinColumns = {@JoinColumn(name = "patient_id")}
    )
    private Set<Patient> patients = new HashSet<>();

    public Doctor(Long id, User user) {
        this.id = id;
        this.user = user;
    }
}
