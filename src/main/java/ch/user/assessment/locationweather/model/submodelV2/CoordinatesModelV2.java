package ch.user.assessment.locationweather.model.submodelV2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoordinatesModelV2 {
    private Number longitude;

    private Number latitude;
}
