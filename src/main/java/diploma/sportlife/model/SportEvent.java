package diploma.sportlife.model;

import diploma.sportlife.model.modelenum.SportEventType;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SportEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "event_type")
    @Enumerated(EnumType.STRING)
    private SportEventType sportEventType;

    @Column(name = "start_date")
    private Timestamp startDate;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @EqualsAndHashCode.Exclude
    private User user;

    @Column(name = "town")
    private String town;

    @Column(name = "adress")
    private String address;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "activity_id", referencedColumnName = "id")
    @EqualsAndHashCode.Exclude
    private Activity activity;

    @Column(name = "min_count")
    private Integer minCount;

    @Column(name = "max_count")
    private Integer maxCount;
}
