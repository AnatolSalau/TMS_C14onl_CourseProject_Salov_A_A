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
@Table(name = "patients")
@SequenceGenerator(sequenceName = "patients_id_seq",
        name = "patients_id_seq", allocationSize = 1)
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patients_id_seq")
    @Column(nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST,
            CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_user_id"))
    private User user;

    @ManyToMany
    @JoinTable(
            name = "doctors_patients",
            joinColumns = {@JoinColumn(name = "patient_id")},
            inverseJoinColumns = {@JoinColumn(name = "doctor_id")}
    )
    private Set<Doctor> doctors = new HashSet<>();
}
