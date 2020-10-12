package ch.user.assessment.locationweather.model.submodelV2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeoPoint2dV2 {

    private Double lon;

    private Double lat;

}
