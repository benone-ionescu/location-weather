package ch.user.eval.locationweather.model.submodelV1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeometryModel {

    private String type;

    private CoordinateModel coordinates;
}
