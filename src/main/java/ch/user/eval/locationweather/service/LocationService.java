package ch.user.eval.locationweather.service;

import ch.user.eval.locationweather.domain.subdomainV1.FieldsEntity;
import ch.user.eval.locationweather.domain.subdomainV1.RecordEntity;
import ch.user.eval.locationweather.model.LocationModelV1;
import ch.user.eval.locationweather.model.RecordModelPackV2;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class LocationService {
    private static final Logger logger = LoggerFactory.getLogger(LocationService.class);

    private final static String WEATHER_URL = "https://swisspost.opendatasoft.com/api/records/1.0/search";

    private final WebClient webClient;

    @Autowired
    public LocationService(/*RestTemplateBuilder restTemplateBuilder*/) {
        this.webClient = WebClient.builder()
                .baseUrl(WEATHER_URL)
                .filter(logRequest())
                .build();
    }

    private ExchangeFilterFunction logRequest() {
        return (clientRequest, next) -> {
            logger.info("Request: {} {}", clientRequest.method(), clientRequest.url());
            clientRequest.headers()
                    .forEach((name, values) -> values.forEach(value -> logger.info("{}={}", name, value)));
            return next.exchange(clientRequest);
        };
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
        return new JSONObject(
                webClient.get()
                        .uri(url)
                        .exchange()
                        .flatMapMany(clientResponse -> clientResponse.bodyToFlux(String.class)));
    }

    private LocationModelV1 getLocation(String url) {
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(LocationModelV1.class)
                .block();
    }

    private RecordModelPackV2 getRecordWithGeometry(String url) {
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(RecordModelPackV2.class)
                .block();
    }
}
