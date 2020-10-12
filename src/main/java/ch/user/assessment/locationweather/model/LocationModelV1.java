package ch.user.assessment.locationweather.model;

import ch.user.assessment.locationweather.model.submodelV1.FacetsGroupsModel;
import ch.user.assessment.locationweather.model.submodelV1.ParametersModel;
import ch.user.assessment.locationweather.model.submodelV1.RecordModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationModelV1 {

    private Long nhits;

    private ParametersModel parameters;

    private List<RecordModel> records;

    private List<FacetsGroupsModel> facet_groups;
}
