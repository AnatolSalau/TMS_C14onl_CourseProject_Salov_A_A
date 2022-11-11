package by.salov.tms.courseproject.entities;

import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode()
@ToString

@Entity
@Table(name = "persistent_logins")
public class PersistentToken {

    @Id
    @Column(nullable = false)
    private String series;

    @Column(name = "username", nullable = false)
    private String userName;

    @Column(name = "token", nullable = false)
    private String token;

    @UpdateTimestamp
    @Column(name = "last_used", nullable = false)
    private Date updated;
}
