package ch.user.assessment.locationweather.model.submodelV1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacetsGroupsModel {

    private List<FacetModel> facets;

    private String name;
}
