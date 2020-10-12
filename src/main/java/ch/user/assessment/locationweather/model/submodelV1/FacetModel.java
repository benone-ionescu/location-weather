package ch.user.assessment.locationweather.model.submodelV1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacetModel {

    private Integer count;

    private String path;

    private String state;

    private String name;
}
