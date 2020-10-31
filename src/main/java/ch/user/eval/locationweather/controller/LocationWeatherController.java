package ch.user.eval.locationweather.controller;

import ch.user.eval.locationweather.domain.LocationEntity;
import ch.user.eval.locationweather.domain.subdomainV1.FieldsEntity;
import ch.user.eval.locationweather.domain.subdomainV1.RecordEntity;
import ch.user.eval.locationweather.model.LocationModelV1;
import ch.user.eval.locationweather.model.RecordModelPackV2;
import ch.user.eval.locationweather.model.submodelV2.GeoPoint2dV2;
import ch.user.eval.locationweather.repository.LocationRepository;
import ch.user.eval.locationweather.repository.RecordRepository;
import ch.user.eval.locationweather.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocationWeatherController {

    private final RecordRepository recordRepository;
    private final LocationRepository locationRepository;
    private final LocationService locationService;

    @Autowired
    public LocationWeatherController(RecordRepository recordRepository,
                                     LocationRepository locationRepository,
                                     LocationService locationService){
        this.recordRepository = recordRepository;
        this.locationRepository = locationRepository;
        this.locationService = locationService;
    }

    @GetMapping(value = "/explore/{platz}/{record}")
    public LocationModelV1 getLocationInfo(
            @PathVariable(value = "platz") Long platz,
            @PathVariable(value = "record") Integer record) {

        //example: http://localhost:8080/explore/3000/0

        LocationModelV1 locationItem = locationService.processLocation(platz, record);

        LocationEntity locationEntity = LocationEntity.builder()
                .nhits(locationItem.getNhits())
                .build();
        locationRepository.save(locationEntity);

        return locationItem;
    }

    @GetMapping(value = "/explore_geometry/{platz}/{record}")
    public GeoPoint2dV2 getRecordWithGeometryInfo(
            @PathVariable(value = "platz") Long platz,
            @PathVariable(value = "record") Integer record) {

        //example: http://localhost:8080/explore_geometry/3000/0

        RecordModelPackV2 recordWithGeometryItem = locationService.processRecordWithGeometry(platz, record);

        return recordWithGeometryItem.getRecord().getFields().getGeo_point_2d();
    }

    @GetMapping(value = "/explore/record/{platz}/{record}")
    public RecordEntity getLocationReservationInfo(
            @PathVariable(value = "platz") Long platz,
            @PathVariable(value = "record") Integer record) {

        //example: http://localhost:8080/explore/record/3000/0

        RecordEntity recordItem = locationService.processLocationRecord(platz, record);
        recordRepository.save(recordItem);

        return recordItem;
    }

    @GetMapping(value = "/test")
    public RecordEntity test() {
        //example: http://localhost:8080/test

        RecordEntity locationWeather = RecordEntity.builder()
                .datasetid("plz_verzeichnis_v2")
                .recordid("980316669cb017bab21081476ea0e3cfed8ad4d1")
                .fields(
                        new FieldsEntity().builder()
                                .kanton("BE")
                                .postleitzahl(3000)
                                .ortbez18("Bern 14")
                                .plz_zz("14")
                                .build())
                .record_timestamp("2020-08-28T23:00:07.854000+00:00")
                .build();

        return locationWeather;
    }
}
