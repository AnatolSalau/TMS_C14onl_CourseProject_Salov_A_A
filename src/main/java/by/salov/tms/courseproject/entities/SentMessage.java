package by.salov.tms.courseproject.entities;

import lombok.*;

import javax.persistence.*;

/**Entity for sent messages */
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode()
@ToString

@Entity
@Table(name = "sent_messages")
@SequenceGenerator(sequenceName = "sent_messages_id_seq",
        name = "sent_messages_id_seq", allocationSize = 1)
public class SentMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sent_messages_id_seq")
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String text;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id", foreignKey = @ForeignKey(name = "fk_author_id"))
    private User author;


    public SentMessage(String text, User author) {
        this.text = text;
        this.author = author;
    }
}
