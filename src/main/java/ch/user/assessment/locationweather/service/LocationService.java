package ch.user.assessment.locationweather.service;

import ch.user.assessment.locationweather.domain.subdomainV1.FieldsEntity;
import ch.user.assessment.locationweather.domain.subdomainV1.RecordEntity;
import ch.user.assessment.locationweather.model.LocationModelV1;
import ch.user.assessment.locationweather.model.RecordModelPackV2;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LocationService {

    private final static String WEATHER_URL = "https://swisspost.opendatasoft.com/api/records/1.0/search";

    private final RestTemplate restTemplate;

    @Autowired
    public LocationService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    /**
     * Process Location
     * @param platz Location platz
     * @param record Location platz record
     * @return Location
     */
    public LocationModelV1 processLocation(Long platz, Integer record) {

        //example: http://localhost:8080/explore/3000/0

        String url = WEATHER_URL + "/?dataset=plz_verzeichnis_v2&q="+platz+"&facet=plz_zz&facet=ortbez18";

        LocationModelV1 location = this.getLocation(url);

        //https://swisspost.opendatasoft.com/api/v2/catalog/datasets/plz_verzeichnis_v2/records/5aa8c59bbed61fee7d780a966fbbeb2a414cd1c9?pretty=true&timezone=UTC

        RecordModelPackV2 recordModel = this.getRecordWithGeometry("https://swisspost.opendatasoft.com/api/v2/catalog/datasets/plz_verzeichnis_v2/records/" +
                location.getRecords().get(location.getRecords().size()-1).getRecordid().toString() +
                "?pretty=true&timezone=UTC");

        RecordEntity locationWeatherRecord = RecordEntity.builder()
                .datasetid(location.getRecords().get(record).getDatasetid().toString())
                .recordid(location.getRecords().get(record).getRecordid().toString())
                .fields(
                        new FieldsEntity().builder()
                                .kanton(location.getRecords().get(record).getFields().getKanton().toString())
                                .postleitzahl(Integer.valueOf(location.getRecords().get(record).getFields().getPostleitzahl().toString()))
                                .ortbez18(location.getRecords().get(record).getFields().getOrtbez18().toString())
                                .plz_zz(location.getRecords().get(record).getFields().getPlz_zz().toString())
                                .build())
                .record_timestamp(location.getRecords().get(record).getRecord_timestamp().toString())
                .build();

        return location;
    }

    /**
     * Process Record Model
     * @param platz Location platz
     * @param record Location platz record
     * @return Location
     */
    public RecordModelPackV2 processRecordWithGeometry(Long platz, Integer record) {

        //example: http://localhost:8080/explore/3000/0

        String url = WEATHER_URL + "/?dataset=plz_verzeichnis_v2&q="+platz+"&facet=plz_zz&facet=ortbez18";

        LocationModelV1 location = this.getLocation(url);

        //https://swisspost.opendatasoft.com/api/v2/catalog/datasets/plz_verzeichnis_v2/records/5aa8c59bbed61fee7d780a966fbbeb2a414cd1c9?pretty=true&timezone=UTC

        RecordModelPackV2 recordModel = this.getRecordWithGeometry("https://swisspost.opendatasoft.com/api/v2/catalog/datasets/plz_verzeichnis_v2/records/" +
                location.getRecords().get(location.getRecords().size()-1).getRecordid().toString() +
                "?pretty=true&timezone=UTC");

        return recordModel;
    }

    /**
     * Process Location Record
     * @param platz Location platz
     * @param record Location platz record
     * @return Location
     */
    public RecordEntity processLocationRecord(Long platz, Integer record) {

        //example: http://localhost:8080/explore/3000/0

        String url = WEATHER_URL + "/?dataset=plz_verzeichnis_v2&q="+platz+"&facet=plz_zz&facet=ortbez18";

        LocationModelV1 location = this.getLocation(url);

        RecordEntity locationWeatherRecord = RecordEntity.builder()
                .datasetid(location.getRecords().get(record).getDatasetid().toString())
                .recordid(location.getRecords().get(record).getRecordid().toString())
                .fields(
                        new FieldsEntity().builder()
                                .kanton(location.getRecords().get(record).getFields().getKanton().toString())
                                .postleitzahl(Integer.valueOf(location.getRecords().get(record).getFields().getPostleitzahl().toString()))
                                .ortbez18(location.getRecords().get(record).getFields().getOrtbez18().toString())
                                .plz_zz(location.getRecords().get(record).getFields().getPlz_zz().toString())
                                .build())
                .record_timestamp(location.getRecords().get(record).getRecord_timestamp().toString())
                .build();

        return locationWeatherRecord;
    }

    private JSONObject getJSON(String url) {
        return new JSONObject(this.restTemplate.getForObject(url, String.class));
    }

    private LocationModelV1 getLocation(String url) {
        return restTemplate.getForObject(url, LocationModelV1.class);
    }

    private RecordModelPackV2 getRecordWithGeometry(String url) {
        return restTemplate.getForObject(url, RecordModelPackV2.class);
    }
}
