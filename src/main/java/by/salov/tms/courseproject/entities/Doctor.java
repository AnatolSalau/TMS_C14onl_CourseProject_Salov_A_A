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
@Table(name = "doctors")
@SequenceGenerator(sequenceName = "doctors_id_seq",
        name = "doctors_id_seq", allocationSize = 1)
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doctors_id_seq")
    @Column(nullable = false)
    private Long id;

/*
    @Column()
    private String isName = "DOCTOR";*/

    @OneToOne (fetch = FetchType.EAGER, cascade = {
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
    }, mappedBy = "doctors")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Patient> patients = new HashSet<>();

    public Doctor(User user) {
        this.user = user;
    }
}
