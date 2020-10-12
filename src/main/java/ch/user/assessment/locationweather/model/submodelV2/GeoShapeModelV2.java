package ch.user.assessment.locationweather.model.submodelV2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeoShapeModelV2 {
    private GeometryModelV2 geometry;
    private String type;
    private Object properties;
}
