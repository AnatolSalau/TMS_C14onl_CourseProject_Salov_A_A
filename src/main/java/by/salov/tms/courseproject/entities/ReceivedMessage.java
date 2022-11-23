package by.salov.tms.courseproject.entities;

import lombok.*;

import javax.persistence.*;

/**Entity for received messages */
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode()
@ToString

@Entity
@Table(name = "received_messages")
@SequenceGenerator(sequenceName = "received_messages_id_seq",
        name = "received_messages_id_seq", allocationSize = 1)
public class ReceivedMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "received_messagess_id_seq")
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String text;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "reader_id", foreignKey = @ForeignKey(name = "fk_reader_id"))
    private User reader;

    public ReceivedMessage(String text, User reader) {
        this.text = text;
        this.reader = reader;
    }
}
