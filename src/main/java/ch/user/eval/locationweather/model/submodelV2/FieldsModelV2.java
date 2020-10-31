package ch.user.eval.locationweather.model.submodelV2;

import ch.user.eval.locationweather.model.submodelV1.FieldsModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

//@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class FieldsModelV2 extends FieldsModel {

    private GeoPoint2dV2 geo_point_2d;

    private GeoShapeModelV2 geo_shape;
}
