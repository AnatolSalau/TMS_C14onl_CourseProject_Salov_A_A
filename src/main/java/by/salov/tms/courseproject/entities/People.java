package by.salov.tms.courseproject.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/** Abstract super class for all others entities */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode

@MappedSuperclass
public abstract class People {

    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "second_name", nullable = false)
    private String secondName;
}
