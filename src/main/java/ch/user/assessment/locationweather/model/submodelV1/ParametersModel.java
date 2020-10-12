package ch.user.assessment.locationweather.model.submodelV1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParametersModel {

    private String dataset;

    private String timezone;

    private Integer q;

    private Integer rows;

    private String format;

    private List<String> facet;
}
