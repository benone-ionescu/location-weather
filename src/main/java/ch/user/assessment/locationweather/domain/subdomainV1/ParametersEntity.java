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
public class ParametersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column
    private String dataset;

    @Column
    private String timezone;

    @Column
    private Integer q;

    @Column
    private Integer rows;

    @Column
    private String format;

    @Embedded
    private List<String> facet;
}
