package ch.user.eval.locationweather.repository;

import ch.user.eval.locationweather.domain.subdomainV1.RecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecordRepository extends JpaRepository<RecordEntity, Long>{
    Optional<RecordEntity> findById(@Param("id") Long id);
    Optional<RecordEntity> findByRecordid(@Param("recordid") String recordid);
}
