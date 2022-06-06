package diploma.sportlife.model;

import diploma.sportlife.model.modelenum.ActivityType;
import diploma.sportlife.model.modelenum.EntityStatus;
import diploma.sportlife.model.modelenum.SportEventTypeOpportunities;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "activity_type")
    private ActivityType activityType;

    @Column(name = "event_type_opportunities")
    private SportEventTypeOpportunities sportEventTypeOpportunities;

}
