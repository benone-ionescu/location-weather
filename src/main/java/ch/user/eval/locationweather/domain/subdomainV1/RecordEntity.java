package ch.user.eval.locationweather.domain.subdomainV1;

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
public class RecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column
    private String datasetid;

    @Column
    private String recordid;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private FieldsEntity fields;

//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private Geometry geometry;

    @Column
    private String record_timestamp;
}
