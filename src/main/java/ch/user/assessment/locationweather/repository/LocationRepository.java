package ch.user.assessment.locationweather.repository;

import ch.user.assessment.locationweather.domain.LocationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationRepository extends CrudRepository<LocationEntity,Long> {
    Optional<LocationEntity> findById(@Param("id") Long id);
}
