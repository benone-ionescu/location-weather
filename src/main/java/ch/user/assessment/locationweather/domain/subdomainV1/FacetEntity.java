package ch.user.assessment.locationweather.domain.subdomainV1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column
    private Integer count;

    @Column
    private String path;

    @Column
    private String state;

    @Column
    private String name;
}
