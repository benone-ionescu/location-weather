package ch.user.eval.locationweather.model;

import ch.user.eval.locationweather.model.submodelV2.LinksModelV2;
import ch.user.eval.locationweather.model.submodelV2.RecordModelV2;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecordModelPackV2 {

    private List<LinksModelV2> links;
    private RecordModelV2 record;
}
