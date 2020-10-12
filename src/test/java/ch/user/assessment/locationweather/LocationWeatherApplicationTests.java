package ch.user.assessment.locationweather;

import ch.user.assessment.locationweather.domain.subdomainV1.RecordEntity;
import ch.user.assessment.locationweather.repository.RecordRepository;
import ch.user.assessment.locationweather.service.LocationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@SpringBootTest
class LocationWeatherApplicationTests {

    private final static String DATASET_ID =  "plz_verzeichnis_v2";

    private final static String RECORD_POSTLEITZAHL_BE = "3000";
    private final static Integer RECORD_ID_BE = 1;
    private final static String LOCATION_RECORD_ID_BE = "814e4a1c329ab47a89aacc0aaf0505815a10117a";
    private final static String LOCATION_KANTON_BE =  "BE";
    private final static String LOCATION_POSTLEITZAHL_BE = "3000";
    private final static String LOCATION_ORTBEZIRK_BE = "Bern 13";
    private final static String LOCATION_PLZ_BE = "13";


    private final static String RECORD_POSTLEITZAHL_FR = "1700";
    private final static Integer RECORD_ID_FR = 3;
    private final static String LOCATION_RECORD_ID_FR = "11d9d61ac828c3c09a5aa9a4dc2f9052d34edd5c";
    private final static String LOCATION_KANTON_FR =  "FR";
    private final static String LOCATION_POSTLEITZAHL_FR = "1702";
    private final static String LOCATION_ORTBEZIRK_FR = "Fribourg";
    private final static String LOCATION_PLZ_FR = "00";

    @Autowired
    RecordRepository recordRepository;

    @Autowired
    LocationService locationService;

    @Test
    void checkDataForSpecificLocation_Bern() {
        testLocation(RECORD_POSTLEITZAHL_BE, RECORD_ID_BE, LOCATION_RECORD_ID_BE,
                DATASET_ID, LOCATION_KANTON_BE,
                LOCATION_POSTLEITZAHL_BE, LOCATION_ORTBEZIRK_BE,
                LOCATION_PLZ_BE);
    }

    @Test
    void checkDataForSpecificLocation_Fribourg() {
        testLocation(RECORD_POSTLEITZAHL_FR, RECORD_ID_FR, LOCATION_RECORD_ID_FR,
                DATASET_ID, LOCATION_KANTON_FR,
                LOCATION_POSTLEITZAHL_FR, LOCATION_ORTBEZIRK_FR,
                LOCATION_PLZ_FR);
    }

    private void testLocation(String record_postleitzahl, int recordid, String locationid,
                              String datasetid, String location_kanton,
                              String location_postleitzahl, String location_ortbezirk,
                              String location_plz) {
        RecordEntity records = locationService.processLocationRecord(Long.valueOf(record_postleitzahl), recordid);
        recordRepository.save(records);

        Optional<RecordEntity> recordEntityTest = recordRepository.findByRecordid(locationid);

        assertEquals(datasetid, recordEntityTest.get().getDatasetid());

        assertEquals(location_kanton, recordEntityTest.get().getFields().getKanton());
        assertEquals(location_postleitzahl, recordEntityTest.get().getFields().getPostleitzahl().toString());
        assertEquals(location_ortbezirk, recordEntityTest.get().getFields().getOrtbez18());
        assertEquals(location_plz, recordEntityTest.get().getFields().getPlz_zz());
    }


}
