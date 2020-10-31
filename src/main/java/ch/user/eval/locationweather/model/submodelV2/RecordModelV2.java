package ch.user.eval.locationweather.model.submodelV2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecordModelV2 {

    private String id;

    private String timestamp;

    private Long size;

    private FieldsModelV2 fields;

    //private GeometryModel geometry;
}
