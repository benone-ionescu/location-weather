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
public class FieldsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @Column
    private Integer briefz_durch;
    @Column
    private Integer onrp;
    @Column
    private String kanton;
    @Column
    private String rec_art;
    @Column
    private Integer sprachcode;
    @Column
    private Integer postleitzahl;
    @Column
    private Integer bfsnr;
    @Column
    private String ortbez18;
    @Column
    private String plz_zz;
    @Column
    private Integer gplz;
    @Column
    private String ortbez27;
    @Column
    private String gilt_ab_dat;
    @Column
    private Integer plz_briefzust;
    @Column
    private Integer plz_typ;
}
