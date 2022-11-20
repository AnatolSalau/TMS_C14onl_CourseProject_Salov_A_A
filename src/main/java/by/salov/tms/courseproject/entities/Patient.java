package by.salov.tms.courseproject.entities;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

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

/*    @Column()
    private String isName = "PATIENT";*/

    @OneToOne(fetch = FetchType.EAGER, cascade = {
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH
    })
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_user_id"))
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST
    })
    @JoinTable(
            name = "doctors_patients",
            joinColumns = {@JoinColumn(name = "patient_id")},
            inverseJoinColumns = {@JoinColumn(name = "doctor_id")}
    )
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Doctor> doctors = new HashSet<>();

    public Patient(User user) {
        this.user = user;
    }
}
