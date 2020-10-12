package ch.user.assessment.locationweather.model.submodelV1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FieldsModel {

    private Integer briefz_durch;

    private Integer onrp;

    private String kanton;

    private String rec_art;

    private Integer sprachcode;

    private Integer postleitzahl;

    private Integer bfsnr;

    private String ortbez18;

    private String plz_zz;

    private Integer gplz;

    private String ortbez27;

    private String gilt_ab_dat;

    private Integer plz_briefzust;

    private Integer plz_typ;
}
