package ch.user.eval.locationweather.model.submodelV1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecordModel {

    private String datasetid;

    private String recordid;

    private FieldsModel fields;

    //private GeometryModel geometry;

    private String record_timestamp;
}
