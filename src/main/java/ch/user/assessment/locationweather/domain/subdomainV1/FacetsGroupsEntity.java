package ch.user.assessment.locationweather.domain.subdomainV1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacetsGroupsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<FacetEntity> facets;

    @Column
    private String name;
}
