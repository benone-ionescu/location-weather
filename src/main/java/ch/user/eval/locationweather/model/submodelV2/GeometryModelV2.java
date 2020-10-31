package ch.user.eval.locationweather.model.submodelV2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeometryModelV2 {
    private String type;
    private List<List<List<Object>>> coordinates;
}
