package diploma.sportlife.model;

import lombok.*;

import javax.persistence.*;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "event_id", referencedColumnName = "id", nullable = false)
    @EqualsAndHashCode.Exclude
    private SportEvent sportEvent;

    @Column(name = "count")
    private Integer count;
}
