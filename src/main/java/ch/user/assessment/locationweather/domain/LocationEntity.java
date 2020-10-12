package ch.user.assessment.locationweather.domain;

import ch.user.assessment.locationweather.domain.subdomainV1.FacetsGroupsEntity;
import ch.user.assessment.locationweather.domain.subdomainV1.ParametersEntity;
import ch.user.assessment.locationweather.domain.subdomainV1.RecordEntity;
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
public class LocationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column
    private Long nhits;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private ParametersEntity parameters;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<RecordEntity> records;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<FacetsGroupsEntity> facet_groups;
}
